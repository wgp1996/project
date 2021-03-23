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
 * 立项申请Controller
 * 
 * @author ruoyi
 * @date 2021-03-18
 */
@RestController
@RequestMapping("/system/enginnerApply")
public class ProjectApplyController extends BaseController
{
    @Autowired
    private IProjectApplyService projectApplyService;
    @Autowired
    private IProjectApplyChildService projectApplyChildService;
    @Autowired
    private ISystemFileService systemFileService;
    @Autowired
    private IFlowInfoService flowInfoService;
    @Autowired
    private IFlowNodeService flowNodeService;
    @Autowired
    private IFlowAuditService flowAuditService;
    /**
     * 查询立项申请列表
     */
    @PreAuthorize("@ss.hasPermi('system:enginnerApply:list')")
    @DataScope(deptAlias = "d", userAlias = "u")
    @GetMapping("/list")
    public TableDataInfo list(ProjectApply projectApply)
    {
        startPage();
        List<ProjectApply> list = projectApplyService.selectProjectApplyList(projectApply);
        return getDataTable(list);
    }

    /**
     * 查询询立项申请审核列表
     */
    @GetMapping("/shList")
    public TableDataInfo shList(ProjectApply projectApply)
    {
        startPage();
        String userId=SecurityUtils.getUsername();
        String roleId=SecurityUtils.getLoginUser().getUser().getRoles().get(0).getRoleId()+"";
        projectApply.setUserId(userId);
        projectApply.setRoleId(roleId);
        List<ProjectApply> list = projectApplyService.selectProjectApplyShList(projectApply);
        return getDataTable(list);
    }

    /**
     * 导出立项申请列表
     */
    @PreAuthorize("@ss.hasPermi('system:enginnerApply:export')")
    @Log(title = "立项申请", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ProjectApply projectApply)
    {
        List<ProjectApply> list = projectApplyService.selectProjectApplyList(projectApply);
        ExcelUtil<ProjectApply> util = new ExcelUtil<ProjectApply>(ProjectApply.class);
        return util.exportExcel(list, "enginnerApply");
    }

    /**
     * 获取立项申请详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:enginnerApply:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(projectApplyService.selectProjectApplyById(id));
    }

    /**
     * 新增立项申请
     */
    @PreAuthorize("@ss.hasPermi('system:enginnerApply:add')")
    @Log(title = "立项申请", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProjectApply projectApply)
    {
        projectApply.setCreateBy(SecurityUtils.getUsername());
        projectApply.setDjNumber(StringUtils.getRandomCode("LS"));
        //正式编号
        if(projectApply.getIsFormal()==0){
            projectApply.setEngineerCode(StringUtils.getRandomCode("EG"));
        }
        if(projectApply.getRows()!=null&&projectApply.getRows()!="") {
            List<ProjectApplyChild> childList = JSONArray.parseArray(projectApply.getRows(), ProjectApplyChild.class);
            for (ProjectApplyChild child : childList) {
                child.setCreateBy(SecurityUtils.getUsername());
                child.setEnginnerCode(projectApply.getEngineerCode());
                child.setCreateTime(DateUtils.getNowDate());
                projectApplyChildService.insertProjectApplyChild(child);
            }
        }
        //插入附件
        if(projectApply.getFileRows()!=null&&projectApply.getFileRows()!="") {
            List<SystemFile> childList = JSONArray.parseArray(projectApply.getFileRows(), SystemFile.class);
            for (SystemFile child : childList) {
                child.setCode(projectApply.getEngineerCode());
                child.setCreateBy(SecurityUtils.getUsername());
                systemFileService.insertSystemFile(child);
            }
        }
        return toAjax(projectApplyService.insertProjectApply(projectApply));
    }

    /**
     * 修改立项申请
     */
    @PreAuthorize("@ss.hasPermi('system:enginnerApply:edit')")
    @Log(title = "立项申请", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProjectApply projectApply)
    {
        if(projectApply.getRows()!=null&&projectApply.getRows()!="") {
            List<ProjectApplyChild> childList = JSONArray.parseArray(projectApply.getRows(), ProjectApplyChild.class);
            for (ProjectApplyChild child : childList) {
                if (child.getId() != null) {
                    child.setEnginnerCode(projectApply.getEngineerCode());
                    child.setUpdateBy(SecurityUtils.getUsername());
                    projectApplyChildService.updateProjectApplyChild(child);
                } else {
                    child.setCreateBy(SecurityUtils.getUsername());
                    child.setEnginnerCode(projectApply.getEngineerCode());
                    child.setCreateTime(DateUtils.getNowDate());
                    projectApplyChildService.insertProjectApplyChild(child);
                }
            }
        }
        //插入附件
        if(projectApply.getFileRows()!=null&&projectApply.getFileRows()!="") {
            List<SystemFile> childList = JSONArray.parseArray(projectApply.getFileRows(), SystemFile.class);
            for (SystemFile child : childList) {
                if (child.getId() != null) {

                }else{
                    child.setCode(projectApply.getEngineerCode());
                    child.setCreateBy(SecurityUtils.getUsername());
                    systemFileService.insertSystemFile(child);
                }
            }
        }
        return toAjax(projectApplyService.updateProjectApply(projectApply));
    }

    /**
     * 删除立项申请
     */
    @PreAuthorize("@ss.hasPermi('system:enginnerApply:remove')")
    @Log(title = "立项申请", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        for(Long id:ids){
            ProjectApply apply=projectApplyService.selectProjectApplyById(id);
            if(apply.getStatus()>0){

            }
        }
        //删除项目部信息
        projectApplyChildService.deleteProjectApplyChildByPIds(ids);
        return toAjax(projectApplyService.deleteProjectApplyByIds(ids));
    }


    /**
     * 提交立项申请
     */
    @PreAuthorize("@ss.hasPermi('system:enginnerApply:effect')")
    @Log(title = "提交立项申请", businessType = BusinessType.EFFECT)
    @DeleteMapping("/effect/{ids}")
    public AjaxResult effect(@PathVariable Long[] ids)
    {
        //查询审批流程
        FlowInfo flowInfo=new FlowInfo();
        flowInfo.setFlowNo("LXSQ001"+SecurityUtils.getUsername());
        flowInfo.setStatus(1);
        List<FlowInfo> list = flowInfoService.selectFlowInfoList(flowInfo);
        //查询审批节点
        FlowNode node=new FlowNode();
        node.setFlowNo(flowInfo.getFlowNo());
        List<FlowNode> nodeList = flowNodeService.selectFlowNodeList(node);
        for(Long id:ids){
            ProjectApply info=projectApplyService.selectProjectApplyById(id);
            if(info.getStatus()>0){
                continue;
            }
            //判断是否审批 如不审批则直接生效
            if(info.getIsSp()==0){
                //直接生效
                info.setStatus((long) 2);
                projectApplyService.updateProjectApply(info);
            }else{
                if(list!=null&&list.size()>0){
                    //添加流程号
                    info.setFlowNo(list.get(0).getFlowNo());
                    //状态修改为待审核
                    info.setStatus((long) 1);
                    //添加一级节点
                    info.setNodeNo(1);
                    projectApplyService.updateProjectApply(info);
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
     * 取消提交立项申请
     */
    @PreAuthorize("@ss.hasPermi('system:enginnerApply:cancel')")
    @Log(title = "取消提交立项申请", businessType = BusinessType.CANCEL)
    @DeleteMapping("/cancel/{ids}")
    public AjaxResult cancel(@PathVariable Long[] ids)
    {
        for(Long id:ids){
            ProjectApply info=projectApplyService.selectProjectApplyById(id);
            if(info.getStatus()==0){
                continue;
            }
            //判断是否审批 如不审批则直接取消
            if(info.getIsSp()==0){
                info.setStatus((long) 0);
                info.setFlowNo("-1");
                info.setNodeNo(0);
            }else{
                //判断是否审批中
                if(info.getNodeNo()==1){
                    //直接取消
                    info.setStatus((long) 0);
                    info.setFlowNo("-1");
                    info.setNodeNo(0);
                    //清空单据流程环节
                    flowAuditService.deleteFlowAuditByDjId(info.getDjNumber());
                }else{
                    //审批中跳过
                    continue;
                }
            }
            projectApplyService.updateProjectApply(info);
        }
        return toAjaxBySuccess("取消成功!");
    }


    /**
     * 审核立项申请
     */
    @PreAuthorize("@ss.hasPermi('system:enginnerApply:examine')")
    @Log(title = "审核立项申请", businessType = BusinessType.UPDATE)
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
            projectApplyService.updateProjectApplyStatusOrNodeNo(flowAudit.getDjId(), -1, 1);
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
            projectApplyService.updateProjectApplyStatusOrNodeNo(flowAudit.getDjId(), flowAudit.getNodeNo() + 1, 0);
            //流程结束
            if (lag) {
                //修改单据状态为已生效
                projectApplyService.updateProjectApplyStatusOrNodeNo(flowAudit.getDjId(), 2, 1);
            }
        }
        return toAjaxBySuccess("审批成功!");
    }

    /**
     * 取消审核立项申请
     */
    @PreAuthorize("@ss.hasPermi('system:enginnerApply:cancelAudit')")
    @Log(title = "取消审核立项申请", businessType = BusinessType.CANCEL)
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
            projectApplyService.updateProjectApplyStatusOrNodeNo(djIds[i],(nodeNos[i]-1),0);
            //如果已经生效则改变状态为待审核
            if(lag){
                //修改单据状态为待审核
                projectApplyService.updateProjectApplyStatusOrNodeNo(djIds[i],1,1);
            }
        }
        return toAjaxBySuccess("取消成功!");
    }
}
