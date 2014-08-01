package com.yermoon.utils;

import com.yermoon.vo.HttpRespons;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * Java HTTP请求对象 发送GET/POST请求工具类
 *
 * @author wangqing
 * @since 14-4-16 下午1:42
 */
@Component
public class HttpRequester {
    private String defaultContentEncoding;

    public HttpRequester() {
        this.defaultContentEncoding = "utf-8";
    }

    /**
     * 发送GET请求
     *
     * @param urlString URL地址
     * @return 响应对象
     * @throws Exception
     */
    public HttpRespons sendGet(String urlString) throws Exception {
        return this.send(urlString, "GET", new HashMap<String, String>(), new HashMap<String, String>());
    }

    /**
     * 发送GET请求
     *
     * @param urlString URL地址
     * @param params    参数集合
     * @return 响应对象
     * @throws Exception
     */
    public HttpRespons sendGet(String urlString, Map<String, String> params) throws Exception {
        return this.send(urlString, "GET", params, null);
    }

    /**
     * 发送GET请求
     *
     * @param urlString URL地址
     * @param params    参数字符串
     * @return 响应对象
     * @throws Exception
     */
    public HttpRespons sendGet(String urlString, String params) throws Exception {
        return this.send(urlString, "GET", params, null);
    }

    /**
     * 发送GET请求
     *
     * @param urlString URL地址
     * @param params    参数集合
     * @param propertys 请求属性
     * @return 响应对象
     * @throws Exception
     */
    public HttpRespons sendGet(String urlString, Map<String, String> params, Map<String, String> propertys)
            throws Exception {
        return this.send(urlString, "GET", params, propertys);
    }

    /**
     * 发送GET请求
     *
     * @param urlString URL地址
     * @param params    参数字符串
     * @param propertys 请求属性
     * @return 响应对象
     * @throws Exception
     */
    public HttpRespons sendGet(String urlString, String params, Map<String, String> propertys)
            throws Exception {
        return this.send(urlString, "GET", params, propertys);
    }

    /**
     * 发送POST请求
     *
     * @param urlString URL地址
     * @return 响应对象
     * @throws Exception
     */
    public HttpRespons sendPost(String urlString) throws Exception {
        return this.send(urlString, "POST", new HashMap<String, String>(), new HashMap<String, String>());
    }

    /**
     * 发送POST请求
     *
     * @param urlString URL地址
     * @param params    参数集合
     * @return 响应对象
     * @throws Exception
     */
    public HttpRespons sendPost(String urlString, Map<String, String> params) throws Exception {
        return this.send(urlString, "POST", params, null);
    }

    /**
     * 发送POST请求
     *
     * @param urlString URL地址
     * @param params    参数字符串
     * @return 响应对象
     * @throws Exception
     */
    public HttpRespons sendPost(String urlString, String params) throws Exception {
        return this.send(urlString, "POST", params, null);
    }

    /**
     * 发送POST请求
     *
     * @param urlString URL地址
     * @param params    参数集合
     * @param propertys 请求属性
     * @return 响应对象
     * @throws Exception
     */
    public HttpRespons sendPost(String urlString, Map<String, String> params, Map<String, String> propertys)
            throws Exception {
        return this.send(urlString, "POST", params, propertys);
    }

    /**
     * 发送POST请求
     *
     * @param urlString URL地址
     * @param params    参数字符串
     * @param propertys 请求属性
     * @return 响应对象
     * @throws Exception
     */
    public HttpRespons sendPost(String urlString, String params, Map<String, String> propertys)
            throws Exception {
        return this.send(urlString, "POST", params, propertys);
    }

    /**
     * 发送HTTP请求
     *
     * @param urlString  地址
     * @param method     get/post
     * @param parameters 添加由键值对指定的请求参数
     * @param propertys  添加由键值对指定的一般请求属性
     * @return 响映对象
     * @throws Exception
     */
    private HttpRespons send(String urlString, String method, String parameters,
                             Map<String, String> propertys) throws Exception {

        HttpURLConnection urlConnection = null;

        if (method.equalsIgnoreCase("GET") && !StringUtils.isBlank(parameters)) {
            StringBuffer param = new StringBuffer();
            param.append("?");
            param.append(parameters);
            urlString += param;
        }

        URL url = new URL(urlString);
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod(method);
        urlConnection.setDoOutput(true);
        urlConnection.setDoInput(true);
        urlConnection.setUseCaches(false);

        if (propertys != null)
            for (String key : propertys.keySet()) {
                urlConnection.addRequestProperty(key, propertys.get(key));
            }

        if (method.equalsIgnoreCase("POST") && parameters != null) {
            urlConnection.getOutputStream().write(parameters.getBytes());
            urlConnection.getOutputStream().flush();
            urlConnection.getOutputStream().close();
        }
        return this.makeContent(urlString, urlConnection);
    }

    /**
     * 发送HTTP请求
     *
     * @param urlString  地址
     * @param method     get/post
     * @param parameters 添加由键值对指定的请求参数
     * @param propertys  添加由键值对指定的一般请求属性
     * @return 响映对象
     * @throws Exception
     */
    private HttpRespons send(String urlString, String method, Map<String, String> parameters,
                             Map<String, String> propertys) throws Exception {
        HttpURLConnection urlConnection = null;

        if (method.equalsIgnoreCase("GET") && parameters != null) {
            StringBuffer param = new StringBuffer();
            int i = 0;
            for (String key : parameters.keySet()) {
                if (i == 0)
                    param.append("?");
                else
                    param.append("&");
                param.append(key).append("=").append(parameters.get(key));
                i++;
            }
            urlString += param;
        }

        URL url = new URL(urlString);
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod(method);
        urlConnection.setDoOutput(true);
        urlConnection.setDoInput(true);
        urlConnection.setUseCaches(false);

        if (propertys != null)
            for (String key : propertys.keySet()) {
                urlConnection.addRequestProperty(key, propertys.get(key));
            }

        if (method.equalsIgnoreCase("POST") && parameters != null) {
            StringBuffer param = new StringBuffer();
            for (String key : parameters.keySet()) {
                param.append("&");
                param.append(key).append("=").append(parameters.get(key));
            }
            urlConnection.getOutputStream().write(param.toString().getBytes());
            urlConnection.getOutputStream().flush();
            urlConnection.getOutputStream().close();
        }
        return this.makeContent(urlString, urlConnection);
    }

    /**
     * 得到响应对象
     *
     * @param urlConnection
     * @return 响应对象
     * @throws Exception
     */
    private HttpRespons makeContent(String urlString, HttpURLConnection urlConnection) throws Exception {
        HttpRespons httpResponser = new HttpRespons();
        try {
            InputStream in = urlConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            httpResponser.setContentCollection(new Vector<String>());
            StringBuffer temp = new StringBuffer();
            String line = bufferedReader.readLine();
            while (line != null) {
//                httpResponser.getContentCollection().add(line);
                temp.append(line).append("\r\n");
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            String ecod = urlConnection.getContentEncoding();
            if (ecod == null)
                ecod = this.defaultContentEncoding;
            httpResponser.setUrlString(urlString);
            httpResponser.setDefaultPort(urlConnection.getURL().getDefaultPort());
            httpResponser.setFile(urlConnection.getURL().getFile());
            httpResponser.setHost(urlConnection.getURL().getHost());
            httpResponser.setPath(urlConnection.getURL().getPath());
            httpResponser.setPort(urlConnection.getURL().getPort());
            httpResponser.setProtocol(urlConnection.getURL().getProtocol());
            httpResponser.setQuery(urlConnection.getURL().getQuery());
            httpResponser.setRef(urlConnection.getURL().getRef());
            httpResponser.setUserInfo(urlConnection.getURL().getUserInfo());
            httpResponser.setContent(new String(temp.toString().getBytes(), ecod));
            httpResponser.setContentEncoding(ecod);
            httpResponser.setCode(urlConnection.getResponseCode());
            httpResponser.setMessage(urlConnection.getResponseMessage());
            httpResponser.setContentType(urlConnection.getContentType());
            httpResponser.setMethod(urlConnection.getRequestMethod());
            httpResponser.setConnectTimeout(urlConnection.getConnectTimeout());
            httpResponser.setReadTimeout(urlConnection.getReadTimeout());
            return httpResponser;
        } catch (Exception e) {
            throw e;
        } finally {
            if (urlConnection != null)
                urlConnection.disconnect();
        }
    }

    /**
     * 默认的响应字符集
     */
    public String getDefaultContentEncoding() {
        return this.defaultContentEncoding;
    }

    /**
     * 设置默认的响应字符集
     */
    public void setDefaultContentEncoding(String defaultContentEncoding) {
        this.defaultContentEncoding = defaultContentEncoding;
    }

}