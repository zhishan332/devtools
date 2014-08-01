package com.yermoon.controller;

import com.yermoon.dto.Response;
import com.yermoon.service.HttpService;
import com.yermoon.vo.HttpRespons;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Http工具
 *
 * @author wangqing
 * @since 14-4-16 下午1:42
 */
@Controller
@RequestMapping("/http")
public class HttpController {
    public static final int POSTMETHODETYPE = 1;
    public static final int GETMETHODTYPE = 2;
    private static final Logger log = LoggerFactory.getLogger(HttpController.class);
    @Resource
    private HttpService httpService;

    @RequestMapping(value = "/dopost", method = RequestMethod.GET)
    public ModelAndView showPost() {
        ModelAndView mav = new ModelAndView("post");
        mav.getModel().put("pageName", "模拟Http请求");
        return mav;
    }

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    @ResponseBody
    public Response send(String url, String param, int methodType) {
        Response resp = new Response();
        if (StringUtils.isBlank(url) || methodType < 1) {
            resp.setStatus(Response.FAILURE);
            resp.setMsg("参数错误，请检查");
            return resp;
        } else {
            switch (methodType) {
                case POSTMETHODETYPE:
                    try {
                        HttpRespons httpRsp = httpService.doPost(url, param);
//                        httpRsp.setContent(HtmlUtils.removeTagFromText(httpRsp.getContent()));
                        resp.setStatus(Response.SUCCESS);
                        resp.setData(httpRsp);
                    } catch (Exception e) {
                        log.error("HTTP模拟请求失败", e);
                        resp.setStatus(Response.FAILURE);
                        resp.setMsg("内部错误");
                    }
                    break;
                case GETMETHODTYPE:
                    try {
                        HttpRespons httpRsp = httpService.doGet(url, param);
//                        httpRsp.setContent(HtmlUtils.removeTagFromText(httpRsp.getContent()));
                        resp.setStatus(Response.SUCCESS);
                        resp.setData(httpRsp);
                    } catch (Exception e) {
                        log.error("HTTP模拟请求失败", e);
                        resp.setStatus(Response.FAILURE);
                        resp.setMsg("内部错误");
                    }
                    break;
                default:
                    resp.setStatus(Response.FAILURE);
                    resp.setMsg("请求失败");
            }
        }
        return resp;
    }
}
