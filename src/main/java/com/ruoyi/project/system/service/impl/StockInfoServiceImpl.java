package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.StockInfoMapper;
import com.ruoyi.project.system.domain.StockInfo;
import com.ruoyi.project.system.service.IStockInfoService;

/**
 * 库存管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-01
 */
@Service
public class StockInfoServiceImpl implements IStockInfoService 
{
    @Autowired
    private StockInfoMapper stockInfoMapper;

    /**
     * 查询库存管理
     * 
     * @param id 库存管理ID
     * @return 库存管理
     */
    @Override
    public StockInfo selectStockInfoById(Integer id)
    {
        return stockInfoMapper.selectStockInfoById(id);
    }

    /**
     * 查询库存管理列表
     * 
     * @param stockInfo 库存管理
     * @return 库存管理
     */
    @Override
    public List<StockInfo> selectStockInfoList(StockInfo stockInfo)
    {
        return stockInfoMapper.selectStockInfoList(stockInfo);
    }

    /**
     * 出库时查询库存管理列表
     *
     * @param stockInfo 库存管理
     * @return 库存管理集合
     */
    @Override
    public List<StockInfo> selectStockInfoListByCkd(StockInfo stockInfo){
        return stockInfoMapper.selectStockInfoListByCkd(stockInfo);
    }

    /**
     * 新增库存管理
     * 
     * @param stockInfo 库存管理
     * @return 结果
     */
    @Override
    public int insertStockInfo(StockInfo stockInfo)
    {
        stockInfo.setCreateTime(DateUtils.getNowDate());
        return stockInfoMapper.insertStockInfo(stockInfo);
    }

    /**
     * 查询商品是否存在
     *
     * @param goodsCode sh
     * @return 结果
     */
    @Override
    public int checkStockByGoodsCode(String goodsCode){
        return stockInfoMapper.checkStockByGoodsCode(goodsCode);
    }
    /**
     * 修改库存管理
     * 
     * @param stockInfo 库存管理
     * @return 结果
     */
    @Override
    public int updateStockInfo(StockInfo stockInfo)
    {
        stockInfo.setUpdateTime(DateUtils.getNowDate());
        return stockInfoMapper.updateStockInfo(stockInfo);
    }

    /**
     * 修改库存数
     *
     * @param stockInfo 库存管理
     * @return 结果
     */
    @Override
    public int updateStockInfoByGoodsCode(StockInfo stockInfo){
        stockInfo.setUpdateTime(DateUtils.getNowDate());
        return stockInfoMapper.updateStockInfoByGoodsCode(stockInfo);
    }



    /**
     * 批量删除库存管理
     * 
     * @param ids 需要删除的库存管理ID
     * @return 结果
     */
    @Override
    public int deleteStockInfoByIds(Integer[] ids)
    {
        return stockInfoMapper.deleteStockInfoByIds(ids);
    }

    /**
     * 删除库存管理信息
     * 
     * @param id 库存管理ID
     * @return 结果
     */
    @Override
    public int deleteStockInfoById(Integer id)
    {
        return stockInfoMapper.deleteStockInfoById(id);
    }
    /**
     * 根据入库单号删除库存管理信息
     *
     * @param djNumber 入库单号
     * @return 结果
     */
    @Override
    public int deleteStockInfoBydjNumber(String djNumber){
            return stockInfoMapper.deleteStockInfoBydjNumber(djNumber);
    }
}
