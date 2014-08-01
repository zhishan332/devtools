package com.yermoon.controller;

import com.yermoon.dto.Response;
import com.yermoon.utils.JsonValidator;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * 字符串工具集
 *
 * @author wangqing
 * @since 14-4-16 下午1:42
 */
@Controller
@RequestMapping("/str")
public class StringController {
    @Resource
    private JsonValidator jsonValidator;

    /**
     * 跳转到字符串拆分
     *
     * @return
     */
    @RequestMapping(value = "/split", method = RequestMethod.GET)
    public ModelAndView showSplit() {
        ModelAndView mav = new ModelAndView("split");
        mav.getModel().put("pageName", "字符串拆分");
        return mav;
    }

    /**
     * 跳转到Json格式化
     *
     * @return
     */
    @RequestMapping(value = "/jsonfmt", method = RequestMethod.GET)
    public ModelAndView showJsonFormat() {
        ModelAndView mav = new ModelAndView("jsonfmt");
        mav.getModel().put("pageName", "Json格式化");
        return mav;
    }

    /**
     * 判断是否是Json
     *
     * @param jsonStr
     * @return
     */
    @RequestMapping(value = "/isjson", method = RequestMethod.GET)
    @ResponseBody
    public Response isJson(String jsonStr) {
        Response resp = new Response();
        resp.setStatus(1);
        if (StringUtils.isBlank(jsonStr)) {
            resp.setData(false);
            return resp;
        }
        resp.setData(jsonValidator.validate(jsonStr));
        return resp;
    }
}
