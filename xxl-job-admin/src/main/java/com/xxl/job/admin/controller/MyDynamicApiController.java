package com.xxl.job.admin.controller;
import com.xxl.job.admin.controller.annotation.PermissionLimit;
import com.xxl.job.admin.core.model.XxlJobInfo;
import com.xxl.job.admin.core.model.XxlJobQuery;
import com.xxl.job.admin.service.XxlJobService;
import com.xxl.job.core.biz.model.ReturnT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;
/**
 * @author: neo.lin
 * @date: 2023/4/11 16:14
 */


/**
 * @Author: JCccc
 * @Date: 2022-6-2 14:23
 * @Description: xxl job rest api
 */
@RestController
@RequestMapping("/api/jobinfo")
public class MyDynamicApiController {
    private static Logger logger = LoggerFactory.getLogger(MyDynamicApiController.class);
    @Autowired
    private XxlJobService xxlJobService;
    @RequestMapping(value = "/pageList",method = RequestMethod.POST)
    @PermissionLimit(limit=false)
    public Map<String, Object> pageList(@RequestBody XxlJobQuery xxlJobQuery) {
        return xxlJobService.pageList(
                xxlJobQuery.getStart(),
                xxlJobQuery.getLength(),
                xxlJobQuery.getJobGroup(),
                xxlJobQuery.getTriggerStatus(),
                xxlJobQuery.getJobDesc(),
                xxlJobQuery.getExecutorHandler(),
                xxlJobQuery.getAuthor());
    }

    @PostMapping("/save")
    @PermissionLimit(limit=false)
    public ReturnT<String> add(@RequestBody(required = true)XxlJobInfo jobInfo) {

        long nextTriggerTime = 5;
        jobInfo.setTriggerStatus(1);
        jobInfo.setTriggerLastTime(0);
        jobInfo.setTriggerNextTime(nextTriggerTime);
        jobInfo.setUpdateTime(new Date());
        return xxlJobService.add(jobInfo);
//        if(jobInfo.getId()==0){
//            return xxlJobService.add(jobInfo);
//        }else{
//            return xxlJobService.update(jobInfo);
//        }
    }

    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    @PermissionLimit(limit=false)
    public ReturnT<String> delete(int id) {
        return xxlJobService.remove(id);
    }

    @RequestMapping(value = "/start",method = RequestMethod.GET)
    @PermissionLimit(limit=false)
    public ReturnT<String> start(int id) {
        return xxlJobService.start(id);
    }

    @RequestMapping(value = "/stop",method = RequestMethod.GET)
    @PermissionLimit(limit=false)
    public ReturnT<String> stop(int id) {
        return xxlJobService.stop(id);
    }

}