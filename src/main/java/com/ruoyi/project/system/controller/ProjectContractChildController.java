package com.ruoyi.project.system.controller;

import java.util.List;

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
import com.ruoyi.project.system.domain.ProjectContractChild;
import com.ruoyi.project.system.service.IProjectContractChildService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 分包合同明细Controller
 * 
 * @author ruoyi
 * @date 2021-03-24
 */
@RestController
@RequestMapping("/system/projectContractChild")
public class ProjectContractChildController extends BaseController
{
    @Autowired
    private IProjectContractChildService projectContractChildService;

    /**
     * 查询分包合同明细列表
     */
//    @PreAuthorize("@ss.hasPermi('system:projectContractChild:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProjectContractChild projectContractChild)
    {
        if(projectContractChild.getDjNumber()==null||projectContractChild.getDjNumber()==""){
            projectContractChild.setDjNumber("-1");
        }
       // startPage();
        List<ProjectContractChild> list = projectContractChildService.selectProjectContractChildList(projectContractChild);
        return getDataTable(list);
    }


    /**
     * 导出分包合同明细列表
     */
    @PreAuthorize("@ss.hasPermi('system:projectContractChild:export')")
    @Log(title = "分包合同明细", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ProjectContractChild projectContractChild)
    {
        List<ProjectContractChild> list = projectContractChildService.selectProjectContractChildList(projectContractChild);
        ExcelUtil<ProjectContractChild> util = new ExcelUtil<ProjectContractChild>(ProjectContractChild.class);
        return util.exportExcel(list, "projectContractChild");
    }

    /**
     * 获取分包合同明细详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:projectContractChild:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(projectContractChildService.selectProjectContractChildById(id));
    }

    /**
     * 新增分包合同明细
     */
    @PreAuthorize("@ss.hasPermi('system:projectContractChild:add')")
    @Log(title = "分包合同明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProjectContractChild projectContractChild)
    {
        return toAjax(projectContractChildService.insertProjectContractChild(projectContractChild));
    }

    /**
     * 修改分包合同明细
     */
    @PreAuthorize("@ss.hasPermi('system:projectContractChild:edit')")
    @Log(title = "分包合同明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProjectContractChild projectContractChild)
    {
        return toAjax(projectContractChildService.updateProjectContractChild(projectContractChild));
    }

    /**
     * 删除分包合同明细
     */
    @PreAuthorize("@ss.hasPermi('system:projectContractChild:remove')")
    @Log(title = "分包合同明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(projectContractChildService.deleteProjectContractChildByIds(ids));
    }
}
