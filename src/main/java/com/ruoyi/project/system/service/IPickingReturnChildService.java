package com.ruoyi.project.system.service;

import java.util.List;
import com.ruoyi.project.system.domain.PickingReturnChild;

/**
 * 借用归还单子表Service接口
 * 
 * @author yu
 * @date 2021-04-12
 */
public interface IPickingReturnChildService 
{
    /**
     * 查询借用归还单子表
     * 
     * @param id 借用归还单子表ID
     * @return 借用归还单子表
     */
    public PickingReturnChild selectPickingReturnChildById(String id);

    /**
     * 查询借用归还单子表列表
     * 
     * @param pickingReturnChild 借用归还单子表
     * @return 借用归还单子表集合
     */
    public List<PickingReturnChild> selectPickingReturnChildList(PickingReturnChild pickingReturnChild);

    /**
     * 新增借用归还单子表
     * 
     * @param pickingReturnChild 借用归还单子表
     * @return 结果
     */
    public int insertPickingReturnChild(PickingReturnChild pickingReturnChild);

    /**
     * 修改借用归还单子表
     * 
     * @param pickingReturnChild 借用归还单子表
     * @return 结果
     */
    public int updatePickingReturnChild(PickingReturnChild pickingReturnChild);

    /**
     * 批量删除借用归还单子表
     * 
     * @param ids 需要删除的借用归还单子表ID
     * @return 结果
     */
    public int deletePickingReturnChildByIds(String[] ids);


    /**
     * 删除借用归还单子表信息
     * 
     * @param id 借用归还单子表ID
     * @return 结果
     */
    public int deletePickingReturnChildById(String id);
}
