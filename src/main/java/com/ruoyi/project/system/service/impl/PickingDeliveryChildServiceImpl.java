package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.system.domain.StockInfo;
import com.ruoyi.project.system.mapper.StockInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.PickingDeliveryChildMapper;
import com.ruoyi.project.system.domain.PickingDeliveryChild;
import com.ruoyi.project.system.service.IPickingDeliveryChildService;

/**
 * 领料出库单子表Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-07
 */
@Service
public class PickingDeliveryChildServiceImpl implements IPickingDeliveryChildService 
{
    @Autowired
    private PickingDeliveryChildMapper pickingDeliveryChildMapper;

    @Autowired
    private StockInfoMapper stockInfoMapper;

    /**
     * 查询领料出库单子表
     * 
     * @param id 领料出库单子表ID
     * @return 领料出库单子表
     */
    @Override
    public PickingDeliveryChild selectPickingDeliveryChildById(String id)
    {
        return pickingDeliveryChildMapper.selectPickingDeliveryChildById(id);
    }

    /**
     * 查询领料出库单子表列表
     * 
     * @param pickingDeliveryChild 领料出库单子表
     * @return 领料出库单子表
     */
    @Override
    public List<PickingDeliveryChild> selectPickingDeliveryChildList(PickingDeliveryChild pickingDeliveryChild)
    {
        return pickingDeliveryChildMapper.selectPickingDeliveryChildList(pickingDeliveryChild);
    }

    /**
     * 新增领料出库单子表
     * 
     * @param pickingDeliveryChild 领料出库单子表
     * @return 结果
     */
    @Override
    public int insertPickingDeliveryChild(PickingDeliveryChild pickingDeliveryChild)
    {
        pickingDeliveryChild.setId(StringUtils.getId());
        pickingDeliveryChild.setCreateTime(DateUtils.getNowDate());
        return pickingDeliveryChildMapper.insertPickingDeliveryChild(pickingDeliveryChild);
    }

    /**
     * 修改领料出库单子表
     * 
     * @param pickingDeliveryChild 领料出库单子表
     * @return 结果
     */
    @Override
    public int updatePickingDeliveryChild(PickingDeliveryChild pickingDeliveryChild)
    {
        pickingDeliveryChild.setUpdateTime(DateUtils.getNowDate());
        return pickingDeliveryChildMapper.updatePickingDeliveryChild(pickingDeliveryChild);
    }

    /**
     * 批量删除领料出库单子表
     * 
     * @param ids 需要删除的领料出库单子表ID
     * @return 结果
     */
    @Override
    public int deletePickingDeliveryChildByIds(String[] ids)
    {
        return pickingDeliveryChildMapper.deletePickingDeliveryChildByIds(ids);
    }

    /**
     * 根据主表ID批量删除领料出库单子表
     *
     * @param ids 需要删除的领料出库单主表ID
     * @return 结果
     */
    @Override
    public int deletePickingDeliveryChildByPIds(Integer[] ids){
        return pickingDeliveryChildMapper.deletePickingDeliveryChildByPIds(ids);
    }
    /**
     * 删除领料出库单子表信息
     * 
     * @param id 领料出库单子表ID
     * @return 结果
     */
    @Override
    public int deletePickingDeliveryChildById(String id)
    {
        PickingDeliveryChild child=pickingDeliveryChildMapper.selectPickingDeliveryChildById(id);
        //添加库存
        StockInfo StockInfo=new StockInfo();
        StockInfo.setDjType(0);
        StockInfo.setRkOrderId(child.getRkOrderId());
        StockInfo.setGoodsCode(child.getGoodsCode());
        StockInfo.setGoodsNum(child.getGoodsNum());
        stockInfoMapper.updateStockInfoByGoodsCode(StockInfo);
        StockInfo=null;
        return pickingDeliveryChildMapper.deletePickingDeliveryChildById(id);
    }
}
