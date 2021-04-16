package com.ruoyi.project.system.controller;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.project.system.domain.*;
import com.ruoyi.project.system.service.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 产值提报Controller
 * 
 * @author ruoyi
 * @date 2021-03-26
 */
@RestController
@RequestMapping("/system/projectReport")
public class ProjectReportController extends BaseController
{
    @Autowired
    private IProjectReportService projectReportService;
    @Autowired
    private IProjectReportChildService projectReportChildService;
    @Autowired
    private ISystemFileService systemFileService;
    @Autowired
    private IFlowInfoService flowInfoService;
    @Autowired
    private IFlowNodeService flowNodeService;
    @Autowired
    private IFlowAuditService flowAuditService;
    /**
     * 查询产值提报列表
     */
    @PreAuthorize("@ss.hasPermi('system:projectReport:list')")
    @GetMapping("/list")
    @DataScope(deptAlias = "d", userAlias = "u")
    public TableDataInfo list(ProjectReport projectReport)
    {
        startPage();
        List<ProjectReport> list = projectReportService.selectProjectReportList(projectReport);
        return getDataTable(list);
    }


    /**
     * 查询产值提报审核列表
     */
    @GetMapping("/shList")
    //@DataScope(deptAlias = "d", userAlias = "u")
    public TableDataInfo shList(ProjectReport projectReport)
    {
        startPage();
        String userId=SecurityUtils.getUsername();
        String roleId=SecurityUtils.getLoginUser().getUser().getRoles().get(0).getRoleId()+"";
        projectReport.setUserId(userId);
        projectReport.setRoleId(roleId);
        List<ProjectReport> list = projectReportService.selectProjectReportShList(projectReport);
        return getDataTable(list);
    }

    /**
     * 导出产值提报列表
     */
    @PreAuthorize("@ss.hasPermi('system:projectReport:export')")
    @Log(title = "产值提报", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ProjectReport projectReport)
    {
        List<ProjectReport> list = projectReportService.selectProjectReportList(projectReport);
        ExcelUtil<ProjectReport> util = new ExcelUtil<ProjectReport>(ProjectReport.class);
        return util.exportExcel(list, "projectReport");
    }

    /**
     * 获取产值提报详细信息
     */
   // @PreAuthorize("@ss.hasPermi('system:projectReport:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(projectReportService.selectProjectReportById(id));
    }

    /**
     * 新增产值提报
     */
    @PreAuthorize("@ss.hasPermi('system:projectReport:add')")
    @Log(title = "产值提报", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProjectReport projectReport)
    {
        projectReport.setCreateBy(SecurityUtils.getUsername());
        projectReport.setDjNumber(StringUtils.getRandomCode("PR"));
        if(projectReport.getRows()!=null&&projectReport.getRows()!="") {
            List<ProjectReportChild> childList = JSONArray.parseArray(projectReport.getRows(), ProjectReportChild.class);
            for (ProjectReportChild child : childList) {
                child.setCreateBy(SecurityUtils.getUsername());
                child.setDjNumber(projectReport.getDjNumber());
                child.setCreateTime(DateUtils.getNowDate());
                projectReportChildService.insertProjectReportChild(child);
            }
        }
        //插入附件
        if(projectReport.getFileRows()!=null&&projectReport.getFileRows()!="") {
            List<SystemFile> childList = JSONArray.parseArray(projectReport.getFileRows(), SystemFile.class);
            for (SystemFile child : childList) {
                child.setCode(projectReport.getDjNumber());
                child.setCreateBy(SecurityUtils.getUsername());
                systemFileService.insertSystemFile(child);
            }
        }
        return toAjax(projectReportService.insertProjectReport(projectReport));
    }

    /**
     * 修改产值提报
     */
    @PreAuthorize("@ss.hasPermi('system:projectReport:edit')")
    @Log(title = "产值提报", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProjectReport projectReport)
    {
        if(projectReport.getStatus()>0){
            return toAjaxByError("禁止修改!");
        }
        projectReport.setUpdateBy(SecurityUtils.getUsername());
        if(projectReport.getRows()!=null&&projectReport.getRows()!="") {
            List<ProjectReportChild> childList = JSONArray.parseArray(projectReport.getRows(), ProjectReportChild.class);
            for (ProjectReportChild child : childList) {
                if (child.getId() != null) {
                    child.setDjNumber(projectReport.getDjNumber());
                    child.setUpdateBy(SecurityUtils.getUsername());
                    projectReportChildService.updateProjectReportChild(child);
                } else {
                    child.setCreateBy(SecurityUtils.getUsername());
                    child.setDjNumber(projectReport.getDjNumber());
                    child.setCreateTime(DateUtils.getNowDate());
                    projectReportChildService.insertProjectReportChild(child);
                }
            }
        }
        //插入附件
        if(projectReport.getFileRows()!=null&&projectReport.getFileRows()!="") {
            List<SystemFile> childList = JSONArray.parseArray(projectReport.getFileRows(), SystemFile.class);
            for (SystemFile child : childList) {
                if (child.getId() != null) {

                }else{
                    child.setCode(projectReport.getDjNumber());
                    child.setCreateBy(SecurityUtils.getUsername());
                    systemFileService.insertSystemFile(child);
                }
            }
        }
        return toAjax(projectReportService.updateProjectReport(projectReport));
    }


    /**
     * 提交产值提报
     */
    @PreAuthorize("@ss.hasPermi('system:projectReport:effect')")
    @Log(title = "提交产值提报", businessType = BusinessType.EFFECT)
    @DeleteMapping("/effect/{ids}")
    public AjaxResult effect(@PathVariable Integer[] ids)
    {
        //查询审批流程
        FlowInfo flowInfo=new FlowInfo();
        //判断是否注册用户
        SysUser user=SecurityUtils.getLoginUser().getUser();
        if(user.getCreateBy().equals("admin")){
            flowInfo.setFlowNo("CZTB001"+SecurityUtils.getUsername());
        }else{
            flowInfo.setFlowNo("CZTB001"+user.getCreateBy());
        }
        flowInfo.setStatus(1);
        List<FlowInfo> list = flowInfoService.selectFlowInfoList(flowInfo);
        //查询审批节点
        FlowNode node=new FlowNode();
        node.setFlowNo(flowInfo.getFlowNo());
        List<FlowNode> nodeList = flowNodeService.selectFlowNodeList(node);
        for(Integer id:ids){
            ProjectReport info=projectReportService.selectProjectReportById(id);
            if(info.getStatus()>0){
                continue;
            }
            //判断是否审批 如不审批则直接生效
            if(info.getIsSp()==0){
                //直接生效
                info.setStatus(2);
                projectReportService.updateProjectReport(info);
            }else{
                if(list!=null&&list.size()>0){
                    //添加流程号
                    info.setFlowNo(list.get(0).getFlowNo());
                    //状态修改为待审核
                    info.setStatus(1);
                    //添加一级节点
                    info.setNodeNo(1);
                    projectReportService.updateProjectReport(info);
                    //删除历史流程 即修改历史流程状态
                    flowAuditService.updateFlowAuditByHistory(info.getDjNumber());
                    //添加单据流程环节
                    for(FlowNode flowNode:nodeList){
                        FlowAudit audit=new FlowAudit();
                        //是否历史流程 0 否 -1 是
                        audit.setFlowStatus(0);
                        audit.setDjId(info.getDjNumber());
                        audit.setNodeNo(flowNode.getNodeNo());
                        audit.setCreateBy(SecurityUtils.getUsername());
                        audit.setPrId(flowNode.getPrId());
                        audit.setPrName(flowNode.getPrName());
                        audit.setStatus(0);
                        audit.setIsEnd(flowNode.getIsEnd());
                        flowAuditService.insertFlowAudit(audit);
                    }
                }else{
                    continue;
                }
            }
        }
        flowInfo=null;
        node=null;
        return toAjaxBySuccess("提交成功!");
    }

    /**
     * 取消提交产值提报
     */
    @PreAuthorize("@ss.hasPermi('system:projectReport:cancel')")
    @Log(title = "取消提交产值提报", businessType = BusinessType.CANCEL)
    @DeleteMapping("/cancel/{ids}")
    public AjaxResult cancel(@PathVariable Integer[] ids)
    {
        for(Integer id:ids){
            ProjectReport info=projectReportService.selectProjectReportById(id);
            if(info.getStatus()==0){
                continue;
            }
            //判断是否审批 如不审批则直接取消
            if(info.getIsSp()==0){
                info.setStatus(0);
                info.setFlowNo("-1");
                info.setNodeNo(0);
            }else{
                //判断是否审批中
                if(info.getNodeNo()==1){
                    //直接取消
                    info.setStatus(0);
                    info.setFlowNo("-1");
                    info.setNodeNo(0);
                    //清空单据流程环节
                    flowAuditService.deleteFlowAuditByDjId(info.getDjNumber());
                }else{
                    //审批中跳过
                    continue;
                }
            }
            projectReportService.updateProjectReport(info);
        }
        return toAjaxBySuccess("取消成功!");
    }

    /**
     * 审核产值提报
     */
    @PreAuthorize("@ss.hasPermi('system:projectReport:examine')")
    @Log(title = "审核产值提报", businessType = BusinessType.UPDATE)
    @PutMapping("/examine")
    public AjaxResult examine(@RequestBody FlowAudit flowAudit)
    {
        boolean lag=false;
        //获取当前节点信息
        FlowAudit item=flowAuditService.selectFlowAuditNoAndDjId(flowAudit.getDjId(),flowAudit.getNodeNo());
        if(item.getStatus()>0){
            return toAjaxByError("重复审批!");
        }
        //判断是否退回
        if(flowAudit.getStatus()==2){
            //状态
            item.setStatus(2);
            //审核意见
            item.setAuditInfo(flowAudit.getAuditInfo());
            //审核时间
            item.setAuditTime(DateUtils.getTime());
            //审核人员
            item.setUserName(SecurityUtils.getUsername());
            //修改节点状态
            flowAuditService.updateFlowAudit(item);
            //修改单据状态为被退回
            projectReportService.updatetProjectReportStatusOrNodeNo(flowAudit.getDjId(), -1, 1);
        }else {
            //允许结束
            if (item.getIsEnd() == 1) {
                lag = true;
            } else {
                //查询末级节点
                int nodeNo = flowAuditService.getEndNode(flowAudit.getDjId());
                //末级结束
                if (nodeNo == item.getNodeNo()) {
                    lag = true;
                } else {
                    lag = false;
                }
            }
            //状态
            item.setStatus(flowAudit.getStatus());
            //是否历史流程 0 否 -1 是
            item.setFlowStatus(0);
            //审核意见
            item.setAuditInfo(flowAudit.getAuditInfo());
            //审核时间
            item.setAuditTime(DateUtils.getTime());
            //审核人员
            item.setUserName(SecurityUtils.getUsername());
            //修改节点状态
            flowAuditService.updateFlowAudit(item);
            //修改单据下一级节点
            projectReportService.updatetProjectReportStatusOrNodeNo(flowAudit.getDjId(), flowAudit.getNodeNo() + 1, 0);
            //流程结束
            if (lag) {
                //修改单据状态为已生效
                projectReportService.updatetProjectReportStatusOrNodeNo(flowAudit.getDjId(), 2, 1);
            }
        }
        return toAjaxBySuccess("审批成功!");
    }

    /**
     * 取消审核产值提报
     */
    @PreAuthorize("@ss.hasPermi('system:projectReport:cancelAudit')")
    @Log(title = "取消审核产值提报", businessType = BusinessType.CANCEL)
    @DeleteMapping("/cancelAudit/{djIds}/{nodeNos}")
    public AjaxResult remove(@PathVariable String[] djIds,@PathVariable Integer[] nodeNos)
    {
        for(int i=0;i<djIds.length;i++){
            boolean lag=false;
            //获取上级节点信息
            FlowAudit item=flowAuditService.selectFlowAuditNoAndDjId(djIds[i],nodeNos[i]-1);
            if(item.getStatus()==0){
                continue;
            }
            //允许结束
            if(item.getIsEnd()==1){
                lag=true;
            }else{
                //查询末级节点
                int nodeNo=flowAuditService.getEndNode(djIds[i]);
                //末级结束
                if(nodeNo==item.getNodeNo()){
                    lag=true;
                }else{
                    lag=false;
                }
            }
            //状态
            item.setFlowStatus(0);
            //状态
            item.setStatus(0);
            //审核意见
            item.setAuditInfo(" ");
            //审核时间
            item.setAuditTime(" ");
            //审核人员
            item.setUserName(" ");
            //回退节点状态
            flowAuditService.updateFlowAudit(item);
            //修改单据上一级节点
            projectReportService.updatetProjectReportStatusOrNodeNo(djIds[i],(nodeNos[i]-1),0);
            //如果已经生效则改变状态为待审核
            if(lag){
                //修改单据状态为待审核
                projectReportService.updatetProjectReportStatusOrNodeNo(djIds[i],1,1);
            }
        }
        return toAjaxBySuccess("取消成功!");
    }





    /**
     * 删除产值提报
     */
    @PreAuthorize("@ss.hasPermi('system:projectReport:remove')")
    @Log(title = "产值提报", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        for(Integer id:ids){
            ProjectReport info=projectReportService.selectProjectReportById(id);
            if(info.getStatus()>0){
                return toAjaxByError("已生效禁止删除");
            }
        }
        projectReportChildService.deleteProjectReportChildByPIds(ids);
        return toAjax(projectReportService.deleteProjectReportByIds(ids));
    }
}
