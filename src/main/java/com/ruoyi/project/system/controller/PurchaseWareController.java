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
 * 采购入库Controller
 * 
 * @author ruoyi
 * @date 2021-03-30
 */
@RestController
@RequestMapping("/system/purchaseWare")
public class PurchaseWareController extends BaseController
{
    @Autowired
    private IPurchaseWareService purchaseWareService;
    @Autowired
    private ISystemFileService systemFileService;
    @Autowired
    private IFlowInfoService flowInfoService;
    @Autowired
    private IFlowNodeService flowNodeService;
    @Autowired
    private IFlowAuditService flowAuditService;
    @Autowired
    private IPurchaseWareChildService PurchaseWareChildService;
    @Autowired
    private IStockInfoService stockInfoService;

    /**
     * 查询采购入库列表
     */
    @PreAuthorize("@ss.hasPermi('system:purchaseWare:list')")
    @GetMapping("/list")
    @DataScope(deptAlias = "d", userAlias = "u")
    public TableDataInfo list(PurchaseWare purchaseWare)
    {
        startPage();
        List<PurchaseWare> list = purchaseWareService.selectPurchaseWareList(purchaseWare);
        return getDataTable(list);
    }

    /**
     * 采购结算单选择入库单列表
     */
    @GetMapping("/settlementSelectList")
    public TableDataInfo wareSelectList(PurchaseWareChild purchaseWareChild)
    {
        startPage();
        purchaseWareChild.setCreateBy(SecurityUtils.getUsername());
        List<PurchaseWareChild> list = PurchaseWareChildService.selectPurchaseWareListBySettlement(purchaseWareChild);
        return getDataTable(list);
    }


    /**
     * 查询采购入库审核列表
     */
    @GetMapping("/shList")
    public TableDataInfo shList(PurchaseWare PurchaseWare)
    {
        startPage();
        String userId= SecurityUtils.getUsername();
        String roleId=SecurityUtils.getLoginUser().getUser().getRoles().get(0).getRoleId()+"";
        PurchaseWare.setUserId(userId);
        PurchaseWare.setRoleId(roleId);
        List<PurchaseWare> list = purchaseWareService.selectPurchaseWareShList(PurchaseWare);
        return getDataTable(list);
    }


    /**
     * 导出采购入库列表
     */
    @PreAuthorize("@ss.hasPermi('system:purchaseWare:export')")
    @Log(title = "采购入库", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(PurchaseWare purchaseWare)
    {
        List<PurchaseWare> list = purchaseWareService.selectPurchaseWareList(purchaseWare);
        ExcelUtil<PurchaseWare> util = new ExcelUtil<PurchaseWare>(PurchaseWare.class);
        return util.exportExcel(list, "purchaseWare");
    }

    /**
     * 获取采购入库详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:purchaseWare:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(purchaseWareService.selectPurchaseWareById(id));
    }

    /**
     * 新增采购入库
     */
    @PreAuthorize("@ss.hasPermi('system:purchaseWare:add')")
    @Log(title = "采购入库", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PurchaseWare purchaseWare)
    {
        purchaseWare.setCreateBy(SecurityUtils.getUsername());
        purchaseWare.setDjNumber(StringUtils.getRandomCode("RK"));
        if(purchaseWare.getRows()!=null&&purchaseWare.getRows()!="") {
            List<PurchaseWareChild> childList = JSONArray.parseArray(purchaseWare.getRows(), PurchaseWareChild.class);
            for (PurchaseWareChild child : childList) {
                child.setCreateBy(SecurityUtils.getUsername());
                child.setDjNumber(purchaseWare.getDjNumber());
                child.setCreateTime(DateUtils.getNowDate());
                PurchaseWareChildService.insertPurchaseWareChild(child);
            }
        }
        //插入附件
        if(purchaseWare.getFileRows()!=null&&purchaseWare.getFileRows()!="") {
            List<SystemFile> childList = JSONArray.parseArray(purchaseWare.getFileRows(), SystemFile.class);
            for (SystemFile child : childList) {
                child.setCode(purchaseWare.getDjNumber());
                child.setCreateBy(SecurityUtils.getUsername());
                systemFileService.insertSystemFile(child);
            }
        }
        return toAjax(purchaseWareService.insertPurchaseWare(purchaseWare));
    }

    /**
     * 修改采购入库
     */
    @PreAuthorize("@ss.hasPermi('system:purchaseWare:edit')")
    @Log(title = "采购入库", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PurchaseWare purchaseWare)
    {
        if(purchaseWare.getStatus()>0){
            return toAjaxByError("禁止修改!");
        }
        purchaseWare.setUpdateBy(SecurityUtils.getUsername());
        if(purchaseWare.getRows()!=null&&purchaseWare.getRows()!="") {
            List<PurchaseWareChild> childList = JSONArray.parseArray(purchaseWare.getRows(), PurchaseWareChild.class);
            for (PurchaseWareChild child : childList) {
                if (child.getId() != null) {
                    child.setDjNumber(purchaseWare.getDjNumber());
                    child.setUpdateBy(SecurityUtils.getUsername());
                    PurchaseWareChildService.updatePurchaseWareChild(child);
                } else {
                    child.setCreateBy(SecurityUtils.getUsername());
                    child.setDjNumber(purchaseWare.getDjNumber());
                    child.setCreateTime(DateUtils.getNowDate());
                    PurchaseWareChildService.updatePurchaseWareChild(child);
                }
            }
        }
        //插入附件
        if(purchaseWare.getFileRows()!=null&&purchaseWare.getFileRows()!="") {
            List<SystemFile> childList = JSONArray.parseArray(purchaseWare.getFileRows(), SystemFile.class);
            for (SystemFile child : childList) {
                if (child.getId() != null) {

                }else{
                    child.setCode(purchaseWare.getDjNumber());
                    child.setCreateBy(SecurityUtils.getUsername());
                    systemFileService.insertSystemFile(child);
                }
            }
        }
        return toAjax(purchaseWareService.updatePurchaseWare(purchaseWare));
    }


    /**
     * 提交采购入库
     */
    @PreAuthorize("@ss.hasPermi('system:purchaseWare:effect')")
    @Log(title = "提交采购入库", businessType = BusinessType.EFFECT)
    @DeleteMapping("/effect/{ids}")
    public AjaxResult effect(@PathVariable Integer[] ids)
    {
        //查询审批流程
        FlowInfo flowInfo=new FlowInfo();
        flowInfo.setFlowNo("CGRK001"+SecurityUtils.getUsername());
        flowInfo.setStatus(1);
        List<FlowInfo> list = flowInfoService.selectFlowInfoList(flowInfo);
        //查询审批节点
        FlowNode node=new FlowNode();
        node.setFlowNo(flowInfo.getFlowNo());
        List<FlowNode> nodeList = flowNodeService.selectFlowNodeList(node);
        for(Integer id:ids){
            PurchaseWare info=purchaseWareService.selectPurchaseWareById(id);
            if(info.getStatus()>0){
                continue;
            }
            //判断是否审批 如不审批则直接生效
            if(info.getIsSp()==0){
                //直接生效
                info.setStatus(2);
                purchaseWareService.updatePurchaseWare(info);
                PurchaseWareChild child=new PurchaseWareChild();
                child.setDjNumber(info.getDjNumber());
                List<PurchaseWareChild> purchaseWareChildList=PurchaseWareChildService.selectPurchaseWareChildList(child);
                for(PurchaseWareChild pwChild:purchaseWareChildList){
                    //添加库存
                    StockInfo StockInfo=new StockInfo();
                    StockInfo.setDjNumber(info.getDjNumber());
                    StockInfo.setProjectCode(info.getProjectCode());
                    StockInfo.setProjectName(info.getProjectName());
//                    StockInfo.setStoreCode(child.);
//                    StockInfo.setStoreName(info.getDjNumber());
                    StockInfo.setDjTime(info.getDjTime());
                    StockInfo.setRkTime(DateUtils.getTime());
                    StockInfo.setGoodsCode(pwChild.getGoodsCode());
                    StockInfo.setGoodsName(pwChild.getGoodsName());
                    StockInfo.setGoodsDw(pwChild.getGoodsDw());
                    StockInfo.setGoodsGg(pwChild.getGoodsGg());
                    StockInfo.setGoodsNum(pwChild.getGoodsNum());
                    StockInfo.setGoodsPrice(pwChild.getGoodsPrice());
                    StockInfo.setGoodsMoney(pwChild.getGoodsMoney());
                    StockInfo.setCreateBy(SecurityUtils.getUsername());
                    stockInfoService.insertStockInfo(StockInfo);
                }
                child=null;
            }else{
                if(list!=null&&list.size()>0){
                    //添加流程号
                    info.setFlowNo(list.get(0).getFlowNo());
                    //状态修改为待审核
                    info.setStatus(1);
                    //添加一级节点
                    info.setNodeNo(1);
                    purchaseWareService.updatePurchaseWare(info);
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
     * 取消提交采购入库
     */
    @PreAuthorize("@ss.hasPermi('system:purchaseWare:cancel')")
    @Log(title = "取消提交采购入库", businessType = BusinessType.CANCEL)
    @DeleteMapping("/cancel/{ids}")
    public AjaxResult cancel(@PathVariable Integer[] ids)
    {
        for(Integer id:ids){
            PurchaseWare info=purchaseWareService.selectPurchaseWareById(id);
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
            purchaseWareService.updatePurchaseWare(info);
        }
        return toAjaxBySuccess("取消成功!");
    }



    /**
     * 审核采购入库
     */
    @PreAuthorize("@ss.hasPermi('system:purchaseWare:examine')")
    @Log(title = "审核采购入库", businessType = BusinessType.UPDATE)
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
            purchaseWareService.updatetPurchaseWareStatusOrNodeNo(flowAudit.getDjId(), -1, 1);
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
            purchaseWareService.updatetPurchaseWareStatusOrNodeNo(flowAudit.getDjId(), flowAudit.getNodeNo() + 1, 0);
            //流程结束
            if (lag) {
                //修改单据状态为已生效
                purchaseWareService.updatetPurchaseWareStatusOrNodeNo(flowAudit.getDjId(), 2, 1);
                PurchaseWare ware=new PurchaseWare();
                ware.setDjNumber(flowAudit.getDjId());
                PurchaseWare info=purchaseWareService.selectPurchaseWareList(ware).get(0);
                PurchaseWareChild child=new PurchaseWareChild();
                child.setDjNumber(info.getDjNumber());
                List<PurchaseWareChild> purchaseWareChildList=PurchaseWareChildService.selectPurchaseWareChildList(child);
                for(PurchaseWareChild pwChild:purchaseWareChildList){
                    //添加库存
                    StockInfo StockInfo=new StockInfo();
                    StockInfo.setDjNumber(info.getDjNumber());
                    StockInfo.setProjectCode(info.getProjectCode());
                    StockInfo.setProjectName(info.getProjectName());
                    StockInfo.setDjTime(info.getDjTime());
                    StockInfo.setRkTime(DateUtils.getTime());
                    StockInfo.setGoodsCode(pwChild.getGoodsCode());
                    StockInfo.setGoodsName(pwChild.getGoodsName());
                    StockInfo.setGoodsDw(pwChild.getGoodsDw());
                    StockInfo.setGoodsGg(pwChild.getGoodsGg());
                    StockInfo.setGoodsNum(pwChild.getGoodsNum());
                    StockInfo.setGoodsPrice(pwChild.getGoodsPrice());
                    StockInfo.setGoodsMoney(pwChild.getGoodsMoney());
                    StockInfo.setCreateBy(SecurityUtils.getUsername());
                    stockInfoService.insertStockInfo(StockInfo);
                    StockInfo=null;
                }
                child=null;
                ware=null;
            }
        }
        return toAjaxBySuccess("审批成功!");
    }

    /**
     * 取消审核采购入库
     */
    @PreAuthorize("@ss.hasPermi('system:purchaseWare:cancelAudit')")
    @Log(title = "取消审核采购入库", businessType = BusinessType.CANCEL)
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
            purchaseWareService.updatetPurchaseWareStatusOrNodeNo(djIds[i],(nodeNos[i]-1),0);
            //如果已经生效则改变状态为待审核
            if(lag){
                //修改单据状态为待审核
                stockInfoService.deleteStockInfoBydjNumber(djIds[i]);
                purchaseWareService.updatetPurchaseWareStatusOrNodeNo(djIds[i],1,1);
            }
        }
        return toAjaxBySuccess("取消成功!");
    }




    /**
     * 删除采购入库
     */
    @PreAuthorize("@ss.hasPermi('system:purchaseWare:remove')")
    @Log(title = "采购入库", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        for(Integer id:ids){
            PurchaseWare info=purchaseWareService.selectPurchaseWareById(id);
            if(info.getStatus()>0){
                return toAjaxByError("已生效禁止删除");
            }
        }
        PurchaseWareChildService.deletePurchaseWareChildByPIds(ids);
        return toAjax(purchaseWareService.deletePurchaseWareByIds(ids));
    }
}
