package com.yermoon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 首页
 *
 * @author wangqing
 * @since 14-4-16 下午1:42
 */

@Controller
@RequestMapping("")
public class IndexController {

    @RequestMapping(value = {"", "index", "/index"}, method = RequestMethod.GET)
    public ModelAndView showIndex() {
        ModelAndView mav = new ModelAndView("split");
        mav.getModel().put("pageName", "首页");
        return mav;
    }
}
