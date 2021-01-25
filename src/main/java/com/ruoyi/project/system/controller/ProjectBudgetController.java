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
import com.ruoyi.project.system.domain.ProjectBudget;
import com.ruoyi.project.system.service.IProjectBudgetService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 项目预算Controller
 * 
 * @author ruoyi
 * @date 2021-01-22
 */
@RestController
@RequestMapping("/system/budgetInfo")
public class ProjectBudgetController extends BaseController
{
    @Autowired
    private IProjectBudgetService projectBudgetService;

    /**
     * 查询项目预算列表
     */
    @PreAuthorize("@ss.hasPermi('system:budgetInfo:list')")
    @DataScope(deptAlias = "d", userAlias = "u")
    @GetMapping("/list")
    public TableDataInfo list(ProjectBudget projectBudget)
    {
        startPage();
        List<ProjectBudget> list = projectBudgetService.selectProjectBudgetList(projectBudget);
        return getDataTable(list);
    }

    /**
     * 导出项目预算列表
     */
    @PreAuthorize("@ss.hasPermi('system:budgetInfo:export')")
    @Log(title = "项目预算", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ProjectBudget projectBudget)
    {
        List<ProjectBudget> list = projectBudgetService.selectProjectBudgetList(projectBudget);
        ExcelUtil<ProjectBudget> util = new ExcelUtil<ProjectBudget>(ProjectBudget.class);
        return util.exportExcel(list, "budgetInfo");
    }

    /**
     * 获取项目预算详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:budgetInfo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(projectBudgetService.selectProjectBudgetById(id));
    }

    /**
     * 新增项目预算
     */
    @PreAuthorize("@ss.hasPermi('system:budgetInfo:add')")
    @Log(title = "项目预算", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProjectBudget projectBudget)
    {
        projectBudget.setCreateBy(SecurityUtils.getUsername());
        return toAjax(projectBudgetService.insertProjectBudget(projectBudget));
    }

    /**
     * 修改项目预算
     */
    @PreAuthorize("@ss.hasPermi('system:budgetInfo:edit')")
    @Log(title = "项目预算", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProjectBudget projectBudget)
    {
        projectBudget.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(projectBudgetService.updateProjectBudget(projectBudget));
    }

    /**
     * 删除项目预算
     */
    @PreAuthorize("@ss.hasPermi('system:budgetInfo:remove')")
    @Log(title = "项目预算", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(projectBudgetService.deleteProjectBudgetByIds(ids));
    }
}
