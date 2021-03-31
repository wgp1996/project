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
import com.ruoyi.project.system.domain.PurchaseWareChild;
import com.ruoyi.project.system.service.IPurchaseWareChildService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 采购入库子表Controller
 * 
 * @author ruoyi
 * @date 2021-03-30
 */
@RestController
@RequestMapping("/system/purchaseWareChild")
public class PurchaseWareChildController extends BaseController
{
    @Autowired
    private IPurchaseWareChildService PurchaseWareChildService;

    /**
     * 查询采购入库子表列表
     */
    @PreAuthorize("@ss.hasPermi('system:purchaseWareChild:list')")
    @GetMapping("/list")
    public TableDataInfo list(PurchaseWareChild PurchaseWareChild)
    {
        startPage();
        List<PurchaseWareChild> list = PurchaseWareChildService.selectPurchaseWareChildList(PurchaseWareChild);
        return getDataTable(list);
    }

    /**
     * 导出采购入库子表列表
     */
    @PreAuthorize("@ss.hasPermi('system:purchaseWareChild:export')")
    @Log(title = "采购入库子表", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(PurchaseWareChild PurchaseWareChild)
    {
        List<PurchaseWareChild> list = PurchaseWareChildService.selectPurchaseWareChildList(PurchaseWareChild);
        ExcelUtil<PurchaseWareChild> util = new ExcelUtil<PurchaseWareChild>(PurchaseWareChild.class);
        return util.exportExcel(list, "purchaseWareChild");
    }

    /**
     * 获取采购入库子表详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:purchaseWareChild:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(PurchaseWareChildService.selectPurchaseWareChildById(id));
    }

    /**
     * 新增采购入库子表
     */
    @PreAuthorize("@ss.hasPermi('system:purchaseWareChild:add')")
    @Log(title = "采购入库子表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PurchaseWareChild PurchaseWareChild)
    {
        return toAjax(PurchaseWareChildService.insertPurchaseWareChild(PurchaseWareChild));
    }

    /**
     * 修改采购入库子表
     */
    @PreAuthorize("@ss.hasPermi('system:purchaseWareChild:edit')")
    @Log(title = "采购入库子表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PurchaseWareChild PurchaseWareChild)
    {
        return toAjax(PurchaseWareChildService.updatePurchaseWareChild(PurchaseWareChild));
    }

    /**
     * 删除采购入库子表
     */
    @PreAuthorize("@ss.hasPermi('system:purchaseWareChild:remove')")
    @Log(title = "采购入库子表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(PurchaseWareChildService.deletePurchaseWareChildByIds(ids));
    }
}
