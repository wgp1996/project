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
import com.ruoyi.project.system.domain.FeeApplyInfoChild;
import com.ruoyi.project.system.service.IFeeApplyInfoChildService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 费用报销子表Controller
 * 
 * @author ruoyi
 * @date 2021-04-16
 */
@RestController
@RequestMapping("/system/feeApplyInfoChild")
public class FeeApplyInfoChildController extends BaseController
{
    @Autowired
    private IFeeApplyInfoChildService feeApplyInfoChildService;

    /**
     * 查询费用报销子表列表
     */
    @PreAuthorize("@ss.hasPermi('system:feeApplyInfoChild:list')")
    @GetMapping("/list")
    public TableDataInfo list(FeeApplyInfoChild feeApplyInfoChild)
    {
        startPage();
        List<FeeApplyInfoChild> list = feeApplyInfoChildService.selectFeeApplyInfoChildList(feeApplyInfoChild);
        return getDataTable(list);
    }

    /**
     * 导出费用报销子表列表
     */
    @PreAuthorize("@ss.hasPermi('system:feeApplyInfoChild:export')")
    @Log(title = "费用报销子表", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(FeeApplyInfoChild feeApplyInfoChild)
    {
        List<FeeApplyInfoChild> list = feeApplyInfoChildService.selectFeeApplyInfoChildList(feeApplyInfoChild);
        ExcelUtil<FeeApplyInfoChild> util = new ExcelUtil<FeeApplyInfoChild>(FeeApplyInfoChild.class);
        return util.exportExcel(list, "feeApplyInfoChild");
    }

    /**
     * 获取费用报销子表详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:feeApplyInfoChild:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(feeApplyInfoChildService.selectFeeApplyInfoChildById(id));
    }

    /**
     * 新增费用报销子表
     */
    @PreAuthorize("@ss.hasPermi('system:feeApplyInfoChild:add')")
    @Log(title = "费用报销子表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FeeApplyInfoChild feeApplyInfoChild)
    {
        return toAjax(feeApplyInfoChildService.insertFeeApplyInfoChild(feeApplyInfoChild));
    }

    /**
     * 修改费用报销子表
     */
    @PreAuthorize("@ss.hasPermi('system:feeApplyInfoChild:edit')")
    @Log(title = "费用报销子表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FeeApplyInfoChild feeApplyInfoChild)
    {
        return toAjax(feeApplyInfoChildService.updateFeeApplyInfoChild(feeApplyInfoChild));
    }

    /**
     * 删除费用报销子表
     */
    @PreAuthorize("@ss.hasPermi('system:feeApplyInfoChild:remove')")
    @Log(title = "费用报销子表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(feeApplyInfoChildService.deleteFeeApplyInfoChildByIds(ids));
    }
}
