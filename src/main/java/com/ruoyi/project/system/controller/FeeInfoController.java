package com.ruoyi.project.system.controller;

import java.util.List;

import com.ruoyi.common.utils.SecurityUtils;
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
import com.ruoyi.project.system.domain.FeeInfo;
import com.ruoyi.project.system.service.IFeeInfoService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 费用项目Controller
 * 
 * @author ruoyi
 * @date 2021-04-16
 */
@RestController
@RequestMapping("/system/feeInfo")
public class FeeInfoController extends BaseController
{
    @Autowired
    private IFeeInfoService feeInfoService;

    /**
     * 查询费用项目列表
     */
    @PreAuthorize("@ss.hasPermi('system:feeInfo:list')")
    @GetMapping("/list")
    @DataScope(deptAlias = "d", userAlias = "u")
    public TableDataInfo list(FeeInfo feeInfo)
    {
        startPage();
        List<FeeInfo> list = feeInfoService.selectFeeInfoList(feeInfo);
        return getDataTable(list);
    }

    /**
     * 导出费用项目列表
     */
    @PreAuthorize("@ss.hasPermi('system:feeInfo:export')")
    @Log(title = "费用项目", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(FeeInfo feeInfo)
    {
        List<FeeInfo> list = feeInfoService.selectFeeInfoList(feeInfo);
        ExcelUtil<FeeInfo> util = new ExcelUtil<FeeInfo>(FeeInfo.class);
        return util.exportExcel(list, "feeInfo");
    }

    /**
     * 获取费用项目详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:feeInfo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(feeInfoService.selectFeeInfoById(id));
    }

    /**
     * 新增费用项目
     */
    @PreAuthorize("@ss.hasPermi('system:feeInfo:add')")
    @Log(title = "费用项目", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FeeInfo feeInfo)
    {
        feeInfo.setCreateBy(SecurityUtils.getUsername());
        return toAjax(feeInfoService.insertFeeInfo(feeInfo));
    }

    /**
     * 修改费用项目
     */
    @PreAuthorize("@ss.hasPermi('system:feeInfo:edit')")
    @Log(title = "费用项目", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FeeInfo feeInfo)
    {
        feeInfo.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(feeInfoService.updateFeeInfo(feeInfo));
    }

    /**
     * 删除费用项目
     */
    @PreAuthorize("@ss.hasPermi('system:feeInfo:remove')")
    @Log(title = "费用项目", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(feeInfoService.deleteFeeInfoByIds(ids));
    }
}
