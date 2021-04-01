package com.ruoyi.project.system.mapper;

import java.util.List;
import com.ruoyi.project.system.domain.StockInfo;

/**
 * 库存管理Mapper接口
 * 
 * @author ruoyi
 * @date 2021-04-01
 */
public interface StockInfoMapper 
{
    /**
     * 查询库存管理
     * 
     * @param id 库存管理ID
     * @return 库存管理
     */
    public StockInfo selectStockInfoById(Integer id);

    /**
     * 查询库存管理列表
     * 
     * @param stockInfo 库存管理
     * @return 库存管理集合
     */
    public List<StockInfo> selectStockInfoList(StockInfo stockInfo);

    /**
     * 新增库存管理
     * 
     * @param stockInfo 库存管理
     * @return 结果
     */
    public int insertStockInfo(StockInfo stockInfo);

    /**
     * 修改库存管理
     * 
     * @param stockInfo 库存管理
     * @return 结果
     */
    public int updateStockInfo(StockInfo stockInfo);

    /**
     * 删除库存管理
     * 
     * @param id 库存管理ID
     * @return 结果
     */
    public int deleteStockInfoById(Integer id);

    /**
     * 批量删除库存管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteStockInfoByIds(Integer[] ids);

    /**
     * 根据入库单号删除库存管理信息
     *
     * @param djNumber 入库单号
     * @return 结果
     */
    public int deleteStockInfoBydjNumber(String djNumber);
}
