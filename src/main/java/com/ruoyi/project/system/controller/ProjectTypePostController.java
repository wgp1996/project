package com.ruoyi.project.system.controller;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.system.domain.ProjectTypePost;
import com.ruoyi.project.system.service.IProjectTypePostService;
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
@RequestMapping("/system/projectTypePost")
public class ProjectTypePostController extends BaseController
{
    @Autowired
    private IProjectTypePostService projectTypeService;
    /**
     * 获取部门下拉树列表
     */
    @GetMapping("/treeselect")
    public AjaxResult treeselect(ProjectTypePost good)
    {
        List<ProjectTypePost> goods = projectTypeService.selectProjectTypeList(good);
        return AjaxResult.success(projectTypeService.buildGoodsTreeSelect(goods));
    }
    /**
     * 查询项目分类列表
     */
    @PreAuthorize("@ss.hasPermi('system:projectTypePost:list')")
    @GetMapping("/list")
   // @DataScope(deptAlias = "d", userAlias = "u")
    public AjaxResult list(ProjectTypePost projectType)
    {
        List<ProjectTypePost> list = projectTypeService.selectProjectTypeList(projectType);
        return AjaxResult.success(list);
    }

    /**
     * 导出项目分类列表
     */
    @PreAuthorize("@ss.hasPermi('system:projectTypePost:export')")
    @Log(title = "项目分类", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ProjectTypePost projectType)
    {
        List<ProjectTypePost> list = projectTypeService.selectProjectTypeList(projectType);
        ExcelUtil<ProjectTypePost> util = new ExcelUtil<ProjectTypePost>(ProjectTypePost.class);
        return util.exportExcel(list, "projectType");
    }

    /**
     * 获取项目分类详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:projectTypePost:query')")
    @GetMapping(value = "/{projectTypeId}")
    public AjaxResult getInfo(@PathVariable("projectTypeId") Integer projectTypeId)
    {
        return AjaxResult.success(projectTypeService.selectProjectTypeById(projectTypeId));
    }

    /**
     * 新增项目分类
     */
    @PreAuthorize("@ss.hasPermi('system:projectTypePost:add')")
    @Log(title = "项目分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProjectTypePost projectType)
    {
        ProjectTypePost info=projectTypeService.selectProjectTypeByName(projectType.getProjectTypeName(),-1,projectType.getProjectCode());
        if(info!=null){
            return toAjaxByError("该项目岗位已存在");
        }
        projectType.setCreateBy(SecurityUtils.getUsername());
        projectType.setType(0);
        return toAjax(projectTypeService.insertProjectType(projectType));
    }

    /**
     * 修改项目分类
     */
    @PreAuthorize("@ss.hasPermi('system:projectTypePost:edit')")
    @Log(title = "项目分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProjectTypePost projectType)
    {
        ProjectTypePost info=projectTypeService.selectProjectTypeByName(projectType.getProjectTypeName(),projectType.getProjectTypeId(),projectType.getProjectCode());
        if(info!=null){
            return toAjaxByError("该项目岗位已存在");
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
    @PreAuthorize("@ss.hasPermi('system:projectTypePost:remove')")
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
