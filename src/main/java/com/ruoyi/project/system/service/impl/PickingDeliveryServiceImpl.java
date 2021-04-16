package com.ruoyi.project.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.system.domain.*;
import com.ruoyi.project.system.mapper.*;
import com.ruoyi.project.system.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 领料出库单Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-07
 */
@Service
public class PickingDeliveryServiceImpl implements IPickingDeliveryService 
{
    @Autowired
    private PickingDeliveryMapper pickingDeliveryMapper;
    @Autowired
    private PickingDeliveryChildMapper pickingDeliveryChildMapper;
    @Autowired
    private SystemFileMapper systemFileMapper;
    @Autowired
    private FlowInfoMapper flowInfoMapper;
    @Autowired
    private FlowNodeMapper flowNodeSMapper;
    @Autowired
    private FlowAuditMapper flowAuditMapper;
    @Autowired
    private StockInfoMapper stockInfoMapper;

    /**
     * 查询领料出库单
     * 
     * @param id 领料出库单ID
     * @return 领料出库单
     */
    @Override
    public PickingDelivery selectPickingDeliveryById(Integer id)
    {
        return pickingDeliveryMapper.selectPickingDeliveryById(id);
    }

    /**
     * 查询领料出库单列表
     * 
     * @param pickingDelivery 领料出库单
     * @return 领料出库单
     */
    @Override
    public List<PickingDelivery> selectPickingDeliveryList(PickingDelivery pickingDelivery)
    {
        return pickingDeliveryMapper.selectPickingDeliveryList(pickingDelivery);
    }

    /**
     * 检测是否被引用
     * @param djNumber
     * @return
     */
    @Override
    public int checkDeliveryOnReturn(String djNumber){
        return pickingDeliveryMapper.checkDeliveryOnReturn(djNumber);
    }

    /**
     * 查询领料出库单审核列表
     *
     * @param pickingDelivery 领料出库单
     * @return 领料出库单
     */
    @Override
    public List<PickingDelivery> selectPickingDeliveryShList(PickingDelivery pickingDelivery)
    {
        return pickingDeliveryMapper.selectPickingDeliveryShList(pickingDelivery);
    }


    /**
     * 修改审核状态
     *
     * @param djNumber 单号
     * @param status 单据状态
     * @param type 0 修改节点 1 修改状态
     * @return 结果
     */
    @Override
    public int updatetPickingDeliveryStatusOrNodeNo(String djNumber,Integer status,int type){
        return pickingDeliveryMapper.updatetPickingDeliveryStatusOrNodeNo(djNumber,status,type);
    }



    /**
     * 新增领料出库单
     * 
     * @param pickingDelivery 领料出库单
     * @return 结果
     */
    @Transactional
    @Override
    public int insertPickingDelivery(PickingDelivery pickingDelivery)
    {
        pickingDelivery.setCreateBy(SecurityUtils.getUsername());
        pickingDelivery.setDjNumber(StringUtils.getRandomCode("PD"));
        if(pickingDelivery.getRows()!=null&&pickingDelivery.getRows()!="") {
            List<PickingDeliveryChild> childList = JSONArray.parseArray(pickingDelivery.getRows(), PickingDeliveryChild.class);
            for (PickingDeliveryChild child : childList) {
                child.setId(StringUtils.getId());
                child.setCreateBy(SecurityUtils.getUsername());
                child.setDjNumber(pickingDelivery.getDjNumber());
                child.setCreateTime(DateUtils.getNowDate());
                pickingDeliveryChildMapper.insertPickingDeliveryChild(child);

                //减去库存
                StockInfo StockInfo=new StockInfo();
                StockInfo.setDjType(1);
                StockInfo.setRkOrderId(child.getRkOrderId());
                StockInfo.setGoodsCode(child.getGoodsCode());
                StockInfo.setGoodsNum(child.getGoodsNum());
                stockInfoMapper.updateStockInfoByGoodsCode(StockInfo);
            }
        }
        //插入附件
        if(pickingDelivery.getFileRows()!=null&&pickingDelivery.getFileRows()!="") {
            List<SystemFile> childList = JSONArray.parseArray(pickingDelivery.getFileRows(), SystemFile.class);
            for (SystemFile child : childList) {
                child.setCode(pickingDelivery.getDjNumber());
                child.setCreateBy(SecurityUtils.getUsername());
                systemFileMapper.insertSystemFile(child);
            }
        }
        pickingDelivery.setCreateTime(DateUtils.getNowDate());
        return pickingDeliveryMapper.insertPickingDelivery(pickingDelivery);
    }

    /**
     * 修改领料出库单
     * 
     * @param pickingDelivery 领料出库单
     * @return 结果
     */
    @Transactional
    @Override
    public int updatePickingDelivery(PickingDelivery pickingDelivery)
    {
        pickingDelivery.setUpdateTime(DateUtils.getNowDate());
        pickingDelivery.setUpdateBy(SecurityUtils.getUsername());
        if(pickingDelivery.getRows()!=null&&pickingDelivery.getRows()!="") {
            List<PickingDeliveryChild> childList = JSONArray.parseArray(pickingDelivery.getRows(), PickingDeliveryChild.class);
            for (PickingDeliveryChild child : childList) {
                if (child.getId() != null) {
                    child.setDjNumber(pickingDelivery.getDjNumber());
                    child.setUpdateBy(SecurityUtils.getUsername());
                    pickingDeliveryChildMapper.updatePickingDeliveryChild(child);
                    //与原始数量做比较 大于则减去库存 小于则增加库存
                    StockInfo StockInfo=new StockInfo();
                    //减去库存
                    if(child.getGoodsNum()>child.getOldGoodsNum()){
                        StockInfo.setDjType(1);
                        StockInfo.setGoodsNum(child.getGoodsNum()-child.getOldGoodsNum());
                    }else{
                        StockInfo.setDjType(0);
                        StockInfo.setGoodsNum(child.getOldGoodsNum()-child.getGoodsNum());
                    }
                    StockInfo.setRkOrderId(child.getRkOrderId());
                    StockInfo.setGoodsCode(child.getGoodsCode());
                    stockInfoMapper.updateStockInfoByGoodsCode(StockInfo);
                    StockInfo=null;
                } else {
                    child.setCreateBy(SecurityUtils.getUsername());
                    child.setDjNumber(pickingDelivery.getDjNumber());
                    child.setCreateTime(DateUtils.getNowDate());
                    pickingDeliveryChildMapper.insertPickingDeliveryChild(child);
                    //减去库存
                    StockInfo StockInfo=new StockInfo();
                    StockInfo.setDjType(1);
                    StockInfo.setGoodsCode(child.getGoodsCode());
                    StockInfo.setGoodsNum(child.getGoodsNum());
                    stockInfoMapper.updateStockInfoByGoodsCode(StockInfo);

                }
            }
        }
        //插入附件
        if(pickingDelivery.getFileRows()!=null&&pickingDelivery.getFileRows()!="") {
            List<SystemFile> childList = JSONArray.parseArray(pickingDelivery.getFileRows(), SystemFile.class);
            for (SystemFile child : childList) {
                if (child.getId() != null) {

                }else{
                    child.setCode(pickingDelivery.getDjNumber());
                    child.setCreateBy(SecurityUtils.getUsername());
                    systemFileMapper.insertSystemFile(child);
                }
            }
        }
        return pickingDeliveryMapper.updatePickingDelivery(pickingDelivery);
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
        //flowInfo.setFlowNo("LLCK001"+SecurityUtils.getUsername());
        //判断是否注册用户
        SysUser user=SecurityUtils.getLoginUser().getUser();
        if(user.getCreateBy().equals("admin")){
            flowInfo.setFlowNo("LLCK001"+SecurityUtils.getUsername());
        }else{
            flowInfo.setFlowNo("LLCK001"+user.getCreateBy());
        }
        flowInfo.setStatus(1);
        List<FlowInfo> list = flowInfoMapper.selectFlowInfoList(flowInfo);
        //查询审批节点
        FlowNode node=new FlowNode();
        node.setFlowNo(flowInfo.getFlowNo());
        List<FlowNode> nodeList = flowNodeSMapper.selectFlowNodeList(node);
        for(Integer id:ids){
            PickingDelivery info=pickingDeliveryMapper.selectPickingDeliveryById(id);
            if(info.getStatus()>0){
                continue;
            }
            //判断是否审批 如不审批则直接生效
            if(info.getIsSp()==0){
                //直接生效
                info.setStatus(2);
                pickingDeliveryMapper.updatePickingDelivery(info);
               /* PickingDeliveryChild child=new PickingDeliveryChild();
                child.setDjNumber(info.getDjNumber());
                List<PickingDeliveryChild> pickingDeliveryChildList=pickingDeliveryChildMapper.selectPickingDeliveryChildList(child);
                for(PickingDeliveryChild pwChild:pickingDeliveryChildList){
                    //添加出库库存记录
                    StockInfo StockInfo=new StockInfo();
                    StockInfo.setDjType(1);//出库
                    StockInfo.setDjNumber(info.getDjNumber());
                    StockInfo.setProjectCode(info.getProjectCode());
                    StockInfo.setProjectName(info.getProjectName());
                    StockInfo.setDjTime(info.getDjTime());
                    StockInfo.setRkTime(DateUtils.getTime());
                    StockInfo.setGoodsCode(pwChild.getGoodsCode());
                    StockInfo.setGoodsName(pwChild.getGoodsName());
                    StockInfo.setGoodsDw(pwChild.getGoodsDw());
                    StockInfo.setGoodsGg(pwChild.getGoodsGg());
                    StockInfo.setGoodsNum(pwChild.getGoodsNum());
                    StockInfo.setGoodsPrice(pwChild.getGoodsPrice());
                    StockInfo.setGoodsMoney(pwChild.getGoodsMoney());
                    StockInfo.setCreateBy(SecurityUtils.getUsername());
                    StockInfo.setGoodsNum(pwChild.getGoodsNum());
                    StockInfo.setGoodsPrice(pwChild.getGoodsPrice());
                    StockInfo.setGoodsMoney(pwChild.getGoodsMoney());
                    StockInfo.setCreateBy(SecurityUtils.getUsername());
                    stockInfoMapper.insertStockInfo(StockInfo);
                }
                child=null;*/
            }else{
                if(list!=null&&list.size()>0){
                    //添加流程号
                    info.setFlowNo(list.get(0).getFlowNo());
                    //状态修改为待审核
                    info.setStatus(1);
                    //添加一级节点
                    info.setNodeNo(1);
                    pickingDeliveryMapper.updatePickingDelivery(info);
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
            PickingDelivery info=pickingDeliveryMapper.selectPickingDeliveryById(id);
            if(info.getStatus()==0){
                continue;
            }
            //判断是否审批 如不审批则直接取消
            if(info.getIsSp()==0){
                info.setStatus(0);
                info.setFlowNo("-1");
                info.setNodeNo(0);
                //查看是否被引用
                int result=pickingDeliveryMapper.checkDeliveryOnReturn(info.getDjNumber());
                if(result>0){
                    return -1;
                }
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
            pickingDeliveryMapper.updatePickingDelivery(info);
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
            pickingDeliveryMapper.updatetPickingDeliveryStatusOrNodeNo(flowAudit.getDjId(), -1, 1);
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
            pickingDeliveryMapper.updatetPickingDeliveryStatusOrNodeNo(flowAudit.getDjId(), flowAudit.getNodeNo() + 1, 0);
            //流程结束
            if (lag) {
                //修改单据状态为已生效
                pickingDeliveryMapper.updatetPickingDeliveryStatusOrNodeNo(flowAudit.getDjId(), 2, 1);
                /*PickingDelivery ware=new PickingDelivery();
                ware.setDjNumber(flowAudit.getDjId());
                PickingDelivery info=pickingDeliveryMapper.selectPickingDeliveryList(ware).get(0);
                PickingDeliveryChild child=new PickingDeliveryChild();
                child.setDjNumber(info.getDjNumber());
                List<PickingDeliveryChild> pickingDeliveryChildList=pickingDeliveryChildMapper.selectPickingDeliveryChildList(child);
                for(PickingDeliveryChild pwChild:pickingDeliveryChildList){
                    //添加库存
                    StockInfo StockInfo=new StockInfo();
                    StockInfo.setDjType(1);//出库
                    StockInfo.setDjNumber(info.getDjNumber());
                    StockInfo.setProjectCode(info.getProjectCode());
                    StockInfo.setProjectName(info.getProjectName());
                    StockInfo.setDjTime(info.getDjTime());
                    StockInfo.setRkTime(DateUtils.getTime());
                    StockInfo.setGoodsCode(pwChild.getGoodsCode());
                    StockInfo.setGoodsName(pwChild.getGoodsName());
                    StockInfo.setGoodsDw(pwChild.getGoodsDw());
                    StockInfo.setGoodsGg(pwChild.getGoodsGg());
                    StockInfo.setGoodsNum(pwChild.getGoodsNum());
                    StockInfo.setGoodsPrice(pwChild.getGoodsPrice());
                    StockInfo.setGoodsMoney(pwChild.getGoodsMoney());
                    StockInfo.setCreateBy(SecurityUtils.getUsername());
                    stockInfoMapper.insertStockInfo(StockInfo);
                    StockInfo=null;
                }
                child=null;
                ware=null;*/
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
                //查看是否被引用
                int result=pickingDeliveryMapper.checkDeliveryOnReturn(item.getDjId());
                if(result>0){
                    return -1;
                }
                lag=true;
            }else{
                //查询末级节点
                int nodeNo=flowAuditMapper.getEndNode(djIds[i]);
                //末级结束
                if(nodeNo==item.getNodeNo()){
                    //查看是否被引用
                    int result=pickingDeliveryMapper.checkDeliveryOnReturn(item.getDjId());
                    if(result>0){
                        return -1;
                    }
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
            pickingDeliveryMapper.updatetPickingDeliveryStatusOrNodeNo(djIds[i],(nodeNos[i]-1),0);
            //如果已经生效则改变状态为待审核
            if(lag){
                //修改单据状态为待审核
                pickingDeliveryMapper.updatetPickingDeliveryStatusOrNodeNo(djIds[i],1,1);
            }
        }
        return 1;
    }

    /**
     * 批量删除领料出库单
     * 
     * @param ids 需要删除的领料出库单ID
     * @return 结果
     */
    @Transactional
    @Override
    public int deletePickingDeliveryByIds(Integer[] ids)
    {
        int result=1;
        List<String> djNumbers=new ArrayList<>();
        for(Integer id:ids){
            PickingDelivery info=pickingDeliveryMapper.selectPickingDeliveryById(id);
            if(info.getStatus()>0){
                result=0;
                return result;
            }else{
                //
                djNumbers.add(info.getDjNumber());
            }
        }
        for(String info:djNumbers){
            PickingDeliveryChild child=new PickingDeliveryChild();
            child.setDjNumber(info);
            List<PickingDeliveryChild> childList = pickingDeliveryChildMapper.selectPickingDeliveryChildList(child);
            for(PickingDeliveryChild pList:childList){
                //添加库存
                StockInfo StockInfo=new StockInfo();
                StockInfo.setDjType(0);
                StockInfo.setRkOrderId(pList.getRkOrderId());
                StockInfo.setGoodsCode(pList.getGoodsCode());
                StockInfo.setGoodsNum(pList.getGoodsNum());
                stockInfoMapper.updateStockInfoByGoodsCode(StockInfo);
                StockInfo=null;
            }
            child=null;
        }
        //删除子表
        pickingDeliveryChildMapper.deletePickingDeliveryChildByPIds(ids);
        result=pickingDeliveryMapper.deletePickingDeliveryByIds(ids);
        return result;
    }

    /**
     * 删除领料出库单信息
     * 
     * @param id 领料出库单ID
     * @return 结果
     */
    @Override
    public int deletePickingDeliveryById(Integer id)
    {
        return pickingDeliveryMapper.deletePickingDeliveryById(id);
    }
}
