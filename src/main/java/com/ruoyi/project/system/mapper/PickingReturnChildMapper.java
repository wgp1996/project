package com.ruoyi.project.system.mapper;

import java.util.List;
import com.ruoyi.project.system.domain.PickingReturnChild;
import com.ruoyi.project.system.domain.PurchaseWareChild;

/**
 * 借用归还单子表Mapper接口
 * 
 * @author yu
 * @date 2021-04-12
 */
public interface PickingReturnChildMapper 
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
     * 借还单查询出库单列表
     *
     * @param pickingReturnChild 子表
     * @return 表集合
     */
    public List<PickingReturnChild> selectPickingDeliveryListByReturn(PickingReturnChild pickingReturnChild);
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
     * 删除借用归还单子表
     * 
     * @param id 借用归还单子表ID
     * @return 结果
     */
    public int deletePickingReturnChildById(String id);

    /**
     * 批量删除借用归还单子表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePickingReturnChildByIds(String[] ids);

    /**
     * 根据主表ID批量删除子表
     *
     * @param ids 需要删除的主表ID
     * @return 结果
     */
    public int deletePickingReturnChildByPIds(Integer[] ids);
}
