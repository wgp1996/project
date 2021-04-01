package com.ruoyi.project.system.controller;

import java.util.List;

import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
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
import com.ruoyi.project.system.domain.StockInfo;
import com.ruoyi.project.system.service.IStockInfoService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 库存管理Controller
 * 
 * @author ruoyi
 * @date 2021-04-01
 */
@RestController
@RequestMapping("/system/stockInfo")
public class StockInfoController extends BaseController
{
    @Autowired
    private IStockInfoService stockInfoService;

    /**
     * 查询库存管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:stockInfo:list')")
    @GetMapping("/list")
    @DataScope(deptAlias = "d", userAlias = "u")
    public TableDataInfo list(StockInfo stockInfo)
    {
        startPage();
        List<StockInfo> list = stockInfoService.selectStockInfoList(stockInfo);
        return getDataTable(list);
    }

    /**
     * 导出库存管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:stockInfo:export')")
    @Log(title = "库存管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(StockInfo stockInfo)
    {
        List<StockInfo> list = stockInfoService.selectStockInfoList(stockInfo);
        ExcelUtil<StockInfo> util = new ExcelUtil<StockInfo>(StockInfo.class);
        return util.exportExcel(list, "stockInfo");
    }

    /**
     * 获取库存管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:stockInfo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(stockInfoService.selectStockInfoById(id));
    }

    /**
     * 新增库存管理
     */
    @PreAuthorize("@ss.hasPermi('system:stockInfo:add')")
    @Log(title = "库存管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StockInfo stockInfo)
    {
        return toAjax(stockInfoService.insertStockInfo(stockInfo));
    }

    /**
     * 修改库存管理
     */
    @PreAuthorize("@ss.hasPermi('system:stockInfo:edit')")
    @Log(title = "库存管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StockInfo stockInfo)
    {
        return toAjax(stockInfoService.updateStockInfo(stockInfo));
    }

    /**
     * 删除库存管理
     */
    @PreAuthorize("@ss.hasPermi('system:stockInfo:remove')")
    @Log(title = "库存管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(stockInfoService.deleteStockInfoByIds(ids));
    }
}
