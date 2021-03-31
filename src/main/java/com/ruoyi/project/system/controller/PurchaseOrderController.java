package com.ruoyi.project.system.controller;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.project.system.domain.*;
import com.ruoyi.project.system.domain.PurchaseOrderChild;
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
 * 采购订单Controller
 * 
 * @author ruoyi
 * @date 2021-03-30
 */
@RestController
@RequestMapping("/system/purchaseOrder")
public class PurchaseOrderController extends BaseController
{
    @Autowired
    private IPurchaseOrderService purchaseOrderService;
    @Autowired
    private ISystemFileService systemFileService;
    @Autowired
    private IFlowInfoService flowInfoService;
    @Autowired
    private IFlowNodeService flowNodeService;
    @Autowired
    private IFlowAuditService flowAuditService;
    @Autowired
    private IPurchaseOrderChildService purchaseOrderChildService;

    /**
     * 查询采购订单列表
     */
    @PreAuthorize("@ss.hasPermi('system:purchaseOrder:list')")
    @GetMapping("/list")
    @DataScope(deptAlias = "d", userAlias = "u")
    public TableDataInfo list(PurchaseOrder purchaseOrder)
    {
        startPage();
        List<PurchaseOrder> list = purchaseOrderService.selectPurchaseOrderList(purchaseOrder);
        return getDataTable(list);
    }

    /**
     * 入库单选择采购订单列表
     */
    @GetMapping("/wareSelectList")
    public TableDataInfo wareSelectList(PurchaseOrder purchaseOrder)
    {
        startPage();
        List<PurchaseOrderChild> list = purchaseOrderChildService.selectPurchaseOrderListByWave(purchaseOrder.getKhCode(),SecurityUtils.getUsername());
        return getDataTable(list);
    }


    /**
     * 查询采购订单审核列表
     */
    @GetMapping("/shList")
    public TableDataInfo shList(PurchaseOrder purchaseOrder)
    {
        startPage();
        String userId=SecurityUtils.getUsername();
        String roleId=SecurityUtils.getLoginUser().getUser().getRoles().get(0).getRoleId()+"";
        purchaseOrder.setUserId(userId);
        purchaseOrder.setRoleId(roleId);
        List<PurchaseOrder> list = purchaseOrderService.selectPurchaseOrderShList(purchaseOrder);
        return getDataTable(list);
    }


    /**
     * 导出采购订单列表
     */
    @PreAuthorize("@ss.hasPermi('system:purchaseOrder:export')")
    @Log(title = "采购订单", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(PurchaseOrder purchaseOrder)
    {
        List<PurchaseOrder> list = purchaseOrderService.selectPurchaseOrderList(purchaseOrder);
        ExcelUtil<PurchaseOrder> util = new ExcelUtil<PurchaseOrder>(PurchaseOrder.class);
        return util.exportExcel(list, "purchaseOrder");
    }

    /**
     * 获取采购订单详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:purchaseOrder:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(purchaseOrderService.selectPurchaseOrderById(id));
    }

    /**
     * 新增采购订单
     */
    @PreAuthorize("@ss.hasPermi('system:purchaseOrder:add')")
    @Log(title = "采购订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PurchaseOrder purchaseOrder)
    {
        purchaseOrder.setCreateBy(SecurityUtils.getUsername());
        purchaseOrder.setDjNumber(StringUtils.getRandomCode("PO"));
        if(purchaseOrder.getRows()!=null&&purchaseOrder.getRows()!="") {
            List<PurchaseOrderChild> childList = JSONArray.parseArray(purchaseOrder.getRows(), PurchaseOrderChild.class);
            for (PurchaseOrderChild child : childList) {
                child.setCreateBy(SecurityUtils.getUsername());
                child.setDjNumber(purchaseOrder.getDjNumber());
                child.setCreateTime(DateUtils.getNowDate());
                purchaseOrderChildService.insertPurchaseOrderChild(child);
            }
        }
        //插入附件
        if(purchaseOrder.getFileRows()!=null&&purchaseOrder.getFileRows()!="") {
            List<SystemFile> childList = JSONArray.parseArray(purchaseOrder.getFileRows(), SystemFile.class);
            for (SystemFile child : childList) {
                child.setCode(purchaseOrder.getDjNumber());
                child.setCreateBy(SecurityUtils.getUsername());
                systemFileService.insertSystemFile(child);
            }
        }
        return toAjax(purchaseOrderService.insertPurchaseOrder(purchaseOrder));
    }

    /**
     * 修改采购订单
     */
    @PreAuthorize("@ss.hasPermi('system:purchaseOrder:edit')")
    @Log(title = "采购订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PurchaseOrder purchaseOrder)
    {
        if(purchaseOrder.getStatus()>0){
            return toAjaxByError("禁止修改!");
        }
        purchaseOrder.setUpdateBy(SecurityUtils.getUsername());
        if(purchaseOrder.getRows()!=null&&purchaseOrder.getRows()!="") {
            List<PurchaseOrderChild> childList = JSONArray.parseArray(purchaseOrder.getRows(), PurchaseOrderChild.class);
            for (PurchaseOrderChild child : childList) {
                if (child.getId() != null) {
                    child.setDjNumber(purchaseOrder.getDjNumber());
                    child.setUpdateBy(SecurityUtils.getUsername());
                    purchaseOrderChildService.updatePurchaseOrderChild(child);
                } else {
                    child.setCreateBy(SecurityUtils.getUsername());
                    child.setDjNumber(purchaseOrder.getDjNumber());
                    child.setCreateTime(DateUtils.getNowDate());
                    purchaseOrderChildService.insertPurchaseOrderChild(child);
                }
            }
        }
        //插入附件
        if(purchaseOrder.getFileRows()!=null&&purchaseOrder.getFileRows()!="") {
            List<SystemFile> childList = JSONArray.parseArray(purchaseOrder.getFileRows(), SystemFile.class);
            for (SystemFile child : childList) {
                if (child.getId() != null) {

                }else{
                    child.setCode(purchaseOrder.getDjNumber());
                    child.setCreateBy(SecurityUtils.getUsername());
                    systemFileService.insertSystemFile(child);
                }
            }
        }
        return toAjax(purchaseOrderService.updatePurchaseOrder(purchaseOrder));
    }

    /**
     * 审核时修改采购订单
     */
    @Log(title = "审核时修改采购订单", businessType = BusinessType.UPDATE)
    @PutMapping("/shEdit")
    public AjaxResult shEdit(@RequestBody PurchaseOrder purchaseOrder)
    {
        purchaseOrder.setUpdateBy(SecurityUtils.getUsername());
        if(purchaseOrder.getRows()!=null&&purchaseOrder.getRows()!="") {
            List<PurchaseOrderChild> childList = JSONArray.parseArray(purchaseOrder.getRows(), PurchaseOrderChild.class);
            for (PurchaseOrderChild child : childList) {
                if (child.getId() != null) {
                    child.setDjNumber(purchaseOrder.getDjNumber());
                    child.setUpdateBy(SecurityUtils.getUsername());
                    purchaseOrderChildService.updatePurchaseOrderChild(child);
                }
            }
        }
        return toAjax(purchaseOrderService.updatePurchaseOrder(purchaseOrder));
    }


    /**
     * 提交采购订单
     */
    @PreAuthorize("@ss.hasPermi('system:purchaseOrder:effect')")
    @Log(title = "提交采购订单", businessType = BusinessType.EFFECT)
    @DeleteMapping("/effect/{ids}")
    public AjaxResult effect(@PathVariable Integer[] ids)
    {
        //查询审批流程
        FlowInfo flowInfo=new FlowInfo();
        flowInfo.setFlowNo("CGDD001"+SecurityUtils.getUsername());
        flowInfo.setStatus(1);
        List<FlowInfo> list = flowInfoService.selectFlowInfoList(flowInfo);
        //查询审批节点
        FlowNode node=new FlowNode();
        node.setFlowNo(flowInfo.getFlowNo());
        List<FlowNode> nodeList = flowNodeService.selectFlowNodeList(node);
        for(Integer id:ids){
            PurchaseOrder info=purchaseOrderService.selectPurchaseOrderById(id);
            if(info.getStatus()>0){
                continue;
            }
            //判断是否审批 如不审批则直接生效
            if(info.getIsSp()==0){
                //直接生效
                info.setStatus(2);
                purchaseOrderService.updatePurchaseOrder(info);
            }else{
                if(list!=null&&list.size()>0){
                    //添加流程号
                    info.setFlowNo(list.get(0).getFlowNo());
                    //状态修改为待审核
                    info.setStatus(1);
                    //添加一级节点
                    info.setNodeNo(1);
                    purchaseOrderService.updatePurchaseOrder(info);
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
     * 取消提交采购订单
     */
    @PreAuthorize("@ss.hasPermi('system:purchaseOrder:cancel')")
    @Log(title = "取消提交采购订单", businessType = BusinessType.CANCEL)
    @DeleteMapping("/cancel/{ids}")
    public AjaxResult cancel(@PathVariable Integer[] ids)
    {
        for(Integer id:ids){
            PurchaseOrder info=purchaseOrderService.selectPurchaseOrderById(id);
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
            purchaseOrderService.updatePurchaseOrder(info);
        }
        return toAjaxBySuccess("取消成功!");
    }



    /**
     * 审核采购订单
     */
    @PreAuthorize("@ss.hasPermi('system:purchaseOrder:examine')")
    @Log(title = "审核采购订单", businessType = BusinessType.UPDATE)
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
            purchaseOrderService.updatetPurchaseOrderStatusOrNodeNo(flowAudit.getDjId(), -1, 1);
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
            purchaseOrderService.updatetPurchaseOrderStatusOrNodeNo(flowAudit.getDjId(), flowAudit.getNodeNo() + 1, 0);
            //流程结束
            if (lag) {
                //修改单据状态为已生效
                purchaseOrderService.updatetPurchaseOrderStatusOrNodeNo(flowAudit.getDjId(), 2, 1);
            }
        }
        return toAjaxBySuccess("审批成功!");
    }

    /**
     * 取消审核采购订单
     */
    @PreAuthorize("@ss.hasPermi('system:purchaseOrder:cancelAudit')")
    @Log(title = "取消审核采购订单", businessType = BusinessType.CANCEL)
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
            purchaseOrderService.updatetPurchaseOrderStatusOrNodeNo(djIds[i],(nodeNos[i]-1),0);
            //如果已经生效则改变状态为待审核
            if(lag){
                //修改单据状态为待审核
                purchaseOrderService.updatetPurchaseOrderStatusOrNodeNo(djIds[i],1,1);
            }
        }
        return toAjaxBySuccess("取消成功!");
    }




    /**
     * 删除采购订单
     */
    @PreAuthorize("@ss.hasPermi('system:purchaseOrder:remove')")
    @Log(title = "采购订单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        for(Integer id:ids){
            PurchaseOrder info=purchaseOrderService.selectPurchaseOrderById(id);
            if(info.getStatus()>0){
                return toAjaxByError("已生效禁止删除");
            }
        }
        purchaseOrderChildService.deletePurchaseOrderChildByPIds(ids);
        return toAjax(purchaseOrderService.deletePurchaseOrderByIds(ids));
    }
}
