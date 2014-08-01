package com.yermoon.controller;

import com.yermoon.dto.DbSrcDto;
import com.yermoon.dto.Response;
import com.yermoon.entity.DbSrcEntity;
import com.yermoon.service.DataBaseService;
import com.yermoon.service.MybatisService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * Mybatis工具集
 *
 * @author wangqing
 * @since 14-4-16 下午1:42
 */
@Controller
@RequestMapping("/mybatis")
public class MybatisController {
    private final Logger log = LoggerFactory.getLogger(MybatisController.class);
    @Resource
    private MybatisService mybatisService;
    @Resource
    private DataBaseService dataBaseService;

    /**
     * 跳转到创建配置文件
     *
     * @return mbcreate跳转、Pagename、数据源
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView showSplit() {
        ModelAndView mav = new ModelAndView("mbcreate");
        mav.getModel().put("pageName", "Mybatis生成配置文件");
        return mav;
    }

    @RequestMapping(value = "/ctconfig", method = RequestMethod.POST)
    @ResponseBody
    public Response createConfig(int dbid, String tablename, String path, String packagePath) {
        Response resp = new Response();
        if (dbid < 1 || StringUtils.isBlank(tablename)) {
            resp.setStatus(0);
            resp.setMsg("参数不合法");
            return resp;
        }
        if (StringUtils.isBlank(packagePath)) {
            packagePath = "com.yermoon.entity";
        }
        if (StringUtils.isBlank(path)) {
            path = "C:\\";
        }
        try {
            DbSrcEntity db = mybatisService.getDbSrcEntity(dbid);
            mybatisService.createConfig(db, tablename, path, packagePath);
            resp.setStatus(1);
        } catch (Exception e) {
            log.error("创建文件异常", e);
            resp.setStatus(0);
            resp.setMsg("创建文件异常");
        }
        return resp;
    }

    @RequestMapping(value = "/ctdb", method = RequestMethod.POST)
    @ResponseBody
    public Response addDbSrc(DbSrcDto dto) {
        Response resp = new Response();
        DbSrcEntity dbSrcEntity = new DbSrcEntity();
        dbSrcEntity.setDbType(dto.getDbType());
        dbSrcEntity.setJdbcUrl(dto.getJdbcUrl());
        dbSrcEntity.setUserName(dto.getUserName());
        dbSrcEntity.setPassword(dto.getPassword());
        if (dbSrcEntity.getDbType() < 1 ||
                StringUtils.isBlank(dbSrcEntity.getJdbcUrl())) {
            resp.setStatus(Response.FAILURE);
            resp.setMsg("参数不合法");
            return resp;
        }
        try {
            mybatisService.createDbSrc(dbSrcEntity);
            resp.setStatus(Response.SUCCESS);
        } catch (Exception e) {
            log.error("添加数据源失败", e);
            resp.setStatus(Response.FAILURE);
            resp.setMsg("添加数据源失败");
        }
        return resp;
    }

    @RequestMapping(value = "/finddb", method = RequestMethod.POST)
    @ResponseBody
    public Response findDbSrc() {
        Response resp = new Response();
        try {
            List<DbSrcEntity> list = mybatisService.findAllDbSrc();
            resp.setData(list);
            resp.setStatus(Response.SUCCESS);
        } catch (Exception e) {
            log.error("查询数据源失败", e);
            resp.setStatus(Response.FAILURE);
            resp.setMsg("查询数据源失败");
        }
        return resp;
    }

    @RequestMapping(value = "/findtables", method = RequestMethod.GET)
    @ResponseBody
    public Response findTables(int dbsrcId) {
        Response resp = new Response();
        try {
            List<String> list = mybatisService.findAllTables(dbsrcId);
            resp.setData(list);
            resp.setStatus(Response.SUCCESS);
        } catch (Exception e) {
            log.error("查询数据源失败", e);
            resp.setStatus(Response.FAILURE);
            resp.setMsg("查询数据源失败");
        }
        return resp;
    }
    @RequestMapping(value = "/deldb", method = RequestMethod.POST)
    @ResponseBody
    public Response delDatabase(int dbid) {
        Response resp = new Response();
        try {
            mybatisService.deleteDbSrc(dbid);
            resp.setStatus(Response.SUCCESS);
        } catch (Exception e) {
            log.error("删除数据源失败", e);
            resp.setStatus(Response.FAILURE);
            resp.setMsg("删除数据源失败");
        }
        return resp;
    }
}
