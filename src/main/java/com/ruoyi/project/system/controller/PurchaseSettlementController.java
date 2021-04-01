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
 * 结算申请单Controller
 * 
 * @author ruoyi
 * @date 2021-04-01
 */
@RestController
@RequestMapping("/system/purchaseSettlement")
public class PurchaseSettlementController extends BaseController
{
    @Autowired
    private IPurchaseSettlementService purchaseSettlementService;
    @Autowired
    private ISystemFileService systemFileService;
    @Autowired
    private IFlowInfoService flowInfoService;
    @Autowired
    private IFlowNodeService flowNodeService;
    @Autowired
    private IFlowAuditService flowAuditService;
    @Autowired
    private IPurchaseSettlementChildService purchaseSettlementChildService;
    /**
     * 查询结算申请单列表
     */
    @PreAuthorize("@ss.hasPermi('system:purchaseSettlement:list')")
    @GetMapping("/list")
    @DataScope(deptAlias = "d", userAlias = "u")
    public TableDataInfo list(PurchaseSettlement purchaseSettlement)
    {
        startPage();
        List<PurchaseSettlement> list = purchaseSettlementService.selectPurchaseSettlementList(purchaseSettlement);
        return getDataTable(list);
    }

    /**
     * 导出结算申请单列表
     */
    @PreAuthorize("@ss.hasPermi('system:purchaseSettlement:export')")
    @Log(title = "结算申请单", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(PurchaseSettlement purchaseSettlement)
    {
        List<PurchaseSettlement> list = purchaseSettlementService.selectPurchaseSettlementList(purchaseSettlement);
        ExcelUtil<PurchaseSettlement> util = new ExcelUtil<PurchaseSettlement>(PurchaseSettlement.class);
        return util.exportExcel(list, "purchaseSettlement");
    }


    /**
     * 查询采购结算审核列表
     */
    @GetMapping("/shList")
    public TableDataInfo shList(PurchaseSettlement PurchaseSettlement)
    {
        startPage();
        String userId= SecurityUtils.getUsername();
        String roleId=SecurityUtils.getLoginUser().getUser().getRoles().get(0).getRoleId()+"";
        PurchaseSettlement.setUserId(userId);
        PurchaseSettlement.setRoleId(roleId);
        List<PurchaseSettlement> list = purchaseSettlementService.selectPurchaseSettlementShList(PurchaseSettlement);
        return getDataTable(list);
    }

    /**
     * 获取结算申请单详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:purchaseSettlement:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(purchaseSettlementService.selectPurchaseSettlementById(id));
    }

    /**
     * 新增结算申请单
     */
    @PreAuthorize("@ss.hasPermi('system:purchaseSettlement:add')")
    @Log(title = "结算申请单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PurchaseSettlement purchaseSettlement)
    {
        purchaseSettlement.setCreateBy(SecurityUtils.getUsername());
        purchaseSettlement.setDjNumber(StringUtils.getRandomCode("RS"));
        if(purchaseSettlement.getRows()!=null&&purchaseSettlement.getRows()!="") {
            List<PurchaseSettlementChild> childList = JSONArray.parseArray(purchaseSettlement.getRows(), PurchaseSettlementChild.class);
            for (PurchaseSettlementChild child : childList) {
                child.setCreateBy(SecurityUtils.getUsername());
                child.setDjNumber(purchaseSettlement.getDjNumber());
                child.setCreateTime(DateUtils.getNowDate());
                purchaseSettlementChildService.insertPurchaseSettlementChild(child);
            }
        }
        //插入附件
        if(purchaseSettlement.getFileRows()!=null&&purchaseSettlement.getFileRows()!="") {
            List<SystemFile> childList = JSONArray.parseArray(purchaseSettlement.getFileRows(), SystemFile.class);
            for (SystemFile child : childList) {
                child.setCode(purchaseSettlement.getDjNumber());
                child.setCreateBy(SecurityUtils.getUsername());
                systemFileService.insertSystemFile(child);
            }
        }
        return toAjax(purchaseSettlementService.insertPurchaseSettlement(purchaseSettlement));
    }

    /**
     * 修改结算申请单
     */
    @PreAuthorize("@ss.hasPermi('system:purchaseSettlement:edit')")
    @Log(title = "结算申请单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PurchaseSettlement purchaseSettlement)
    {
        if(purchaseSettlement.getStatus()>0){
            return toAjaxByError("禁止修改!");
        }
        purchaseSettlement.setUpdateBy(SecurityUtils.getUsername());
        if(purchaseSettlement.getRows()!=null&&purchaseSettlement.getRows()!="") {
            List<PurchaseSettlementChild> childList = JSONArray.parseArray(purchaseSettlement.getRows(), PurchaseSettlementChild.class);
            for (PurchaseSettlementChild child : childList) {
                if (child.getId() != null) {
                    child.setDjNumber(purchaseSettlement.getDjNumber());
                    child.setUpdateBy(SecurityUtils.getUsername());
                    purchaseSettlementChildService.updatePurchaseSettlementChild(child);
                } else {
                    child.setCreateBy(SecurityUtils.getUsername());
                    child.setDjNumber(purchaseSettlement.getDjNumber());
                    child.setCreateTime(DateUtils.getNowDate());
                    purchaseSettlementChildService.updatePurchaseSettlementChild(child);
                }
            }
        }
        //插入附件
        if(purchaseSettlement.getFileRows()!=null&&purchaseSettlement.getFileRows()!="") {
            List<SystemFile> childList = JSONArray.parseArray(purchaseSettlement.getFileRows(), SystemFile.class);
            for (SystemFile child : childList) {
                if (child.getId() != null) {

                }else{
                    child.setCode(purchaseSettlement.getDjNumber());
                    child.setCreateBy(SecurityUtils.getUsername());
                    systemFileService.insertSystemFile(child);
                }
            }
        }
        return toAjax(purchaseSettlementService.updatePurchaseSettlement(purchaseSettlement));
    }

    /**
     * 提交采购结算
     */
    @PreAuthorize("@ss.hasPermi('system:purchaseSettlement:effect')")
    @Log(title = "提交采购结算", businessType = BusinessType.EFFECT)
    @DeleteMapping("/effect/{ids}")
    public AjaxResult effect(@PathVariable Integer[] ids)
    {
        //查询审批流程
        FlowInfo flowInfo=new FlowInfo();
        flowInfo.setFlowNo("CGJS001"+SecurityUtils.getUsername());
        flowInfo.setStatus(1);
        List<FlowInfo> list = flowInfoService.selectFlowInfoList(flowInfo);
        //查询审批节点
        FlowNode node=new FlowNode();
        node.setFlowNo(flowInfo.getFlowNo());
        List<FlowNode> nodeList = flowNodeService.selectFlowNodeList(node);
        for(Integer id:ids){
            PurchaseSettlement info=purchaseSettlementService.selectPurchaseSettlementById(id);
            if(info.getStatus()>0){
                continue;
            }
            //判断是否审批 如不审批则直接生效
            if(info.getIsSp()==0){
                //直接生效
                info.setStatus(2);
                purchaseSettlementService.updatePurchaseSettlement(info);
            }else{
                if(list!=null&&list.size()>0){
                    //添加流程号
                    info.setFlowNo(list.get(0).getFlowNo());
                    //状态修改为待审核
                    info.setStatus(1);
                    //添加一级节点
                    info.setNodeNo(1);
                    purchaseSettlementService.updatePurchaseSettlement(info);
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
     * 取消提交采购结算
     */
    @PreAuthorize("@ss.hasPermi('system:purchaseSettlement:cancel')")
    @Log(title = "取消提交采购结算", businessType = BusinessType.CANCEL)
    @DeleteMapping("/cancel/{ids}")
    public AjaxResult cancel(@PathVariable Integer[] ids)
    {
        for(Integer id:ids){
            PurchaseSettlement info=purchaseSettlementService.selectPurchaseSettlementById(id);
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
            purchaseSettlementService.updatePurchaseSettlement(info);
        }
        return toAjaxBySuccess("取消成功!");
    }



    /**
     * 审核采购结算
     */
    @PreAuthorize("@ss.hasPermi('system:purchaseSettlement:examine')")
    @Log(title = "审核采购结算", businessType = BusinessType.UPDATE)
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
            purchaseSettlementService.updatetPurchaseSettlementStatusOrNodeNo(flowAudit.getDjId(), -1, 1);
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
            purchaseSettlementService.updatetPurchaseSettlementStatusOrNodeNo(flowAudit.getDjId(), flowAudit.getNodeNo() + 1, 0);
            //流程结束
            if (lag) {
                //修改单据状态为已生效
                purchaseSettlementService.updatetPurchaseSettlementStatusOrNodeNo(flowAudit.getDjId(), 2, 1);
            }
        }
        return toAjaxBySuccess("审批成功!");
    }

    /**
     * 取消审核采购结算
     */
    @PreAuthorize("@ss.hasPermi('system:purchaseSettlement:cancelAudit')")
    @Log(title = "取消审核采购结算", businessType = BusinessType.CANCEL)
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
            purchaseSettlementService.updatetPurchaseSettlementStatusOrNodeNo(djIds[i],(nodeNos[i]-1),0);
            //如果已经生效则改变状态为待审核
            if(lag){
                purchaseSettlementService.updatetPurchaseSettlementStatusOrNodeNo(djIds[i],1,1);
            }
        }
        return toAjaxBySuccess("取消成功!");
    }


    /**
     * 删除结算申请单
     */
    @PreAuthorize("@ss.hasPermi('system:purchaseSettlement:remove')")
    @Log(title = "结算申请单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        for(Integer id:ids){
            PurchaseSettlement info=purchaseSettlementService.selectPurchaseSettlementById(id);
            if(info.getStatus()>0){
                return toAjaxByError("已生效禁止删除");
            }
        }
        purchaseSettlementChildService.deletePurchaseSettlementChildByPIds(ids);
        return toAjax(purchaseSettlementService.deletePurchaseSettlementByIds(ids));
    }
}
