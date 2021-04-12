package com.ruoyi.project.system.controller;

import java.util.List;

import com.ruoyi.project.system.service.*;
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
import com.ruoyi.project.system.domain.PickingDeliveryChild;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 领料出库单子表Controller
 * 
 * @author ruoyi
 * @date 2021-04-07
 */
@RestController
@RequestMapping("/system/pickingDeliveryChild")
public class PickingDeliveryChildController extends BaseController
{
    @Autowired
    private IPickingDeliveryChildService pickingDeliveryChildService;
    @Autowired
    private ISystemFileService systemFileService;
    @Autowired
    private IFlowInfoService flowInfoService;
    @Autowired
    private IFlowNodeService flowNodeService;
    @Autowired
    private IFlowAuditService flowAuditService;

    /**
     * 查询领料出库单子表列表
     */
    @PreAuthorize("@ss.hasPermi('system:pickingDeliveryChild:list')")
    @GetMapping("/list")
    public TableDataInfo list(PickingDeliveryChild pickingDeliveryChild)
    {
        startPage();
        List<PickingDeliveryChild> list = pickingDeliveryChildService.selectPickingDeliveryChildList(pickingDeliveryChild);
        return getDataTable(list);
    }

    /**
     * 导出领料出库单子表列表
     */
    @PreAuthorize("@ss.hasPermi('system:pickingDeliveryChild:export')")
    @Log(title = "领料出库单子表", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(PickingDeliveryChild pickingDeliveryChild)
    {
        List<PickingDeliveryChild> list = pickingDeliveryChildService.selectPickingDeliveryChildList(pickingDeliveryChild);
        ExcelUtil<PickingDeliveryChild> util = new ExcelUtil<PickingDeliveryChild>(PickingDeliveryChild.class);
        return util.exportExcel(list, "pickingDeliveryChild");
    }

    /**
     * 获取领料出库单子表详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:pickingDeliveryChild:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(pickingDeliveryChildService.selectPickingDeliveryChildById(id));
    }

    /**
     * 新增领料出库单子表
     */
    @PreAuthorize("@ss.hasPermi('system:pickingDeliveryChild:add')")
    @Log(title = "领料出库单子表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PickingDeliveryChild pickingDeliveryChild)
    {
        return toAjax(pickingDeliveryChildService.insertPickingDeliveryChild(pickingDeliveryChild));
    }

    /**
     * 修改领料出库单子表
     */
    @PreAuthorize("@ss.hasPermi('system:pickingDeliveryChild:edit')")
    @Log(title = "领料出库单子表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PickingDeliveryChild pickingDeliveryChild)
    {
        return toAjax(pickingDeliveryChildService.updatePickingDeliveryChild(pickingDeliveryChild));
    }

    /**
     * 删除领料出库单子表
     */
    @PreAuthorize("@ss.hasPermi('system:pickingDeliveryChild:remove')")
    @Log(title = "领料出库单子表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(pickingDeliveryChildService.deletePickingDeliveryChildByIds(ids));
    }
}
