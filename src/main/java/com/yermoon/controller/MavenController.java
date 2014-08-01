package com.yermoon.controller;

import com.yermoon.dto.Maven;
import com.yermoon.dto.Response;
import com.yermoon.service.MavenService;
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
 * Maven工具集
 *
 * @author wangqing
 * @since 14-4-16 下午1:42
 */
@Controller
@RequestMapping("/maven")
public class MavenController {
    private static final Logger log = LoggerFactory.getLogger(MavenController.class);
    @Resource
    private MavenService mavenService;

    /**
     * 跳转到字符串拆分
     *
     * @return
     */
    @RequestMapping(value = "/impjar", method = RequestMethod.GET)
    public ModelAndView showSplit() {
        ModelAndView mav = new ModelAndView("impljar");
        mav.getModel().put("pageName", "Maven本地Jar包导入");
        return mav;
    }

    /**
     * 导入本地Jar
     *
     * @param maven
     * @param jarPath
     * @return
     */
    @RequestMapping(value = "/doimp", method = RequestMethod.POST)
    @ResponseBody
    public Response doImp(Maven maven, String jarPath) {
        Response resp = new Response();

        if (StringUtils.isBlank(jarPath)) {
            resp.setStatus(Response.FAILURE);
            resp.setMsg("路径不能为空");
            return resp;
        }
        try {
            String res = mavenService.implLocalJar(maven, jarPath);
            resp.setStatus(Response.SUCCESS);
            resp.setData(res);
        } catch (Exception e) {
            resp.setStatus(Response.FAILURE);
            resp.setMsg("内部错误");
            log.error("导入本地Jar错误", e);
        }
        return resp;
    }

}
