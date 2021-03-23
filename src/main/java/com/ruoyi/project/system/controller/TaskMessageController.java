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
import com.ruoyi.project.system.domain.TaskMessage;
import com.ruoyi.project.system.service.ITaskMessageService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 任务对话详情Controller
 * 
 * @author ruoyi
 * @date 2021-03-09
 */
@RestController
@RequestMapping("/system/taskMessage")
public class TaskMessageController extends BaseController
{
    @Autowired
    private ITaskMessageService taskMessageService;

    /**
     * 查询任务对话详情列表
     */
   /* @PreAuthorize("@ss.hasPermi('system:taskMessage:list')")*/
    @GetMapping("/list")
    public TableDataInfo list(TaskMessage taskMessage)
    {
        //startPage();
        List<TaskMessage> list = taskMessageService.selectTaskMessageList(taskMessage);
        return getDataTable(list);
    }

    /**
     * 导出任务对话详情列表
     */
    @PreAuthorize("@ss.hasPermi('system:taskMessage:export')")
    @Log(title = "任务对话详情", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TaskMessage taskMessage)
    {
        List<TaskMessage> list = taskMessageService.selectTaskMessageList(taskMessage);
        ExcelUtil<TaskMessage> util = new ExcelUtil<TaskMessage>(TaskMessage.class);
        return util.exportExcel(list, "taskMessage");
    }

    /**
     * 获取任务对话详情详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:taskMessage:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(taskMessageService.selectTaskMessageById(id));
    }

    /**
     * 新增任务对话详情
     */
   /* @PreAuthorize("@ss.hasPermi('system:taskMessage:add')")*/
    @Log(title = "任务对话详情", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TaskMessage taskMessage)
    {
        return toAjax(taskMessageService.insertTaskMessage(taskMessage));
    }

    /**
     * 修改任务对话详情
     */
    @PreAuthorize("@ss.hasPermi('system:taskMessage:edit')")
    @Log(title = "任务对话详情", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TaskMessage taskMessage)
    {
        return toAjax(taskMessageService.updateTaskMessage(taskMessage));
    }

    /**
     * 删除任务对话详情
     */
    @PreAuthorize("@ss.hasPermi('system:taskMessage:remove')")
    @Log(title = "任务对话详情", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(taskMessageService.deleteTaskMessageByIds(ids));
    }
}
