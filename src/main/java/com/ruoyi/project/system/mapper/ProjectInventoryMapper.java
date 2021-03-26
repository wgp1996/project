package com.ruoyi.project.system.mapper;

import java.util.List;
import com.ruoyi.project.system.domain.ProjectInventory;

/**
 * 编制清单Mapper接口
 * 
 * @author ruoyi
 * @date 2021-03-23
 */
public interface ProjectInventoryMapper 
{
    /**
     * 查询编制清单
     * 
     * @param id 编制清单ID
     * @return 编制清单
     */
    public ProjectInventory selectProjectInventoryById(Integer id);
    /**
     * 查询编制清单
     *
     * @param djNumber 编制清单单号
     * @return 编制清单
     */
    public List<ProjectInventory> selectProjectInventoryListByDjNumber(String djNumber);
    /**
     * 查询编制清单列表
     * 
     * @param projectInventory 编制清单
     * @return 编制清单集合
     */
    public List<ProjectInventory> selectProjectInventoryList(ProjectInventory projectInventory);

    /**
     * 分包合同选择编制清单列表
     *
     * @param projectInventory 编制清单
     * @return 编制清单集合
     */
    public List<ProjectInventory> inventoryList(ProjectInventory projectInventory);
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
     * 删除编制清单
     * 
     * @param id 编制清单ID
     * @return 结果
     */
    public int deleteProjectInventoryById(Integer id);

    /**
     * 批量删除编制清单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteProjectInventoryByIds(Integer[] ids);
    /**
     * 删除编制清单信息
     *
     * @param id 编制清单ID
     * @return 结果
     */
    public int deleteProjectInventoryByPId(Integer id);
}
