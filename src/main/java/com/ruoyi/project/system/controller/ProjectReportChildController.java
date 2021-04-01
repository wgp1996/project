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
import com.ruoyi.project.system.domain.ProjectReportChild;
import com.ruoyi.project.system.service.IProjectReportChildService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 产值明细Controller
 * 
 * @author ruoyi
 * @date 2021-03-26
 */
@RestController
@RequestMapping("/system/projectReportChild")
public class ProjectReportChildController extends BaseController
{
    @Autowired
    private IProjectReportChildService projectReportChildService;

    /**
     * 查询产值明细列表
     */
    @GetMapping("/list")
    public TableDataInfo list(ProjectReportChild projectReportChild)
    {
        if(projectReportChild.getDjNumber()==null||projectReportChild.getDjNumber()==""){
            projectReportChild.setDjNumber("-1");
        }
        startPage();
        List<ProjectReportChild> list = projectReportChildService.selectProjectReportChildList(projectReportChild);
        return getDataTable(list);
    }

    /**
     * 导出产值明细列表
     */
    @PreAuthorize("@ss.hasPermi('system:projectReportChild:export')")
    @Log(title = "产值明细", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ProjectReportChild projectReportChild)
    {
        List<ProjectReportChild> list = projectReportChildService.selectProjectReportChildList(projectReportChild);
        ExcelUtil<ProjectReportChild> util = new ExcelUtil<ProjectReportChild>(ProjectReportChild.class);
        return util.exportExcel(list, "projectReportChild");
    }

    /**
     * 获取产值明细详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:projectReportChild:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(projectReportChildService.selectProjectReportChildById(id));
    }

    /**
     * 新增产值明细
     */
    @PreAuthorize("@ss.hasPermi('system:projectReportChild:add')")
    @Log(title = "产值明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProjectReportChild projectReportChild)
    {
        return toAjax(projectReportChildService.insertProjectReportChild(projectReportChild));
    }

    /**
     * 修改产值明细
     */
    @PreAuthorize("@ss.hasPermi('system:projectReportChild:edit')")
    @Log(title = "产值明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProjectReportChild projectReportChild)
    {
        return toAjax(projectReportChildService.updateProjectReportChild(projectReportChild));
    }

    /**
     * 删除产值明细
     */
    @PreAuthorize("@ss.hasPermi('system:projectReportChild:remove')")
    @Log(title = "产值明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(projectReportChildService.deleteProjectReportChildByIds(ids));
    }
}
