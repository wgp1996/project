package com.ruoyi.project.system.controller;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.project.system.domain.*;
import com.ruoyi.project.system.service.IFlowAuditService;
import com.ruoyi.project.system.service.IFlowNodeService;
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
import com.ruoyi.project.system.service.IFlowInfoService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 流程表Controller
 * 
 * @author ruoyi
 * @date 2021-01-25
 */
@RestController
@RequestMapping("/system/flowInfo")
public class FlowInfoController extends BaseController
{
    @Autowired
    private IFlowInfoService flowInfoService;
    @Autowired
    private IFlowNodeService flowNodeService;
    @Autowired
    private IFlowAuditService flowAuditService;

    /**
     * 查询审批流程列表
     */
    @GetMapping("/flowList")
    public TableDataInfo flowList(FlowAudit flowAudit)
    {
        List<FlowAudit> list=null;
        if(flowAudit.getDjId()!=""&&flowAudit.getDjId()!=null&&!"".equals(flowAudit.getDjId())){
            list = flowAuditService.selectFlowAuditList(flowAudit);
            for(FlowAudit item:list){
                if(item.getAuditTime()==null||item.getAuditTime()==" "){
                    item.setAuditTime("");
                }
                if(item.getStatusName()==null||item.getStatusName()==" "){
                    item.setStatusName("未审核");
                }
            }
        }
        return getDataTable(list);
    }

    /**
     * 查询流程表列表
     */
    @PreAuthorize("@ss.hasPermi('system:flowInfo:list')")
    @GetMapping("/list")
    @DataScope(deptAlias = "d", userAlias = "u")
    public TableDataInfo list(FlowInfo flowInfo)
    {
        startPage();
        List<FlowInfo> list = flowInfoService.selectFlowInfoList(flowInfo);
        for(FlowInfo info:list){
            FlowNode node=new FlowNode();
            node.setFlowNo(info.getFlowNo());
            info.setChildrenList(flowNodeService.selectFlowNodeList(node));
            node=null;
        }
        return getDataTable(list);
    }

    /**
     * 导出流程表列表
     */
    @PreAuthorize("@ss.hasPermi('system:flowInfo:export')")
    @Log(title = "流程表", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(FlowInfo flowInfo)
    {
        List<FlowInfo> list = flowInfoService.selectFlowInfoList(flowInfo);
        ExcelUtil<FlowInfo> util = new ExcelUtil<FlowInfo>(FlowInfo.class);
        return util.exportExcel(list, "flowInfo");
    }

    /**
     * 获取流程表详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:flowInfo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(flowInfoService.selectFlowInfoById(id));
    }

    /**
     * 新增流程表
     */
    @PreAuthorize("@ss.hasPermi('system:flowInfo:add')")
    @Log(title = "流程表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FlowInfo flowInfo)
    {
        if(flowInfo.getRows()==""){
            return  toAjaxByError("节点明细不能为空!");
        }
        int result=flowInfoService.checkFlow(flowInfo.getFlowNo(),-1);
        if(result>0){
            return toAjaxByError("流程重复!");
        }
        flowInfo.setFlowNo(flowInfo.getFlowNo()+SecurityUtils.getUsername());
        flowInfo.setStatus(0);
        //flowInfo.setFlowNo(StringUtils.getRandomCode("SP"));
        //插入审批人员
        if(flowInfo.getRows()!=null&&flowInfo.getRows()!="") {
            List<FlowNode> childList = JSONArray.parseArray(flowInfo.getRows(), FlowNode.class);
            int i=1;
            for (FlowNode child : childList) {
                //child.setNodeNo(StringUtils.getRandomCode("NO"));
                child.setNodeNo(i);
                child.setCreateBy(SecurityUtils.getUsername());
                child.setNodeNum(i+"");
                child.setFlowNo(flowInfo.getFlowNo());
                i++;
                flowNodeService.insertFlowNode(child);
            }
        }
        return toAjax(flowInfoService.insertFlowInfo(flowInfo));
    }

    /**
     * 修改流程表
     */
    @PreAuthorize("@ss.hasPermi('system:flowInfo:edit')")
    @Log(title = "流程表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FlowInfo flowInfo)
    {
        if(flowInfo.getStatus()>0){
            return toAjaxByError("流程已启动,禁止修改!");
        }
        if(flowInfo.getRows()==""){
            return  toAjaxByError("节点明细不能为空!");
        }
        int result=flowInfoService.checkFlow(flowInfo.getFlowNo(),flowInfo.getId());
        if(result>0){
            return toAjaxByError("流程重复!");
        }
       // flowInfo.setStatus(0);
       // flowInfo.setFlowNo(StringUtils.getRandomCode("SP"));
        //插入审批人员
        if(flowInfo.getRows()!=null&&flowInfo.getRows()!="") {
            List<FlowNode> childList = JSONArray.parseArray(flowInfo.getRows(), FlowNode.class);
            int i=1;
            for (FlowNode child : childList) {
                if (child.getId() != null) {
                    child.setUpdateBy(SecurityUtils.getUsername());
                    child.setNodeNum(i+"");
                    child.setFlowNo(flowInfo.getFlowNo());
                    flowNodeService.updateFlowNode(child);
                }else{
                    child.setNodeNo(i);
                    child.setCreateBy(SecurityUtils.getUsername());
                    child.setNodeNum(i+"");
                    child.setFlowNo(flowInfo.getFlowNo());
                    flowNodeService.insertFlowNode(child);
                }
                i++;
            }
        }

        return toAjax(flowInfoService.updateFlowInfo(flowInfo));
    }

    /**
     * 删除流程表
     */
    @PreAuthorize("@ss.hasPermi('system:flowInfo:remove')")
    @Log(title = "流程表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        for(Integer id:ids){
            FlowInfo flowInfo=flowInfoService.selectFlowInfoById(id);
            if(flowInfo.getStatus()>0){
                return toAjaxByError(flowInfo.getFlowName()+":流程已启动,禁止删除!");
            }
        }
        //删除节点
        flowNodeService.deleteFlowNodeByPid(ids);
        return toAjax(flowInfoService.deleteFlowInfoByIds(ids));
    }

    /**
     * 启用流程表
     */
    @PreAuthorize("@ss.hasPermi('system:flowInfo:effect')")
    @Log(title = "流程表", businessType = BusinessType.EFFECT)
    @DeleteMapping("/effect/{ids}")
    public AjaxResult effect(@PathVariable Integer[] ids)
    {
        for(Integer id:ids){
            FlowInfo flowInfo=flowInfoService.selectFlowInfoById(id);
            flowInfo.setStatus(1);
            flowInfoService.updateFlowInfo(flowInfo);
            flowInfo=null;
        }
        return toAjaxBySuccess("生效成功!");
    }

    /**
     * 取消启用流程表
     */
    @PreAuthorize("@ss.hasPermi('system:flowInfo:cancel')")
    @Log(title = "流程表", businessType = BusinessType.CANCEL)
    @DeleteMapping("/cancel/{ids}")
    public AjaxResult cancel(@PathVariable Integer[] ids)
    {
        for(Integer id:ids){
            FlowInfo flowInfo=flowInfoService.selectFlowInfoById(id);
            flowInfo.setStatus(0);
            flowInfoService.updateFlowInfo(flowInfo);
            flowInfo=null;
        }
        return toAjaxBySuccess("生效成功!");
    }
}
