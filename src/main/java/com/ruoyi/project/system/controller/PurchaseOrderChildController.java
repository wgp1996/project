package com.ruoyi.project.system.controller;

import java.util.List;
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
import com.ruoyi.project.system.domain.PurchaseOrderChild;
import com.ruoyi.project.system.service.IPurchaseOrderChildService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 采购订单子表Controller
 * 
 * @author ruoyi
 * @date 2021-03-30
 */
@RestController
@RequestMapping("/system/purchaseOrderChild")
public class PurchaseOrderChildController extends BaseController
{
    @Autowired
    private IPurchaseOrderChildService purchaseOrderChildService;

    /**
     * 查询采购订单子表列表
     */
    @GetMapping("/list")
    public TableDataInfo list(PurchaseOrderChild purchaseOrderChild)
    {
        if(purchaseOrderChild.getDjNumber()==null||purchaseOrderChild.getDjNumber()==""){
            purchaseOrderChild.setDjNumber("-1");
        }
        startPage();
        List<PurchaseOrderChild> list = purchaseOrderChildService.selectPurchaseOrderChildList(purchaseOrderChild);
        return getDataTable(list);
    }

    /**
     * 导出采购订单子表列表
     */
    @PreAuthorize("@ss.hasPermi('system:purchaseOrderChild:export')")
    @Log(title = "采购订单子表", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(PurchaseOrderChild purchaseOrderChild)
    {
        List<PurchaseOrderChild> list = purchaseOrderChildService.selectPurchaseOrderChildList(purchaseOrderChild);
        ExcelUtil<PurchaseOrderChild> util = new ExcelUtil<PurchaseOrderChild>(PurchaseOrderChild.class);
        return util.exportExcel(list, "purchaseOrderChild");
    }

    /**
     * 获取采购订单子表详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:purchaseOrderChild:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(purchaseOrderChildService.selectPurchaseOrderChildById(id));
    }

    /**
     * 新增采购订单子表
     */
    @PreAuthorize("@ss.hasPermi('system:purchaseOrderChild:add')")
    @Log(title = "采购订单子表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PurchaseOrderChild purchaseOrderChild)
    {
        return toAjax(purchaseOrderChildService.insertPurchaseOrderChild(purchaseOrderChild));
    }

    /**
     * 修改采购订单子表
     */
    @PreAuthorize("@ss.hasPermi('system:purchaseOrderChild:edit')")
    @Log(title = "采购订单子表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PurchaseOrderChild purchaseOrderChild)
    {
        return toAjax(purchaseOrderChildService.updatePurchaseOrderChild(purchaseOrderChild));
    }

    /**
     * 删除采购订单子表
     */
    @PreAuthorize("@ss.hasPermi('system:purchaseOrderChild:remove')")
    @Log(title = "采购订单子表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(purchaseOrderChildService.deletePurchaseOrderChildByIds(ids));
    }
}
