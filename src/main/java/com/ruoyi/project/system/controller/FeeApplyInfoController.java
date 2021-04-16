package com.ruoyi.project.system.controller;

import java.util.List;

import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.project.system.domain.FlowAudit;
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
import com.ruoyi.project.system.domain.FeeApplyInfo;
import com.ruoyi.project.system.service.IFeeApplyInfoService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 费用报销单Controller
 * 
 * @author xiaoyu
 * @date 2021-04-16
 */
@RestController
@RequestMapping("/system/feeApplyInfo")
public class FeeApplyInfoController extends BaseController
{
    @Autowired
    private IFeeApplyInfoService feeApplyInfoService;

    /**
     * 查询费用报销单列表
     */
    @PreAuthorize("@ss.hasPermi('system:feeApplyInfo:list')")
    @DataScope(deptAlias = "d", userAlias = "u")
    @GetMapping("/list")
    public TableDataInfo list(FeeApplyInfo feeApplyInfo)
    {
        startPage();
        List<FeeApplyInfo> list = feeApplyInfoService.selectFeeApplyInfoList(feeApplyInfo);
        return getDataTable(list);
    }

    /**
     * 导出费用报销单列表
     */
    @PreAuthorize("@ss.hasPermi('system:feeApplyInfo:export')")
    @Log(title = "费用报销单", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(FeeApplyInfo feeApplyInfo)
    {
        List<FeeApplyInfo> list = feeApplyInfoService.selectFeeApplyInfoList(feeApplyInfo);
        ExcelUtil<FeeApplyInfo> util = new ExcelUtil<FeeApplyInfo>(FeeApplyInfo.class);
        return util.exportExcel(list, "feeApplyInfo");
    }

    /**
     * 获取费用报销单详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:feeApplyInfo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(feeApplyInfoService.selectFeeApplyInfoById(id));
    }

    /**
     * 新增费用报销单
     */
    @PreAuthorize("@ss.hasPermi('system:feeApplyInfo:add')")
    @Log(title = "费用报销单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FeeApplyInfo feeApplyInfo)
    {
        return toAjax(feeApplyInfoService.insertFeeApplyInfo(feeApplyInfo));
    }

    /**
     * 修改费用报销单
     */
    @PreAuthorize("@ss.hasPermi('system:feeApplyInfo:edit')")
    @Log(title = "费用报销单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FeeApplyInfo feeApplyInfo)
    {
        return toAjax(feeApplyInfoService.updateFeeApplyInfo(feeApplyInfo));
    }


    /**
     * 提交费用报销单
     */
    @PreAuthorize("@ss.hasPermi('system:feeApplyInfo:effect')")
    @Log(title = "提交费用报销单", businessType = BusinessType.EFFECT)
    @DeleteMapping("/effect/{ids}")
    public AjaxResult effect(@PathVariable Integer[] ids)
    {
        if(feeApplyInfoService.effect(ids)>0){
            return toAjaxBySuccess("提交成功!");
        }else{
            return toAjaxByError("提交失败!");
        }

    }

    /**
     * 取消提交费用报销单
     */
    @PreAuthorize("@ss.hasPermi('system:feeApplyInfo:cancel')")
    @Log(title = "取消提交费用报销单", businessType = BusinessType.CANCEL)
    @DeleteMapping("/cancel/{ids}")
    public AjaxResult cancel(@PathVariable Integer[] ids)
    {
        if(feeApplyInfoService.cancel(ids)>0){
            return toAjaxBySuccess("取消成功!");
        }else{
            return toAjaxByError("提交失败!");
        }
    }

    /**
     * 审核费用报销单
     */
    @PreAuthorize("@ss.hasPermi('system:feeApplyInfo:examine')")
    @Log(title = "审核费用报销单", businessType = BusinessType.UPDATE)
    @PutMapping("/examine")
    public AjaxResult examine(@RequestBody FlowAudit flowAudit)
    {
        if(feeApplyInfoService.examine(flowAudit)>0){
            return toAjaxBySuccess("审批成功!");
        }else if(feeApplyInfoService.examine(flowAudit)==0){
            return toAjaxByError("重复审核!");
        }else{
            return toAjaxByError("审核失败!");
        }

    }

    /**
     * 取消审核费用报销单
     */
    @PreAuthorize("@ss.hasPermi('system:feeApplyInfo:cancelAudit')")
    @Log(title = "取消审核费用报销单", businessType = BusinessType.CANCEL)
    @DeleteMapping("/cancelAudit/{djIds}/{nodeNos}")
    public AjaxResult remove(@PathVariable String[] djIds,@PathVariable Integer[] nodeNos)
    {
        if(feeApplyInfoService.cancelAudit(djIds,nodeNos)>0){
            return toAjaxBySuccess("取消审核成功!");
        }else{
            return toAjaxByError("取消审核失败!");
        }
    }


    /**
     * 删除费用报销单
     */
    @PreAuthorize("@ss.hasPermi('system:feeApplyInfo:remove')")
    @Log(title = "费用报销单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(feeApplyInfoService.deleteFeeApplyInfoByIds(ids));
    }
}
