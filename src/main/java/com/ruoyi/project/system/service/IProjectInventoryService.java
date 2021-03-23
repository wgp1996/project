package com.ruoyi.project.system.service;

import java.util.List;

import com.ruoyi.framework.web.domain.TreeSelect;
import com.ruoyi.project.system.domain.ProjectInventory;
import com.ruoyi.project.system.domain.ProjectTypePost;

/**
 * 编制清单Service接口
 * 
 * @author ruoyi
 * @date 2021-03-23
 */
public interface IProjectInventoryService 
{
    /**
     * 查询编制清单
     * 
     * @param id 编制清单ID
     * @return 编制清单
     */
    public ProjectInventory selectProjectInventoryById(Integer id);

    /**
     * 查询编制清单列表
     * 
     * @param projectInventory 编制清单
     * @return 编制清单集合
     */
    public List<ProjectInventory> selectProjectInventoryList(ProjectInventory projectInventory);


    /**
     * 构建前端所需要下拉树结构
     *
     * @param projectInventorys 列表
     * @return 下拉树结构列表
     */
    public List<TreeSelect> buildTreeSelect(List<ProjectInventory> projectInventorys);
    /**
     * 构建前端所需要下拉树结构
     *
     * @param projectInventorys 列表
     * @return 下拉树结构列表
     */
    public List<ProjectInventory> buildTree(List<ProjectInventory> projectInventorys);
    /**
     * 新增编制清单
     * 
     * @param projectInventory 编制清单
     * @return 结果
     */
    public int insertProjectInventory(ProjectInventory projectInventory);

    /**
     * 修改编制清单
     * 
     * @param projectInventory 编制清单
     * @return 结果
     */
    public int updateProjectInventory(ProjectInventory projectInventory);

    /**
     * 批量删除编制清单
     * 
     * @param ids 需要删除的编制清单ID
     * @return 结果
     */
    public int deleteProjectInventoryByIds(Integer[] ids);

    /**
     * 删除编制清单信息
     * 
     * @param id 编制清单ID
     * @return 结果
     */
    public int deleteProjectInventoryById(Integer id);
}
