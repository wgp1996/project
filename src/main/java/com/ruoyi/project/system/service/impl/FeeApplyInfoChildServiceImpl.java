package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.FeeApplyInfoChildMapper;
import com.ruoyi.project.system.domain.FeeApplyInfoChild;
import com.ruoyi.project.system.service.IFeeApplyInfoChildService;

/**
 * 费用报销子表Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-16
 */
@Service
public class FeeApplyInfoChildServiceImpl implements IFeeApplyInfoChildService 
{
    @Autowired
    private FeeApplyInfoChildMapper feeApplyInfoChildMapper;

    /**
     * 查询费用报销子表
     * 
     * @param id 费用报销子表ID
     * @return 费用报销子表
     */
    @Override
    public FeeApplyInfoChild selectFeeApplyInfoChildById(String id)
    {
        return feeApplyInfoChildMapper.selectFeeApplyInfoChildById(id);
    }

    /**
     * 查询费用报销子表列表
     * 
     * @param feeApplyInfoChild 费用报销子表
     * @return 费用报销子表
     */
    @Override
    public List<FeeApplyInfoChild> selectFeeApplyInfoChildList(FeeApplyInfoChild feeApplyInfoChild)
    {
        return feeApplyInfoChildMapper.selectFeeApplyInfoChildList(feeApplyInfoChild);
    }

    /**
     * 新增费用报销子表
     * 
     * @param feeApplyInfoChild 费用报销子表
     * @return 结果
     */
    @Override
    public int insertFeeApplyInfoChild(FeeApplyInfoChild feeApplyInfoChild)
    {
        feeApplyInfoChild.setCreateTime(DateUtils.getNowDate());
        feeApplyInfoChild.setId(StringUtils.getId());
        return feeApplyInfoChildMapper.insertFeeApplyInfoChild(feeApplyInfoChild);
    }

    /**
     * 修改费用报销子表
     * 
     * @param feeApplyInfoChild 费用报销子表
     * @return 结果
     */
    @Override
    public int updateFeeApplyInfoChild(FeeApplyInfoChild feeApplyInfoChild)
    {
        feeApplyInfoChild.setUpdateTime(DateUtils.getNowDate());
        return feeApplyInfoChildMapper.updateFeeApplyInfoChild(feeApplyInfoChild);
    }

    /**
     * 批量删除费用报销子表
     * 
     * @param ids 需要删除的费用报销子表ID
     * @return 结果
     */
    @Override
    public int deleteFeeApplyInfoChildByIds(String[] ids)
    {
        return feeApplyInfoChildMapper.deleteFeeApplyInfoChildByIds(ids);
    }

    /**
     * 删除费用报销子表信息
     * 
     * @param id 费用报销子表ID
     * @return 结果
     */
    @Override
    public int deleteFeeApplyInfoChildById(String id)
    {
        return feeApplyInfoChildMapper.deleteFeeApplyInfoChildById(id);
    }
}
