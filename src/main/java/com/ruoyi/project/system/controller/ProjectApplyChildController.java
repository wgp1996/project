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
import com.ruoyi.project.system.domain.ProjectApplyChild;
import com.ruoyi.project.system.service.IProjectApplyChildService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 项目部信息Controller
 * 
 * @author ruoyi
 * @date 2021-03-18
 */
@RestController
@RequestMapping("/system/enginnerApplyChild")
public class ProjectApplyChildController extends BaseController
{
    @Autowired
    private IProjectApplyChildService projectApplyChildService;

    /**
     * 查询项目部信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:enginnerApplyChild:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProjectApplyChild projectApplyChild)
    {
        startPage();
        List<ProjectApplyChild> list = projectApplyChildService.selectProjectApplyChildList(projectApplyChild);
        return getDataTable(list);
    }

    /**
     * 查询项目部信息列表
     */
    @GetMapping("/allList")
    public TableDataInfo allList(ProjectApplyChild projectApplyChild)
    {
        List<ProjectApplyChild> list = projectApplyChildService.selectProjectApplyChildList(projectApplyChild);
        return getDataTable(list);
    }


    /**
     * 导出项目部信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:enginnerApplyChild:export')")
    @Log(title = "项目部信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ProjectApplyChild projectApplyChild)
    {
        List<ProjectApplyChild> list = projectApplyChildService.selectProjectApplyChildList(projectApplyChild);
        ExcelUtil<ProjectApplyChild> util = new ExcelUtil<ProjectApplyChild>(ProjectApplyChild.class);
        return util.exportExcel(list, "enginnerApplyChild");
    }

    /**
     * 获取项目部信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:enginnerApplyChild:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(projectApplyChildService.selectProjectApplyChildById(id));
    }

    /**
     * 新增项目部信息
     */
    @PreAuthorize("@ss.hasPermi('system:enginnerApplyChild:add')")
    @Log(title = "项目部信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProjectApplyChild projectApplyChild)
    {
        return toAjax(projectApplyChildService.insertProjectApplyChild(projectApplyChild));
    }

    /**
     * 修改项目部信息
     */
    @PreAuthorize("@ss.hasPermi('system:enginnerApplyChild:edit')")
    @Log(title = "项目部信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProjectApplyChild projectApplyChild)
    {
        return toAjax(projectApplyChildService.updateProjectApplyChild(projectApplyChild));
    }

    /**
     * 删除项目部信息
     */
    @PreAuthorize("@ss.hasPermi('system:enginnerApplyChild:remove')")
    @Log(title = "项目部信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(projectApplyChildService.deleteProjectApplyChildByIds(ids));
    }
}
