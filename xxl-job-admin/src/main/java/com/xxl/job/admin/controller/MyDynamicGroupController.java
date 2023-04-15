package com.xxl.job.admin.controller;

import com.xxl.job.admin.controller.annotation.PermissionLimit;
import com.xxl.job.admin.dao.XxlJobGroupDao;
import com.xxl.job.core.biz.model.ReturnT;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
/**
 * @author: neo.lin
 * @date: 2023/4/14 17:59
 */
@Controller
@RequestMapping("/api/jobgroup")
public class MyDynamicGroupController {
    @Resource
    public XxlJobGroupDao xxlJobGroupDao;

    @ResponseBody
    @RequestMapping(value = "/findByAppname",method = RequestMethod.GET)
    @PermissionLimit(limit=false)
    public ReturnT<Integer> findByAppname(String appname) {
        Integer groupId = xxlJobGroupDao.findByAppname(appname);
        return new ReturnT<>(groupId);
    }

}
