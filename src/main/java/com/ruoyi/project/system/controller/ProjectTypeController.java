package com.ruoyi.project.system.controller;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.system.domain.ProjectType;
import com.ruoyi.project.system.service.IProjectTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 项目分类Controller
 * 
 * @author ruoyi
 * @date 2020-08-14
 */
@RestController
@RequestMapping("/system/projectType")
public class ProjectTypeController extends BaseController
{
    @Autowired
    private IProjectTypeService projectTypeService;
    /**
     * 获取部门下拉树列表
     */
    @GetMapping("/treeselect")
    public AjaxResult treeselect(ProjectType good)
    {
        List<ProjectType> goods = projectTypeService.selectProjectTypeList(good);
        return AjaxResult.success(projectTypeService.buildGoodsTreeSelect(goods));
    }

    /**
     * 获取部门下拉树列表
     */
    @GetMapping("/projecttreeselect")
    public AjaxResult projecttreeselect(ProjectType good)
    {
        List<ProjectType> goods = projectTypeService.selectProjectTypeProjectList(good);
        return AjaxResult.success(goods);
    }
    /**
     * 查询项目分类列表
     */
    @PreAuthorize("@ss.hasPermi('system:projectType:list')")
    @GetMapping("/list")
    @DataScope(deptAlias = "d", userAlias = "u")
    public AjaxResult list(ProjectType projectType)
    {
        List<ProjectType> list = projectTypeService.selectProjectTypeList(projectType);
        return AjaxResult.success(list);
    }

    /**
     * 导出项目分类列表
     */
    @PreAuthorize("@ss.hasPermi('system:projectType:export')")
    @Log(title = "项目分类", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ProjectType projectType)
    {
        List<ProjectType> list = projectTypeService.selectProjectTypeList(projectType);
        ExcelUtil<ProjectType> util = new ExcelUtil<ProjectType>(ProjectType.class);
        return util.exportExcel(list, "projectType");
    }

    /**
     * 获取项目分类详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:projectType:query')")
    @GetMapping(value = "/{projectTypeId}")
    public AjaxResult getInfo(@PathVariable("projectTypeId") Integer projectTypeId)
    {
        return AjaxResult.success(projectTypeService.selectProjectTypeById(projectTypeId));
    }

    /**
     * 新增项目分类
     */
    @PreAuthorize("@ss.hasPermi('system:projectType:add')")
    @Log(title = "项目分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProjectType projectType)
    {
        ProjectType info=projectTypeService.selectProjectTypeByName(projectType.getProjectTypeName(),-1);
        if(info!=null){
            return toAjaxByError("该分类已存在");
        }
        projectType.setCreateBy(SecurityUtils.getUsername());
        projectType.setType(0);
        return toAjax(projectTypeService.insertProjectType(projectType));
    }

    /**
     * 修改项目分类
     */
    @PreAuthorize("@ss.hasPermi('system:projectType:edit')")
    @Log(title = "项目分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProjectType projectType)
    {
        ProjectType info=projectTypeService.selectProjectTypeByName(projectType.getProjectTypeName(),projectType.getProjectTypeId());
        if(info!=null){
            return toAjaxByError("该分类已存在");
        }
        if(projectType.getProjectTypeId()==projectType.getProjectTypePid()){
            return toAjaxByError("上级分类不能是自己");
        }
        projectType.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(projectTypeService.updateProjectType(projectType));
    }

    /**
     * 删除项目分类
     */
    @PreAuthorize("@ss.hasPermi('system:projectType:remove')")
    @Log(title = "项目分类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{projectTypeIds}")
    public AjaxResult remove(@PathVariable Integer[] projectTypeIds)
    {
        if (projectTypeService.hasChildProjectTypeById(projectTypeIds[0])>0)
        {
            return AjaxResult.error("存在下级分类,不允许删除");
        }
        return toAjax(projectTypeService.deleteProjectTypeByIds(projectTypeIds));
    }
}
