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
import com.ruoyi.project.system.domain.SystemFile;
import com.ruoyi.project.system.service.ISystemFileService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 系统文件Controller
 * 
 * @author ruoyi
 * @date 2021-01-25
 */
@RestController
@RequestMapping("/system/systemFile")
public class SystemFileController extends BaseController
{
    @Autowired
    private ISystemFileService systemFileService;

    /**
     * 查询系统文件列表
     */
    @GetMapping("/list")
    public TableDataInfo list(SystemFile systemFile)
    {
        startPage();
        List<SystemFile> list = systemFileService.selectSystemFileList(systemFile);
        return getDataTable(list);
    }

    /**
     * 导出系统文件列表
     */
    @PreAuthorize("@ss.hasPermi('system:systemFile:export')")
    @Log(title = "系统文件", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SystemFile systemFile)
    {
        List<SystemFile> list = systemFileService.selectSystemFileList(systemFile);
        ExcelUtil<SystemFile> util = new ExcelUtil<SystemFile>(SystemFile.class);
        return util.exportExcel(list, "systemFile");
    }

    /**
     * 获取系统文件详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:systemFile:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(systemFileService.selectSystemFileById(id));
    }

    /**
     * 新增系统文件
     */
    @PreAuthorize("@ss.hasPermi('system:systemFile:add')")
    @Log(title = "系统文件", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SystemFile systemFile)
    {
        return toAjax(systemFileService.insertSystemFile(systemFile));
    }

    /**
     * 修改系统文件
     */
    @PreAuthorize("@ss.hasPermi('system:systemFile:edit')")
    @Log(title = "系统文件", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SystemFile systemFile)
    {
        return toAjax(systemFileService.updateSystemFile(systemFile));
    }

    /**
     * 删除系统文件
     */
    @PreAuthorize("@ss.hasPermi('system:systemFile:remove')")
    @Log(title = "系统文件", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(systemFileService.deleteSystemFileByIds(ids));
    }
}
