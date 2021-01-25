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
import com.ruoyi.project.system.domain.ProjectUser;
import com.ruoyi.project.system.service.IProjectUserService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 项目人员Controller
 * 
 * @author ruoyi
 * @date 2021-01-25
 */
@RestController
@RequestMapping("/system/projectUser")
public class ProjectUserController extends BaseController
{
    @Autowired
    private IProjectUserService projectUserService;

    /**
     * 查询项目人员列表
     */
    @GetMapping("/list")
    public TableDataInfo list(ProjectUser projectUser)
    {
        startPage();
        List<ProjectUser> list = projectUserService.selectProjectUserList(projectUser);
        return getDataTable(list);
    }

    /**
     * 导出项目人员列表
     */
    @PreAuthorize("@ss.hasPermi('system:projectUser:export')")
    @Log(title = "项目人员", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ProjectUser projectUser)
    {
        List<ProjectUser> list = projectUserService.selectProjectUserList(projectUser);
        ExcelUtil<ProjectUser> util = new ExcelUtil<ProjectUser>(ProjectUser.class);
        return util.exportExcel(list, "projectUser");
    }

    /**
     * 获取项目人员详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:projectUser:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(projectUserService.selectProjectUserById(id));
    }

    /**
     * 新增项目人员
     */
    @PreAuthorize("@ss.hasPermi('system:projectUser:add')")
    @Log(title = "项目人员", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProjectUser projectUser)
    {
        return toAjax(projectUserService.insertProjectUser(projectUser));
    }

    /**
     * 修改项目人员
     */
    @PreAuthorize("@ss.hasPermi('system:projectUser:edit')")
    @Log(title = "项目人员", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProjectUser projectUser)
    {
        return toAjax(projectUserService.updateProjectUser(projectUser));
    }

    /**
     * 删除项目人员
     */
    @PreAuthorize("@ss.hasPermi('system:projectUser:remove')")
    @Log(title = "项目人员", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(projectUserService.deleteProjectUserByIds(ids));
    }
}
