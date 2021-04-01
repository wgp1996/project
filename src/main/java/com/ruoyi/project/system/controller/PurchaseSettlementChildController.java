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
import com.ruoyi.project.system.domain.PurchaseSettlementChild;
import com.ruoyi.project.system.service.IPurchaseSettlementChildService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 结算申请单明细Controller
 * 
 * @author ruoyi
 * @date 2021-04-01
 */
@RestController
@RequestMapping("/system/purchaseSettlementChild")
public class PurchaseSettlementChildController extends BaseController
{
    @Autowired
    private IPurchaseSettlementChildService purchaseSettlementChildService;

    /**
     * 查询结算申请单明细列表
     */
    @GetMapping("/list")
    public TableDataInfo list(PurchaseSettlementChild purchaseSettlementChild)
    {
        startPage();
        List<PurchaseSettlementChild> list = purchaseSettlementChildService.selectPurchaseSettlementChildList(purchaseSettlementChild);
        return getDataTable(list);
    }

    /**
     * 导出结算申请单明细列表
     */
    @PreAuthorize("@ss.hasPermi('system:purchaseSettlementChild:export')")
    @Log(title = "结算申请单明细", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(PurchaseSettlementChild purchaseSettlementChild)
    {
        List<PurchaseSettlementChild> list = purchaseSettlementChildService.selectPurchaseSettlementChildList(purchaseSettlementChild);
        ExcelUtil<PurchaseSettlementChild> util = new ExcelUtil<PurchaseSettlementChild>(PurchaseSettlementChild.class);
        return util.exportExcel(list, "purchaseSettlementChild");
    }

    /**
     * 获取结算申请单明细详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:purchaseSettlementChild:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(purchaseSettlementChildService.selectPurchaseSettlementChildById(id));
    }

    /**
     * 新增结算申请单明细
     */
    @PreAuthorize("@ss.hasPermi('system:purchaseSettlementChild:add')")
    @Log(title = "结算申请单明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PurchaseSettlementChild purchaseSettlementChild)
    {
        return toAjax(purchaseSettlementChildService.insertPurchaseSettlementChild(purchaseSettlementChild));
    }

    /**
     * 修改结算申请单明细
     */
    @PreAuthorize("@ss.hasPermi('system:purchaseSettlementChild:edit')")
    @Log(title = "结算申请单明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PurchaseSettlementChild purchaseSettlementChild)
    {
        return toAjax(purchaseSettlementChildService.updatePurchaseSettlementChild(purchaseSettlementChild));
    }

    /**
     * 删除结算申请单明细
     */
    @PreAuthorize("@ss.hasPermi('system:purchaseSettlementChild:remove')")
    @Log(title = "结算申请单明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(purchaseSettlementChildService.deletePurchaseSettlementChildByIds(ids));
    }
}
