package com.ruoyi.project.system.controller;

import java.util.List;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.project.system.domain.*;
import com.ruoyi.project.system.service.IPickingReturnChildService;
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
import com.ruoyi.project.system.service.IPickingReturnService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 借用归还单Controller
 * 
 * @author xy
 * @date 2021-04-12
 */
@RestController
@RequestMapping("/system/pickingReturn")
public class PickingReturnController extends BaseController
{
    @Autowired
    private IPickingReturnService pickingReturnService;
    @Autowired
    private IPickingReturnChildService pickingReturnChildService;
    /**
     * 查询借用归还单列表
     */
    @PreAuthorize("@ss.hasPermi('system:pickingReturn:list')")
    @GetMapping("/list")
    public TableDataInfo list(PickingReturn pickingReturn)
    {
        startPage();
        List<PickingReturn> list = pickingReturnService.selectPickingReturnList(pickingReturn);
        return getDataTable(list);
    }

    /**
     * 查询审核列表
     */
    @GetMapping("/shList")
    public TableDataInfo shList(PickingReturn pickingReturn)
    {
        startPage();
        String userId= SecurityUtils.getUsername();
        String roleId=SecurityUtils.getLoginUser().getUser().getRoles().get(0).getRoleId()+"";
        pickingReturn.setUserId(userId);
        pickingReturn.setRoleId(roleId);
        List<PickingReturn> list = pickingReturnService.selectPickingReturnShList(pickingReturn);
        return getDataTable(list);
    }

    /**
     * 导出借用归还单列表
     */
    @PreAuthorize("@ss.hasPermi('system:pickingReturn:export')")
    @Log(title = "借用归还单", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(PickingReturn pickingReturn)
    {
        List<PickingReturn> list = pickingReturnService.selectPickingReturnList(pickingReturn);
        ExcelUtil<PickingReturn> util = new ExcelUtil<PickingReturn>(PickingReturn.class);
        return util.exportExcel(list, "pickingReturn");
    }

    /**
     * 获取借用归还单详细信息
     */
   //@PreAuthorize("@ss.hasPermi('system:pickingReturn:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(pickingReturnService.selectPickingReturnById(id));
    }

    /**
     * 新增借用归还单
     */
    @PreAuthorize("@ss.hasPermi('system:pickingReturn:add')")
    @Log(title = "借用归还单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PickingReturn pickingReturn)
    {
        return toAjax(pickingReturnService.insertPickingReturn(pickingReturn));
    }

    /**
     * 修改借用归还单
     */
    @PreAuthorize("@ss.hasPermi('system:pickingReturn:edit')")
    @Log(title = "借用归还单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PickingReturn pickingReturn)
    {
        return toAjax(pickingReturnService.updatePickingReturn(pickingReturn));
    }



    /**
     * 提交借用归还
     */
    @PreAuthorize("@ss.hasPermi('system:pickingReturn:effect')")
    @Log(title = "提交借用归还", businessType = BusinessType.EFFECT)
    @DeleteMapping("/effect/{ids}")
    public AjaxResult effect(@PathVariable Integer[] ids)
    {
        if(pickingReturnService.effect(ids)>0){
            return toAjaxBySuccess("提交成功!");
        }else{
            return toAjaxByError("提交失败!");
        }

    }

    /**
     * 取消提交借用归还
     */
    @PreAuthorize("@ss.hasPermi('system:pickingReturn:cancel')")
    @Log(title = "取消提交借用归还", businessType = BusinessType.CANCEL)
    @DeleteMapping("/cancel/{ids}")
    public AjaxResult cancel(@PathVariable Integer[] ids)
    {
        if(pickingReturnService.cancel(ids)>0){
            return toAjaxBySuccess("取消成功!");
        }else{
            return toAjaxByError("提交失败!");
        }
    }

    /**
     * 审核借用归还
     */
    @PreAuthorize("@ss.hasPermi('system:pickingReturn:examine')")
    @Log(title = "审核借用归还", businessType = BusinessType.UPDATE)
    @PutMapping("/examine")
    public AjaxResult examine(@RequestBody FlowAudit flowAudit)
    {
        if(pickingReturnService.examine(flowAudit)>0){
            return toAjaxBySuccess("审批成功!");
        }else if(pickingReturnService.examine(flowAudit)==0){
            return toAjaxByError("重复审核!");
        }else{
            return toAjaxByError("审核失败!");
        }

    }

    /**
     * 取消审核借用归还
     */
    @PreAuthorize("@ss.hasPermi('system:pickingReturn:cancelAudit')")
    @Log(title = "取消审核借用归还", businessType = BusinessType.CANCEL)
    @DeleteMapping("/cancelAudit/{djIds}/{nodeNos}")
    public AjaxResult remove(@PathVariable String[] djIds,@PathVariable Integer[] nodeNos)
    {
        if(pickingReturnService.cancelAudit(djIds,nodeNos)>0){
            return toAjaxBySuccess("取消审核成功!");
        }else{
            return toAjaxByError("取消审核失败!");
        }
    }


    /**
     * 删除借用归还单
     */
    @PreAuthorize("@ss.hasPermi('system:pickingReturn:remove')")
    @Log(title = "借用归还单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(pickingReturnService.deletePickingReturnByIds(ids));
    }
}
