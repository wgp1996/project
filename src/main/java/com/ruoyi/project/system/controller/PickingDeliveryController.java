package com.ruoyi.project.system.controller;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.project.system.domain.*;
import com.ruoyi.project.system.domain.PickingDeliveryChild;
import com.ruoyi.project.system.service.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
 * 领料出库单Controller
 * 
 * @author ruoyi
 * @date 2021-04-07
 */
@RestController
@RequestMapping("/system/pickingDelivery")
public class PickingDeliveryController extends BaseController
{
    @Autowired
    private IPickingDeliveryService pickingDeliveryService;
    @Autowired
    private IPickingDeliveryChildService pickingDeliveryChildService;
    @Autowired
    private ISystemFileService systemFileService;
    @Autowired
    private IFlowInfoService flowInfoService;
    @Autowired
    private IFlowNodeService flowNodeService;
    @Autowired
    private IFlowAuditService flowAuditService;
    @Autowired
    private IStockInfoService stockInfoService;

    /**
     * 查询领料出库单列表
     */
    @PreAuthorize("@ss.hasPermi('system:pickingDelivery:list')")
    @GetMapping("/list")
    @DataScope(deptAlias = "d", userAlias = "u")
    public TableDataInfo list(PickingDelivery pickingDelivery)
    {
        startPage();
        List<PickingDelivery> list = pickingDeliveryService.selectPickingDeliveryList(pickingDelivery);
        return getDataTable(list);
    }

    /**
     * 查询采购入库审核列表
     */
    @GetMapping("/shList")
    public TableDataInfo shList(PickingDelivery pickingDelivery)
    {
        startPage();
        String userId= SecurityUtils.getUsername();
        String roleId=SecurityUtils.getLoginUser().getUser().getRoles().get(0).getRoleId()+"";
        pickingDelivery.setUserId(userId);
        pickingDelivery.setRoleId(roleId);
        List<PickingDelivery> list = pickingDeliveryService.selectPickingDeliveryShList(pickingDelivery);
        return getDataTable(list);
    }

    /**
     * 借还单选择出库单列表
     */
    @GetMapping("/returnSelectList")
    public TableDataInfo returnSelectList(PickingDeliveryChild pickingDeliveryChild)
    {
        startPage();
        pickingDeliveryChild.setCreateBy(SecurityUtils.getUsername());
        List<PickingReturnChild> list = pickingDeliveryChildService.selectPickingDeliveryListByReturn(pickingDeliveryChild);
        return getDataTable(list);
    }


    /**
     * 导出领料出库单列表
     */
    @PreAuthorize("@ss.hasPermi('system:pickingDelivery:export')")
    @Log(title = "领料出库单", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(PickingDelivery pickingDelivery)
    {
        List<PickingDelivery> list = pickingDeliveryService.selectPickingDeliveryList(pickingDelivery);
        ExcelUtil<PickingDelivery> util = new ExcelUtil<PickingDelivery>(PickingDelivery.class);
        return util.exportExcel(list, "pickingDelivery");
    }

    /**
     * 获取领料出库单详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:pickingDelivery:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(pickingDeliveryService.selectPickingDeliveryById(id));
    }

    /**
     * 新增领料出库单
     */
    @PreAuthorize("@ss.hasPermi('system:pickingDelivery:add')")
    @Log(title = "领料出库单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PickingDelivery pickingDelivery)
    {
        return toAjax(pickingDeliveryService.insertPickingDelivery(pickingDelivery));
    }

    /**
     * 修改领料出库单
     */
    @PreAuthorize("@ss.hasPermi('system:pickingDelivery:edit')")
    @Log(title = "领料出库单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PickingDelivery pickingDelivery)
    {
        if(pickingDelivery.getStatus()>0){
            return toAjaxByError("禁止修改!");
        }
        return toAjax(pickingDeliveryService.updatePickingDelivery(pickingDelivery));
    }



    /**
     * 提交领料出库
     */
    @PreAuthorize("@ss.hasPermi('system:pickingDelivery:effect')")
    @Log(title = "提交领料出库", businessType = BusinessType.EFFECT)
    @DeleteMapping("/effect/{ids}")
    public AjaxResult effect(@PathVariable Integer[] ids)
    {
        if(pickingDeliveryService.effect(ids)>0){
            return toAjaxBySuccess("提交成功!");
        }else{
            return toAjaxByError("提交失败!");
        }
    }

    /**
     * 取消提交领料出库
     */
    @PreAuthorize("@ss.hasPermi('system:pickingDelivery:cancel')")
    @Log(title = "取消提交领料出库", businessType = BusinessType.CANCEL)
    @DeleteMapping("/cancel/{ids}")
    public AjaxResult cancel(@PathVariable Integer[] ids)
    {
        if(pickingDeliveryService.cancel(ids)>0){
            return toAjaxBySuccess("取消成功!");
        }else{
            return toAjaxByError("提交失败!");
        }
    }

    /**
     * 审核领料出库
     */
    @PreAuthorize("@ss.hasPermi('system:pickingDelivery:examine')")
    @Log(title = "审核领料出库", businessType = BusinessType.UPDATE)
    @PutMapping("/examine")
    public AjaxResult examine(@RequestBody FlowAudit flowAudit)
    {
        if(pickingDeliveryService.examine(flowAudit)>0){
            return toAjaxBySuccess("审批成功!");
        }else if(pickingDeliveryService.examine(flowAudit)==0){
            return toAjaxByError("重复审核!");
        }else{
            return toAjaxByError("审核失败!");
        }

    }

    /**
     * 取消审核领料出库
     */
    @PreAuthorize("@ss.hasPermi('system:pickingDelivery:cancelAudit')")
    @Log(title = "取消审核领料出库", businessType = BusinessType.CANCEL)
    @DeleteMapping("/cancelAudit/{djIds}/{nodeNos}")
    public AjaxResult remove(@PathVariable String[] djIds,@PathVariable Integer[] nodeNos)
    {
        if(pickingDeliveryService.cancelAudit(djIds,nodeNos)>0){
            return toAjaxBySuccess("取消审核成功!");
        }else if(pickingDeliveryService.cancelAudit(djIds,nodeNos)==-1){
            return toAjaxByError("单据被引用!");
        }else{
            return toAjaxByError("取消审核失败!");
        }
    }




    /**
     * 删除领料出库单
     */
    @PreAuthorize("@ss.hasPermi('system:pickingDelivery:remove')")
    @Log(title = "领料出库单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(pickingDeliveryService.deletePickingDeliveryByIds(ids));
    }
}
