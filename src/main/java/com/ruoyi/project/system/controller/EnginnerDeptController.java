package com.ruoyi.project.system.controller;

import java.util.List;

import com.ruoyi.common.constant.UserConstants;
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
import com.ruoyi.project.system.domain.EnginnerDept;
import com.ruoyi.project.system.service.IEnginnerDeptService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 部门Controller
 * 
 * @author ruoyi
 * @date 2021-03-18
 */
@RestController
@RequestMapping("/system/enginnerDept")
public class EnginnerDeptController extends BaseController
{
    @Autowired
    private IEnginnerDeptService enginnerDeptService;

    /**
     * 查询部门列表
     */
    @PreAuthorize("@ss.hasPermi('system:enginnerDept:list')")
    @GetMapping("/list")
    @DataScope(deptAlias = "d", userAlias = "u")
    public AjaxResult list(EnginnerDept enginnerDept)
    {
        List<EnginnerDept> list = enginnerDeptService.selectEnginnerDeptList(enginnerDept);
        return AjaxResult.success(list);
    }

    /**
     * 导出部门列表
     */
    @PreAuthorize("@ss.hasPermi('system:enginnerDept:export')")
    @Log(title = "部门", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(EnginnerDept enginnerDept)
    {
        List<EnginnerDept> list = enginnerDeptService.selectEnginnerDeptList(enginnerDept);
        ExcelUtil<EnginnerDept> util = new ExcelUtil<EnginnerDept>(EnginnerDept.class);
        return util.exportExcel(list, "enginnerDept");
    }

    /**
     * 获取部门详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:enginnerDept:query')")
    @GetMapping(value = "/{deptId}")
    public AjaxResult getInfo(@PathVariable("deptId") Long deptId)
    {
        return AjaxResult.success(enginnerDeptService.selectEnginnerDeptById(deptId));
    }

    /**
     * 新增部门
     */
    @PreAuthorize("@ss.hasPermi('system:enginnerDept:add')")
    @Log(title = "部门", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EnginnerDept enginnerDept)
    {
        if (UserConstants.NOT_UNIQUE.equals(enginnerDeptService.checkDeptNameUnique(enginnerDept)))
        {
            return AjaxResult.error("新增部门'" + enginnerDept.getDeptName() + "'失败，部门名称已存在");
        }
        enginnerDept.setCreateBy(SecurityUtils.getUsername());
        return toAjax(enginnerDeptService.insertEnginnerDept(enginnerDept));
    }

    /**
     * 修改部门
     */
    @PreAuthorize("@ss.hasPermi('system:enginnerDept:edit')")
    @Log(title = "部门", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EnginnerDept enginnerDept)
    {
        if (UserConstants.NOT_UNIQUE.equals(enginnerDeptService.checkDeptNameUnique(enginnerDept)))
        {
            return AjaxResult.error("修改部门'" + enginnerDept.getDeptName() + "'失败，部门名称已存在");
        }
        else if (enginnerDept.getParentId().equals(enginnerDept.getDeptId()))
        {
            return AjaxResult.error("修改部门'" + enginnerDept.getDeptName() + "'失败，上级部门不能是自己");
        }
        enginnerDept.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(enginnerDeptService.updateEnginnerDept(enginnerDept));
    }

    /**
     * 删除部门
     */
    @PreAuthorize("@ss.hasPermi('system:enginnerDept:remove')")
    @Log(title = "部门", businessType = BusinessType.DELETE)
	@DeleteMapping("/{deptId}")
    public AjaxResult remove(@PathVariable Long deptId)
    {
        if (enginnerDeptService.hasChildByDeptId(deptId))
        {
            return AjaxResult.error("存在下级部门,不允许删除");
        }
        return toAjax(enginnerDeptService.deleteEnginnerDeptById(deptId));
    }
}
