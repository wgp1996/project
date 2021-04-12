package com.ruoyi.project.system.mapper;

import java.util.List;
import com.ruoyi.project.system.domain.PickingReturn;

/**
 * 借用归还单Mapper接口
 * 
 * @author xy
 * @date 2021-04-12
 */
public interface PickingReturnMapper 
{
    /**
     * 查询借用归还单
     * 
     * @param id 借用归还单ID
     * @return 借用归还单
     */
    public PickingReturn selectPickingReturnById(Integer id);

    /**
     * 查询借用归还单列表
     * 
     * @param pickingReturn 借用归还单
     * @return 借用归还单集合
     */
    public List<PickingReturn> selectPickingReturnList(PickingReturn pickingReturn);

    /**
     * 查询借用归还单审核列表
     *
     * @param pickingReturn 借用归还单
     * @return 借用归还单集合
     */
    public List<PickingReturn> selectPickingReturnShList(PickingReturn pickingReturn);

    /**
     * 新增借用归还单
     *
     * @param pickingReturn 借用归还单
     * @return 结果
     */
    public int insertPickingReturn(PickingReturn pickingReturn);



    /**
     * 修改审核状态
     *
     * @param djNumber 单号
     * @param status 单据状态
     * @param type 0 修改节点 1 修改状态
     * @return 结果
     */
    public int updatetPickingReturnStatusOrNodeNo(String djNumber,Integer status,int type);

    /**
     * 修改借用归还单
     * 
     * @param pickingReturn 借用归还单
     * @return 结果
     */
    public int updatePickingReturn(PickingReturn pickingReturn);

    /**
     * 删除借用归还单
     * 
     * @param id 借用归还单ID
     * @return 结果
     */
    public int deletePickingReturnById(Integer id);

    /**
     * 批量删除借用归还单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePickingReturnByIds(Integer[] ids);
}
