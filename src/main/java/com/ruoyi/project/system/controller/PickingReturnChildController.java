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
import com.ruoyi.project.system.domain.PickingReturnChild;
import com.ruoyi.project.system.service.IPickingReturnChildService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 借用归还单子表Controller
 * 
 * @author yu
 * @date 2021-04-12
 */
@RestController
@RequestMapping("/system/pickingReturnChild")
public class PickingReturnChildController extends BaseController
{
    @Autowired
    private IPickingReturnChildService pickingReturnChildService;

    /**
     * 查询借用归还单子表列表
     */
    @PreAuthorize("@ss.hasPermi('system:pickingReturnChild:list')")
    @GetMapping("/list")
    public TableDataInfo list(PickingReturnChild pickingReturnChild)
    {
        startPage();
        List<PickingReturnChild> list = pickingReturnChildService.selectPickingReturnChildList(pickingReturnChild);
        return getDataTable(list);
    }

    /**
     * 导出借用归还单子表列表
     */
    @PreAuthorize("@ss.hasPermi('system:pickingReturnChild:export')")
    @Log(title = "借用归还单子表", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(PickingReturnChild pickingReturnChild)
    {
        List<PickingReturnChild> list = pickingReturnChildService.selectPickingReturnChildList(pickingReturnChild);
        ExcelUtil<PickingReturnChild> util = new ExcelUtil<PickingReturnChild>(PickingReturnChild.class);
        return util.exportExcel(list, "pickingReturnChild");
    }

    /**
     * 获取借用归还单子表详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:pickingReturnChild:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(pickingReturnChildService.selectPickingReturnChildById(id));
    }

    /**
     * 新增借用归还单子表
     */
    @PreAuthorize("@ss.hasPermi('system:pickingReturnChild:add')")
    @Log(title = "借用归还单子表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PickingReturnChild pickingReturnChild)
    {
        return toAjax(pickingReturnChildService.insertPickingReturnChild(pickingReturnChild));
    }

    /**
     * 修改借用归还单子表
     */
    @PreAuthorize("@ss.hasPermi('system:pickingReturnChild:edit')")
    @Log(title = "借用归还单子表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PickingReturnChild pickingReturnChild)
    {
        return toAjax(pickingReturnChildService.updatePickingReturnChild(pickingReturnChild));
    }

    /**
     * 删除借用归还单子表
     */
    @PreAuthorize("@ss.hasPermi('system:pickingReturnChild:remove')")
    @Log(title = "借用归还单子表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(pickingReturnChildService.deletePickingReturnChildByIds(ids));
    }
}
