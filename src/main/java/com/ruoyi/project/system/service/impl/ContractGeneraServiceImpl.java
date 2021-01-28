package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.ContractGeneraMapper;
import com.ruoyi.project.system.domain.ContractGenera;
import com.ruoyi.project.system.service.IContractGeneraService;

/**
 * 总包合同Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-01-26
 */
@Service
public class ContractGeneraServiceImpl implements IContractGeneraService 
{
    @Autowired
    private ContractGeneraMapper contractGeneraMapper;

    /**
     * 查询总包合同
     * 
     * @param id 总包合同ID
     * @return 总包合同
     */
    @Override
    public ContractGenera selectContractGeneraById(Long id)
    {
        return contractGeneraMapper.selectContractGeneraById(id);
    }
    /**
     * 查询总包合同审核列表
     *
     * @param userId 用户id
     * @param roleId 角色id
     * @param type 0 待审核 1 已审核
     * @return 总包合同集合
     */
    @Override
    public List<ContractGenera> selectContractGeneraShList(ContractGenera contractGenera){
        return contractGeneraMapper.selectContractGeneraShList(contractGenera);
    }
    /**
     * 查询总包合同列表
     * 
     * @param contractGenera 总包合同
     * @return 总包合同
     */
    @Override
    public List<ContractGenera> selectContractGeneraList(ContractGenera contractGenera)
    {
        return contractGeneraMapper.selectContractGeneraList(contractGenera);
    }

    /**
     * 新增总包合同
     * 
     * @param contractGenera 总包合同
     * @return 结果
     */
    @Override
    public int insertContractGenera(ContractGenera contractGenera)
    {
        contractGenera.setCreateTime(DateUtils.getNowDate());
        return contractGeneraMapper.insertContractGenera(contractGenera);
    }

    /**
     * 修改总包合同
     * 
     * @param contractGenera 总包合同
     * @return 结果
     */
    @Override
    public int updateContractGenera(ContractGenera contractGenera)
    {
        contractGenera.setUpdateTime(DateUtils.getNowDate());
        return contractGeneraMapper.updateContractGenera(contractGenera);
    }

    /**
     * 修改总包合同状态
     *
     * @param djNumber 单号
     * @param status 单据状态
     * @param type 0 修改节点 1 修改状态
     * @return 结果
     */
    @Override
    public int updateContractGeneraStatusOrNodeNo(String djNumber,Integer status,int type){
        return contractGeneraMapper.updateContractGeneraStatusOrNodeNo(djNumber,status,type);
    }
    /**
     * 批量删除总包合同
     * 
     * @param ids 需要删除的总包合同ID
     * @return 结果
     */
    @Override
    public int deleteContractGeneraByIds(Long[] ids)
    {
        return contractGeneraMapper.deleteContractGeneraByIds(ids);
    }

    /**
     * 删除总包合同信息
     * 
     * @param id 总包合同ID
     * @return 结果
     */
    @Override
    public int deleteContractGeneraById(Long id)
    {
        return contractGeneraMapper.deleteContractGeneraById(id);
    }
}
