package com.ruoyi.project.system.controller;

import java.util.List;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.domain.ProjectContractChild;
import com.ruoyi.project.system.domain.ProjectTypePost;
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
import com.ruoyi.project.system.domain.ProjectInventory;
import com.ruoyi.project.system.service.IProjectInventoryService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 编制清单Controller
 * 
 * @author ruoyi
 * @date 2021-03-23
 */
@RestController
@RequestMapping("/system/projectInventory")
public class ProjectInventoryController extends BaseController
{
    @Autowired
    private IProjectInventoryService projectInventoryService;




    /**
     * 获取部门下拉树列表
     */
    @GetMapping("/treeselect")
    public AjaxResult treeselect(ProjectInventory projectInventory)
    {
        List<ProjectInventory> inventories = projectInventoryService.selectProjectInventoryList(projectInventory);
        return AjaxResult.success(projectInventoryService.buildTreeSelect(inventories));
    }

    /**
     * 查询编制清单列表
     */
    @PreAuthorize("@ss.hasPermi('system:projectInventory:list')")
    @GetMapping("/list")
    public AjaxResult list(ProjectInventory projectInventory)
    {
        List<ProjectInventory> list = projectInventoryService.selectProjectInventoryList(projectInventory);
        return AjaxResult.success(list);
    }

    /**
     * 分包合同选择清单
     * @param projectInventory
     * @return
     */
    @GetMapping("/selectList")
    @DataScope(deptAlias = "d", userAlias = "u")
    public TableDataInfo selectList(ProjectInventory projectInventory)
    {
        startPage();
        List<ProjectInventory> list = projectInventoryService.inventoryList(projectInventory);
        return getDataTable(list);
    }

    /**
     * 导出编制清单列表
     */
    @PreAuthorize("@ss.hasPermi('system:projectInventory:export')")
    @Log(title = "编制清单", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ProjectInventory projectInventory)
    {
        List<ProjectInventory> list = projectInventoryService.selectProjectInventoryList(projectInventory);
        ExcelUtil<ProjectInventory> util = new ExcelUtil<ProjectInventory>(ProjectInventory.class);
        return util.exportExcel(list, "projectInventory");
    }

    /**
     * 获取编制清单详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:projectInventory:query')")
    @GetMapping(value = "/detailInfo/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(projectInventoryService.selectProjectInventoryById(id));
    }

    /**
     * 根据单号获取编制清单详细信息
     */
    @GetMapping(value = "/detail/{djNumber}")
    public AjaxResult getInfoByDjNumber(@PathVariable("djNumber") String djNumber)
    {
        return AjaxResult.success(projectInventoryService.selectProjectInventoryListByDjNumber(djNumber));
    }

    /**
     * 新增编制清单
     */
    //@PreAuthorize("@ss.hasPermi('system:projectInventory:add')")
    @Log(title = "编制清单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProjectInventory projectInventory)
    {
        if(projectInventory.getDjNumber()==null||projectInventory.getDjNumber()==""){
            return toAjaxByError("请先保存主表!");
        }
        projectInventory.setCreateBy(SecurityUtils.getUsername());
        return toAjax(projectInventoryService.insertProjectInventory(projectInventory));
    }

    /**
     * 修改编制清单
     */
    //@PreAuthorize("@ss.hasPermi('system:projectInventory:edit')")
    @Log(title = "编制清单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProjectInventory projectInventory)
    {
        projectInventory.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(projectInventoryService.updateProjectInventory(projectInventory));
    }

    /**
     * 删除编制清单
     */
    @PreAuthorize("@ss.hasPermi('system:projectInventory:remove')")
    @Log(title = "编制清单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable Integer id)
    {
        if(projectInventoryService.hasChildById(id)>0){
            return toAjaxByError("请先删除下级");
        }
        return toAjax(projectInventoryService.deleteProjectInventoryById(id));
    }
}
