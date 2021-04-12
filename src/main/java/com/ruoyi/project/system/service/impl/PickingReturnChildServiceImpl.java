package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.PickingReturnChildMapper;
import com.ruoyi.project.system.domain.PickingReturnChild;
import com.ruoyi.project.system.service.IPickingReturnChildService;

/**
 * 借用归还单子表Service业务层处理
 * 
 * @author yu
 * @date 2021-04-12
 */
@Service
public class PickingReturnChildServiceImpl implements IPickingReturnChildService 
{
    @Autowired
    private PickingReturnChildMapper pickingReturnChildMapper;

    /**
     * 查询借用归还单子表
     * 
     * @param id 借用归还单子表ID
     * @return 借用归还单子表
     */
    @Override
    public PickingReturnChild selectPickingReturnChildById(String id)
    {
        return pickingReturnChildMapper.selectPickingReturnChildById(id);
    }

    /**
     * 查询借用归还单子表列表
     * 
     * @param pickingReturnChild 借用归还单子表
     * @return 借用归还单子表
     */
    @Override
    public List<PickingReturnChild> selectPickingReturnChildList(PickingReturnChild pickingReturnChild)
    {
        return pickingReturnChildMapper.selectPickingReturnChildList(pickingReturnChild);
    }



    /**
     * 新增借用归还单子表
     * 
     * @param pickingReturnChild 借用归还单子表
     * @return 结果
     */
    @Override
    public int insertPickingReturnChild(PickingReturnChild pickingReturnChild)
    {
        pickingReturnChild.setId(StringUtils.getId());
        pickingReturnChild.setCreateTime(DateUtils.getNowDate());
        return pickingReturnChildMapper.insertPickingReturnChild(pickingReturnChild);
    }

    /**
     * 修改借用归还单子表
     * 
     * @param pickingReturnChild 借用归还单子表
     * @return 结果
     */
    @Override
    public int updatePickingReturnChild(PickingReturnChild pickingReturnChild)
    {
        pickingReturnChild.setUpdateTime(DateUtils.getNowDate());
        return pickingReturnChildMapper.updatePickingReturnChild(pickingReturnChild);
    }

    /**
     * 批量删除借用归还单子表
     * 
     * @param ids 需要删除的借用归还单子表ID
     * @return 结果
     */
    @Override
    public int deletePickingReturnChildByIds(String[] ids)
    {
        return pickingReturnChildMapper.deletePickingReturnChildByIds(ids);
    }

    /**
     * 删除借用归还单子表信息
     * 
     * @param id 借用归还单子表ID
     * @return 结果
     */
    @Override
    public int deletePickingReturnChildById(String id)
    {
        return pickingReturnChildMapper.deletePickingReturnChildById(id);
    }
}
