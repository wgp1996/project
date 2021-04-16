package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.FeeInfoMapper;
import com.ruoyi.project.system.domain.FeeInfo;
import com.ruoyi.project.system.service.IFeeInfoService;

/**
 * 费用项目Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-16
 */
@Service
public class FeeInfoServiceImpl implements IFeeInfoService 
{
    @Autowired
    private FeeInfoMapper feeInfoMapper;

    /**
     * 查询费用项目
     * 
     * @param id 费用项目ID
     * @return 费用项目
     */
    @Override
    public FeeInfo selectFeeInfoById(Integer id)
    {
        return feeInfoMapper.selectFeeInfoById(id);
    }

    /**
     * 查询费用项目列表
     * 
     * @param feeInfo 费用项目
     * @return 费用项目
     */
    @Override
    public List<FeeInfo> selectFeeInfoList(FeeInfo feeInfo)
    {
        return feeInfoMapper.selectFeeInfoList(feeInfo);
    }

    /**
     * 新增费用项目
     * 
     * @param feeInfo 费用项目
     * @return 结果
     */
    @Override
    public int insertFeeInfo(FeeInfo feeInfo)
    {
        feeInfo.setCreateTime(DateUtils.getNowDate());
        return feeInfoMapper.insertFeeInfo(feeInfo);
    }

    /**
     * 修改费用项目
     * 
     * @param feeInfo 费用项目
     * @return 结果
     */
    @Override
    public int updateFeeInfo(FeeInfo feeInfo)
    {
        feeInfo.setUpdateTime(DateUtils.getNowDate());
        return feeInfoMapper.updateFeeInfo(feeInfo);
    }

    /**
     * 批量删除费用项目
     * 
     * @param ids 需要删除的费用项目ID
     * @return 结果
     */
    @Override
    public int deleteFeeInfoByIds(Integer[] ids)
    {
        return feeInfoMapper.deleteFeeInfoByIds(ids);
    }

    /**
     * 删除费用项目信息
     * 
     * @param id 费用项目ID
     * @return 结果
     */
    @Override
    public int deleteFeeInfoById(Integer id)
    {
        return feeInfoMapper.deleteFeeInfoById(id);
    }
}
