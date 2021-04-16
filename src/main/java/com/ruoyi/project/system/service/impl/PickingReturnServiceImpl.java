package com.ruoyi.project.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.project.system.domain.*;
import com.ruoyi.project.system.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.service.IPickingReturnService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 借用归还单Service业务层处理
 * 
 * @author xy
 * @date 2021-04-12
 */
@Service
public class PickingReturnServiceImpl implements IPickingReturnService 
{
    @Autowired
    private PickingReturnMapper pickingReturnMapper;
    @Autowired
    private PickingReturnChildMapper pickingReturnChildMapper;
    @Autowired
    private SystemFileMapper systemFileMapper;
    @Autowired
    private FlowInfoMapper flowInfoMapper;
    @Autowired
    private FlowNodeMapper flowNodeSMapper;
    @Autowired
    private FlowAuditMapper flowAuditMapper;

    /**
     * 查询借用归还单
     * 
     * @param id 借用归还单ID
     * @return 借用归还单
     */
    @Override
    public PickingReturn selectPickingReturnById(Integer id)
    {
        return pickingReturnMapper.selectPickingReturnById(id);
    }

    /**
     * 查询借用归还单列表
     * 
     * @param pickingReturn 借用归还单
     * @return 借用归还单
     */
    @Override
    public List<PickingReturn> selectPickingReturnList(PickingReturn pickingReturn)
    {
        return pickingReturnMapper.selectPickingReturnList(pickingReturn);
    }

    /**
     * 查询借用归还单审核列表
     *
     * @param pickingReturn 借用归还单
     * @return 借用归还单集合
     */
    @Override
    public List<PickingReturn> selectPickingReturnShList(PickingReturn pickingReturn){
        return pickingReturnMapper.selectPickingReturnShList(pickingReturn);
    }
    /**
     * 新增借用归还单
     * 
     * @param pickingReturn 借用归还单
     * @return 结果
     */
    @Override
    @Transactional
    @DataScope(deptAlias = "d", userAlias = "u")
    public int insertPickingReturn(PickingReturn pickingReturn)
    {
        pickingReturn.setCreateBy(SecurityUtils.getUsername());
        pickingReturn.setDjNumber(StringUtils.getRandomCode("PR"));
        if(pickingReturn.getRows()!=null&&pickingReturn.getRows()!="") {
            List<PickingReturnChild> childList = JSONArray.parseArray(pickingReturn.getRows(), PickingReturnChild.class);
            for (PickingReturnChild child : childList) {
                child.setId(StringUtils.getId());
                child.setCreateBy(SecurityUtils.getUsername());
                child.setDjNumber(pickingReturn.getDjNumber());
                child.setCreateTime(DateUtils.getNowDate());
                pickingReturnChildMapper.insertPickingReturnChild(child);
            }
        }
        //插入附件
        if(pickingReturn.getFileRows()!=null&&pickingReturn.getFileRows()!="") {
            List<SystemFile> childList = JSONArray.parseArray(pickingReturn.getFileRows(), SystemFile.class);
            for (SystemFile child : childList) {
                child.setCode(pickingReturn.getDjNumber());
                child.setCreateBy(SecurityUtils.getUsername());
                systemFileMapper.insertSystemFile(child);
            }
        }
        pickingReturn.setCreateTime(DateUtils.getNowDate());
        pickingReturn.setCreateTime(DateUtils.getNowDate());
        return pickingReturnMapper.insertPickingReturn(pickingReturn);
    }

    /**
     * 修改借用归还单
     * 
     * @param pickingReturn 借用归还单
     * @return 结果
     */
    @Override
    @Transactional
    public int updatePickingReturn(PickingReturn pickingReturn)
    {
        pickingReturn.setUpdateTime(DateUtils.getNowDate());
        pickingReturn.setUpdateBy(SecurityUtils.getUsername());
        if(pickingReturn.getRows()!=null&&pickingReturn.getRows()!="") {
            List<PickingReturnChild> childList = JSONArray.parseArray(pickingReturn.getRows(), PickingReturnChild.class);
            for (PickingReturnChild child : childList) {
                if (child.getId() != null) {
                    child.setDjNumber(pickingReturn.getDjNumber());
                    child.setUpdateBy(SecurityUtils.getUsername());
                    pickingReturnChildMapper.updatePickingReturnChild(child);
                } else {
                    child.setCreateBy(SecurityUtils.getUsername());
                    child.setDjNumber(pickingReturn.getDjNumber());
                    child.setCreateTime(DateUtils.getNowDate());
                    pickingReturnChildMapper.insertPickingReturnChild(child);

                }
            }
        }
        //插入附件
        if(pickingReturn.getFileRows()!=null&&pickingReturn.getFileRows()!="") {
            List<SystemFile> childList = JSONArray.parseArray(pickingReturn.getFileRows(), SystemFile.class);
            for (SystemFile child : childList) {
                if (child.getId() != null) {

                }else{
                    child.setCode(pickingReturn.getDjNumber());
                    child.setCreateBy(SecurityUtils.getUsername());
                    systemFileMapper.insertSystemFile(child);
                }
            }
        }
        return pickingReturnMapper.updatePickingReturn(pickingReturn);
    }

    /**
     * 批量提交
     *
     * @param ids 单据ID
     * @return 结果
     */
    @Transactional
    @Override
    public int effect(Integer[] ids)
    {
        //查询审批流程
        FlowInfo flowInfo=new FlowInfo();
       // flowInfo.setFlowNo("JYGH001"+SecurityUtils.getUsername());
        //判断是否注册用户
        SysUser user=SecurityUtils.getLoginUser().getUser();
        if(user.getCreateBy().equals("admin")){
            flowInfo.setFlowNo("JYGH001"+SecurityUtils.getUsername());
        }else{
            flowInfo.setFlowNo("JYGH001"+user.getCreateBy());
        }
        flowInfo.setStatus(1);
        List<FlowInfo> list = flowInfoMapper.selectFlowInfoList(flowInfo);
        //查询审批节点
        FlowNode node=new FlowNode();
        node.setFlowNo(flowInfo.getFlowNo());
        List<FlowNode> nodeList = flowNodeSMapper.selectFlowNodeList(node);
        for(Integer id:ids){
            PickingReturn info=pickingReturnMapper.selectPickingReturnById(id);
            if(info.getStatus()>0){
                continue;
            }
            //判断是否审批 如不审批则直接生效
            if(info.getIsSp()==0){
                //直接生效
                info.setStatus(2);
                pickingReturnMapper.updatePickingReturn(info);
            }else{
                if(list!=null&&list.size()>0){
                    //添加流程号
                    info.setFlowNo(list.get(0).getFlowNo());
                    //状态修改为待审核
                    info.setStatus(1);
                    //添加一级节点
                    info.setNodeNo(1);
                    pickingReturnMapper.updatePickingReturn(info);
                    //删除历史流程 即修改历史流程状态
                    flowAuditMapper.updateFlowAuditByHistory(info.getDjNumber());
                    //添加单据流程环节
                    for(FlowNode flowNode:nodeList){
                        FlowAudit audit=new FlowAudit();
                        //是否历史流程 0 否 -1 是
                        audit.setFlowStatus(0);
                        audit.setDjId(info.getDjNumber());
                        audit.setNodeNo(flowNode.getNodeNo());
                        audit.setCreateBy(SecurityUtils.getUsername());
                        audit.setPrId(flowNode.getPrId());
                        audit.setPrName(flowNode.getPrName());
                        audit.setStatus(0);
                        audit.setIsEnd(flowNode.getIsEnd());
                        flowAuditMapper.insertFlowAudit(audit);
                    }
                }else{
                    continue;
                }
            }
        }
        flowInfo=null;
        node=null;
        return 1;
    }
    /**
     * 批量取消提交
     *
     * @param ids 单据ID
     * @return 结果
     */
    @Transactional
    @Override
    public int cancel(Integer[] ids)
    {
        for(Integer id:ids){
            PickingReturn info=pickingReturnMapper.selectPickingReturnById(id);
            if(info.getStatus()==0){
                continue;
            }
            //判断是否审批 如不审批则直接取消
            if(info.getIsSp()==0){
                info.setStatus(0);
                info.setFlowNo("-1");
                info.setNodeNo(0);
            }else{
                //判断是否审批中
                if(info.getNodeNo()==1){
                    //直接取消
                    info.setStatus(0);
                    info.setFlowNo("-1");
                    info.setNodeNo(0);
                    //清空单据流程环节
                    flowAuditMapper.deleteFlowAuditByDjId(info.getDjNumber());
                }else{
                    //审批中跳过
                    continue;
                }
            }
            pickingReturnMapper.updatePickingReturn(info);
        }
        return 1;
    }
    /**
     * 审核
     *
     * @param flowAudit 审核信息
     * @return 结果
     */
    @Transactional
    @Override
    public int examine(FlowAudit flowAudit)
    {
        boolean lag=false;
        //获取当前节点信息
        FlowAudit item=flowAuditMapper.selectFlowAuditNoAndDjId(flowAudit.getDjId(),flowAudit.getNodeNo());
        if(item.getStatus()>0){
            return 0;//重复审批
        }
        //判断是否退回
        if(flowAudit.getStatus()==2){
            //状态
            item.setStatus(2);
            //审核意见
            item.setAuditInfo(flowAudit.getAuditInfo());
            //审核时间
            item.setAuditTime(DateUtils.getTime());
            //审核人员
            item.setUserName(SecurityUtils.getUsername());
            //修改节点状态
            flowAuditMapper.updateFlowAudit(item);
            //修改单据状态为被退回
            pickingReturnMapper.updatetPickingReturnStatusOrNodeNo(flowAudit.getDjId(), -1, 1);
        }else {
            //允许结束
            if (item.getIsEnd() == 1) {
                lag = true;
            } else {
                //查询末级节点
                int nodeNo = flowAuditMapper.getEndNode(flowAudit.getDjId());
                //末级结束
                if (nodeNo == item.getNodeNo()) {
                    lag = true;
                } else {
                    lag = false;
                }
            }
            //状态
            item.setStatus(flowAudit.getStatus());
            //是否历史流程 0 否 -1 是
            item.setFlowStatus(0);
            //审核意见
            item.setAuditInfo(flowAudit.getAuditInfo());
            //审核时间
            item.setAuditTime(DateUtils.getTime());
            //审核人员
            item.setUserName(SecurityUtils.getUsername());
            //修改节点状态
            flowAuditMapper.updateFlowAudit(item);
            //修改单据下一级节点
            pickingReturnMapper.updatetPickingReturnStatusOrNodeNo(flowAudit.getDjId(), flowAudit.getNodeNo() + 1, 0);
            //流程结束
            if (lag) {
                //修改单据状态为已生效
                pickingReturnMapper.updatetPickingReturnStatusOrNodeNo(flowAudit.getDjId(), 2, 1);
            }
        }
        return 1;
    }
    /**
     * 取消审核
     *
     * @param djIds 单据ID
     * @param nodeNos 节点
     * @return 结果
     */
    @Transactional
    @Override
    public int cancelAudit(String[] djIds,Integer[] nodeNos)
    {
        for(int i=0;i<djIds.length;i++){
            boolean lag=false;
            //获取上级节点信息
            FlowAudit item=flowAuditMapper.selectFlowAuditNoAndDjId(djIds[i],nodeNos[i]-1);
            if(item.getStatus()==0){
                continue;
            }
            //允许结束
            if(item.getIsEnd()==1){
                lag=true;
            }else{
                //查询末级节点
                int nodeNo=flowAuditMapper.getEndNode(djIds[i]);
                //末级结束
                if(nodeNo==item.getNodeNo()){
                    lag=true;
                }else{
                    lag=false;
                }
            }
            //状态
            item.setFlowStatus(0);
            //状态
            item.setStatus(0);
            //审核意见
            item.setAuditInfo(" ");
            //审核时间
            item.setAuditTime(" ");
            //审核人员
            item.setUserName(" ");
            //回退节点状态
            flowAuditMapper.updateFlowAudit(item);
            //修改单据上一级节点
            pickingReturnMapper.updatetPickingReturnStatusOrNodeNo(djIds[i],(nodeNos[i]-1),0);
            //如果已经生效则改变状态为待审核
            if(lag){
                //修改单据状态为待审核
                pickingReturnMapper.updatetPickingReturnStatusOrNodeNo(djIds[i],1,1);
            }
        }
        return 1;
    }

    /**
     * 批量删除借用归还单
     *
     * @param ids 需要删除的借用归还单ID
     * @return 结果
     */
    @Transactional
    @Override
    public int deletePickingReturnByIds(Integer[] ids)
    {
        int result=1;
        List<String> djNumbers=new ArrayList<>();
        for(Integer id:ids){
            PickingReturn info=pickingReturnMapper.selectPickingReturnById(id);
            if(info.getStatus()>0){
                result=0;
                return result;
            }
        }
        //删除子表
        pickingReturnChildMapper.deletePickingReturnChildByPIds(ids);
        result=pickingReturnMapper.deletePickingReturnByIds(ids);
        return result;
    }
    /**
     * 删除借用归还单信息
     * 
     * @param id 借用归还单ID
     * @return 结果
     */
    @Override
    public int deletePickingReturnById(Integer id)
    {
        return pickingReturnMapper.deletePickingReturnById(id);
    }
}
