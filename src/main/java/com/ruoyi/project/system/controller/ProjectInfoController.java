package com.ruoyi.project.system.controller;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.project.system.domain.ProjectUser;
import com.ruoyi.project.system.domain.SystemFile;
import com.ruoyi.project.system.service.IProjectUserService;
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
import com.ruoyi.project.system.domain.ProjectInfo;
import com.ruoyi.project.system.service.IProjectInfoService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 项目建档Controller
 * 
 * @author ruoyi
 * @date 2021-01-22
 */
@RestController
@RequestMapping("/system/projectInfo")
public class ProjectInfoController extends BaseController
{
    @Autowired
    private IProjectInfoService projectInfoService;
    @Autowired
    private IProjectUserService projectUserService;
    @Autowired
    private ISystemFileService systemFileService;

    /**
     * 查询项目建档列表
     */
    @PreAuthorize("@ss.hasPermi('system:projectInfo:list')")
    @GetMapping("/list")
    @DataScope(deptAlias = "d", userAlias = "u")
    public TableDataInfo list(ProjectInfo projectInfo)
    {
        startPage();
        List<ProjectInfo> list = projectInfoService.selectProjectInfoList(projectInfo);
        for(ProjectInfo info:list){
            ProjectUser user=new ProjectUser();
            user.setProjectCode(info.getProjectCode());
            info.setChildrenList(projectUserService.selectProjectUserList(user));
            user=null;
        }
        return getDataTable(list);
    }

    /**
     * 总包选择项目建档列表
     *
     * @param projectInfo 项目建档
     * @return 项目建档集合
     */
    @GetMapping("/zbList")
    @DataScope(deptAlias = "d", userAlias = "u")
    public TableDataInfo zbList(ProjectInfo projectInfo)
    {
        startPage();
        List<ProjectInfo> list = projectInfoService.selectProjectInfoListByZb(projectInfo);
        return getDataTable(list);
    }
    /**
     * 查询项目人员列表
     */
    @GetMapping("/userList")
    public TableDataInfo userList(ProjectUser projectUser)
    {
        startPage();
        List<ProjectUser> list = projectUserService.selectProjectUserList(projectUser);
        return getDataTable(list);
    }

    /**
     * 导出项目建档列表
     */
    @PreAuthorize("@ss.hasPermi('system:projectInfo:export')")
    @Log(title = "项目建档", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ProjectInfo projectInfo)
    {
        List<ProjectInfo> list = projectInfoService.selectProjectInfoList(projectInfo);
        ExcelUtil<ProjectInfo> util = new ExcelUtil<ProjectInfo>(ProjectInfo.class);
        return util.exportExcel(list, "projectInfo");
    }

    /**
     * 获取项目建档详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:projectInfo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(projectInfoService.selectProjectInfoById(id));
    }


    /**
     * 根据编码获取项目建档详细信息
     */
    @GetMapping(value = "/getInfoByCode/{code}")
    public AjaxResult getInfoByCode(@PathVariable("code") String code)
    {
        return AjaxResult.success(projectInfoService.selectProjectInfoByCode(code));
    }
    /**
     * 新增项目建档
     */
    @PreAuthorize("@ss.hasPermi('system:projectInfo:add')")
    @Log(title = "项目建档", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProjectInfo projectInfo)
    {
        if(projectInfoService.checkProjectCode(projectInfo.getProjectCode(),-1)>0){
            return toAjaxByError("项目编码重复!");
        }
//        if(projectInfo.getRows()==""){
//            return  toAjaxByError("人员信息不能为空!");
//        }
        //插入人员
        if(projectInfo.getRows()!=null&&projectInfo.getRows()!="") {
            List<ProjectUser> childList = JSONArray.parseArray(projectInfo.getRows(), ProjectUser.class);
            for (ProjectUser child : childList) {
                child.setCreateBy(SecurityUtils.getUsername());
                child.setProjectCode(projectInfo.getProjectCode());
                child.setProjectName(projectInfo.getProjectName());
                child.setCreateTime(DateUtils.getNowDate());
                projectUserService.insertProjectUser(child);
            }
        }
        //插入附件
        if(projectInfo.getFileRows()!=null&&projectInfo.getFileRows()!="") {
            List<SystemFile> childList = JSONArray.parseArray(projectInfo.getFileRows(), SystemFile.class);
            for (SystemFile child : childList) {
                child.setCode(projectInfo.getProjectCode());
                child.setCreateBy(SecurityUtils.getUsername());
                systemFileService.insertSystemFile(child);
            }
        }
        projectInfo.setCreateBy(SecurityUtils.getUsername());
        return toAjax(projectInfoService.insertProjectInfo(projectInfo));
    }

    /**
     * 修改项目建档
     */
    @PreAuthorize("@ss.hasPermi('system:projectInfo:edit')")
    @Log(title = "项目建档", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProjectInfo projectInfo)
    {
        if(projectInfoService.checkProjectCode(projectInfo.getProjectCode(),projectInfo.getId())>0){
            return toAjaxByError("项目编码重复!");
        }
//        if(projectInfo.getRows()==""){
//            return  toAjaxByError("人员信息不能为空!");
//        }
        if(projectInfo.getRows()!=null&&projectInfo.getRows()!="") {
            List<ProjectUser> childList = JSONArray.parseArray(projectInfo.getRows(), ProjectUser.class);
            for (ProjectUser child : childList) {
                if (child.getId() != null) {
                    child.setProjectCode(projectInfo.getProjectCode());
                    child.setProjectName(projectInfo.getProjectName());
                    child.setCreateBy(SecurityUtils.getUsername());
                    projectUserService.updateProjectUser(child);
                } else {
                    child.setCreateBy(SecurityUtils.getUsername());
                    child.setProjectCode(projectInfo.getProjectCode());
                    child.setProjectName(projectInfo.getProjectName());
                    child.setCreateTime(DateUtils.getNowDate());
                    projectUserService.insertProjectUser(child);
                }
            }
        }

        //插入附件
        if(projectInfo.getFileRows()!=null&&projectInfo.getFileRows()!="") {
            List<SystemFile> childList = JSONArray.parseArray(projectInfo.getFileRows(), SystemFile.class);
            for (SystemFile child : childList) {
                if (child.getId() != null) {

                }else{
                    child.setCode(projectInfo.getProjectCode());
                    child.setCreateBy(SecurityUtils.getUsername());
                    systemFileService.insertSystemFile(child);
                }
            }
        }
        projectInfo.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(projectInfoService.updateProjectInfo(projectInfo));
    }

    /**
     * 删除项目建档
     */
    @PreAuthorize("@ss.hasPermi('system:projectInfo:remove')")
    @Log(title = "项目建档", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        //检测人员 岗位 预算信息
        if(projectInfoService.checkProjectChild(ids)>0){
            return toAjaxByError("请先删除项目相关的人员、岗位、预算等信息!");
        }
        return toAjax(projectInfoService.deleteProjectInfoByIds(ids));
    }
}
