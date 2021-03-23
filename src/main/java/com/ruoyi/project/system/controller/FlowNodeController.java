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
import com.ruoyi.project.system.domain.FlowNode;
import com.ruoyi.project.system.service.IFlowNodeService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 节点表Controller
 * 
 * @author ruoyi
 * @date 2021-01-25
 */
@RestController
@RequestMapping("/system/flowNode")
public class FlowNodeController extends BaseController
{
    @Autowired
    private IFlowNodeService flowNodeService;

    /**
     * 查询节点表列表
     */
//    @PreAuthorize("@ss.hasPermi('system:flowNode:list')")
    @GetMapping("/list")
    public TableDataInfo list(FlowNode flowNode)
    {
        startPage();
        List<FlowNode> list = flowNodeService.selectFlowNodeList(flowNode);
        return getDataTable(list);
    }

    /**
     * 导出节点表列表
     */
    @PreAuthorize("@ss.hasPermi('system:flowNode:export')")
    @Log(title = "节点表", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(FlowNode flowNode)
    {
        List<FlowNode> list = flowNodeService.selectFlowNodeList(flowNode);
        ExcelUtil<FlowNode> util = new ExcelUtil<FlowNode>(FlowNode.class);
        return util.exportExcel(list, "flowNode");
    }

    /**
     * 获取节点表详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:flowNode:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(flowNodeService.selectFlowNodeById(id));
    }

    /**
     * 新增节点表
     */
    @PreAuthorize("@ss.hasPermi('system:flowNode:add')")
    @Log(title = "节点表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FlowNode flowNode)
    {
        return toAjax(flowNodeService.insertFlowNode(flowNode));
    }

    /**
     * 修改节点表
     */
    @PreAuthorize("@ss.hasPermi('system:flowNode:edit')")
    @Log(title = "节点表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FlowNode flowNode)
    {
        return toAjax(flowNodeService.updateFlowNode(flowNode));
    }

    /**
     * 删除节点表
     */
    @Log(title = "节点表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(flowNodeService.deleteFlowNodeByIds(ids));
    }
}
