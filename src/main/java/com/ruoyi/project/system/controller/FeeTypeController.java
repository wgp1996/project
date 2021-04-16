package com.ruoyi.project.system.controller;

import java.util.List;

import com.ruoyi.project.system.domain.GoodsType;
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
import com.ruoyi.project.system.domain.FeeType;
import com.ruoyi.project.system.service.IFeeTypeService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 费用项目分类Controller
 * 
 * @author ruoyi
 * @date 2021-04-16
 */
@RestController
@RequestMapping("/system/feeType")
public class FeeTypeController extends BaseController
{
    @Autowired
    private IFeeTypeService feeTypeService;


    /**
     * 获取费用项目下拉树列表
     */
    @GetMapping("/treeselect")
    public AjaxResult treeselect(FeeType feeType)
    {
        List<FeeType> goods = feeTypeService.selectFeeTypeList(feeType);
        return AjaxResult.success(feeTypeService.buildGoodsTreeSelect(goods));
    }

    /**
     * 查询费用项目分类列表
     */
    @PreAuthorize("@ss.hasPermi('system:feeType:list')")
    @GetMapping("/list")
    public AjaxResult list(FeeType feeType)
    {
        List<FeeType> list = feeTypeService.selectFeeTypeList(feeType);
        return AjaxResult.success(list);
    }

    /**
     * 导出费用项目分类列表
     */
    @PreAuthorize("@ss.hasPermi('system:feeType:export')")
    @Log(title = "费用项目分类", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(FeeType feeType)
    {
        List<FeeType> list = feeTypeService.selectFeeTypeList(feeType);
        ExcelUtil<FeeType> util = new ExcelUtil<FeeType>(FeeType.class);
        return util.exportExcel(list, "feeType");
    }

    /**
     * 获取费用项目分类详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:feeType:query')")
    @GetMapping(value = "/{feeTypeId}")
    public AjaxResult getInfo(@PathVariable("feeTypeId") Integer feeTypeId)
    {
        return AjaxResult.success(feeTypeService.selectFeeTypeById(feeTypeId));
    }

    /**
     * 新增费用项目分类
     */
    @PreAuthorize("@ss.hasPermi('system:feeType:add')")
    @Log(title = "费用项目分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FeeType feeType)
    {
        return toAjax(feeTypeService.insertFeeType(feeType));
    }

    /**
     * 修改费用项目分类
     */
    @PreAuthorize("@ss.hasPermi('system:feeType:edit')")
    @Log(title = "费用项目分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FeeType feeType)
    {
        return toAjax(feeTypeService.updateFeeType(feeType));
    }

    /**
     * 删除费用项目分类
     */
    @PreAuthorize("@ss.hasPermi('system:feeType:remove')")
    @Log(title = "费用项目分类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{feeTypeIds}")
    public AjaxResult remove(@PathVariable Integer[] feeTypeIds)
    {
        return toAjax(feeTypeService.deleteFeeTypeByIds(feeTypeIds));
    }
}
