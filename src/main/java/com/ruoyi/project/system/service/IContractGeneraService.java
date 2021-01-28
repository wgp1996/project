package com.ruoyi.project.system.service;

import java.util.List;
import com.ruoyi.project.system.domain.ContractGenera;

/**
 * 总包合同Service接口
 * 
 * @author ruoyi
 * @date 2021-01-26
 */
public interface IContractGeneraService 
{
    /**
     * 查询总包合同
     * 
     * @param id 总包合同ID
     * @return 总包合同
     */
    public ContractGenera selectContractGeneraById(Long id);

    /**
     * 查询总包合同列表
     * 
     * @param contractGenera 总包合同
     * @return 总包合同集合
     */
    public List<ContractGenera> selectContractGeneraList(ContractGenera contractGenera);

    /**
     * 查询总包合同审核列表
     *
     * @param userId 用户id
     * @param roleId 角色id
     * @param type 0 待审核 1 已审核
     * @return 总包合同集合
     */
    public List<ContractGenera> selectContractGeneraShList(ContractGenera contractGenera);

    /**
     * 新增总包合同
     * 
     * @param contractGenera 总包合同
     * @return 结果
     */
    public int insertContractGenera(ContractGenera contractGenera);

    /**
     * 修改总包合同
     * 
     * @param contractGenera 总包合同
     * @return 结果
     */
    public int updateContractGenera(ContractGenera contractGenera);

    /**
     * 修改总包合同状态
     *
     * @param djNumber 单号
     * @param status 单据状态
     * @param type 0 修改节点 1 修改状态
     * @return 结果
     */
    public int updateContractGeneraStatusOrNodeNo(String djNumber,Integer status,int type);
    /**
     * 批量删除总包合同
     * 
     * @param ids 需要删除的总包合同ID
     * @return 结果
     */
    public int deleteContractGeneraByIds(Long[] ids);

    /**
     * 删除总包合同信息
     * 
     * @param id 总包合同ID
     * @return 结果
     */
    public int deleteContractGeneraById(Long id);
}
