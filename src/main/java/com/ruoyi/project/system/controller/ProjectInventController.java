package com.ruoyi.project.system.controller;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.project.system.domain.ProjectInventory;
import com.ruoyi.project.system.domain.SystemFile;
import com.ruoyi.project.system.service.ISystemFileService;
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
import com.ruoyi.project.system.domain.ProjectInvent;
import com.ruoyi.project.system.service.IProjectInventService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 预算编制Controller
 * 
 * @author ruoyi
 * @date 2021-03-24
 */
@RestController
@RequestMapping("/system/projectInvent")
public class ProjectInventController extends BaseController
{
    @Autowired
    private IProjectInventService projectInventService;
    @Autowired
    private ISystemFileService systemFileService;
    /**
     * 查询预算编制列表
     */
    @PreAuthorize("@ss.hasPermi('system:projectInvent:list')")
    @GetMapping("/list")
    @DataScope(deptAlias = "d", userAlias = "u")
    public TableDataInfo list(ProjectInvent projectInvent)
    {
        startPage();
        List<ProjectInvent> list = projectInventService.selectProjectInventList(projectInvent);
        return getDataTable(list);
    }


    /**
     * 导出预算编制列表
     */
    @PreAuthorize("@ss.hasPermi('system:projectInvent:export')")
    @Log(title = "预算编制", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ProjectInvent projectInvent)
    {
        List<ProjectInvent> list = projectInventService.selectProjectInventList(projectInvent);
        ExcelUtil<ProjectInvent> util = new ExcelUtil<ProjectInvent>(ProjectInvent.class);
        return util.exportExcel(list, "projectInvent");
    }

    /**
     * 获取预算编制详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:projectInvent:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(projectInventService.selectProjectInventById(id));
    }

    /**
     * 新增/编辑预算编制
     */
    @PreAuthorize("@ss.hasPermi('system:projectInvent:add')")
    @Log(title = "预算编制", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProjectInvent projectInvent)
    {
        if(projectInvent.getProjectCode()==null){
            return toAjaxByError("项目不能为空!");
        }
        if(projectInvent.getId()!=null){
            if(projectInvent.getStatus()>0){
                return toAjaxByError("该状态禁止修改!");
            }
            //插入附件
            if(projectInvent.getFileRows()!=null&&projectInvent.getFileRows()!="") {
                List<SystemFile> childList = JSONArray.parseArray(projectInvent.getFileRows(), SystemFile.class);
                for (SystemFile child : childList) {
                    if (child.getId() != null) {

                    }else{
                        child.setCode(projectInvent.getDjNumber());
                        child.setCreateBy(SecurityUtils.getUsername());
                        systemFileService.insertSystemFile(child);
                    }
                }
            }
            projectInvent.setUpdateBy(SecurityUtils.getUsername());
            projectInventService.updateProjectInvent(projectInvent);
            return AjaxResult.success(projectInvent);
        }else{
            projectInvent.setDjNumber(StringUtils.getRandomCode("PI"));
            if(projectInvent.getFileRows()!=null&&projectInvent.getFileRows()!="") {
                List<SystemFile> childList = JSONArray.parseArray(projectInvent.getFileRows(), SystemFile.class);
                for (SystemFile child : childList) {
                    child.setCode(projectInvent.getDjNumber());
                    child.setCreateBy(SecurityUtils.getUsername());
                    systemFileService.insertSystemFile(child);
                }
            }
            projectInvent.setStatus(0);
            projectInvent.setCreateBy(SecurityUtils.getUsername());
            projectInventService.insertProjectInvent(projectInvent);
            return AjaxResult.success(projectInvent);
        }
    }

    /**
     * 修改预算编制
     */
    @PreAuthorize("@ss.hasPermi('system:projectInvent:edit')")
    @Log(title = "预算编制", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProjectInvent projectInvent)
    {
        return toAjax(projectInventService.updateProjectInvent(projectInvent));
    }

    /**
     * 删除预算编制
     */
    @PreAuthorize("@ss.hasPermi('system:projectInvent:remove')")
    @Log(title = "预算编制", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(projectInventService.deleteProjectInventByIds(ids));
    }
}
