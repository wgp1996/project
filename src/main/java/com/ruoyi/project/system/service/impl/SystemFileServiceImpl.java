package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.SystemFileMapper;
import com.ruoyi.project.system.domain.SystemFile;
import com.ruoyi.project.system.service.ISystemFileService;

/**
 * 系统文件Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-01-25
 */
@Service
public class SystemFileServiceImpl implements ISystemFileService 
{
    @Autowired
    private SystemFileMapper systemFileMapper;

    /**
     * 查询系统文件
     * 
     * @param id 系统文件ID
     * @return 系统文件
     */
    @Override
    public SystemFile selectSystemFileById(Integer id)
    {
        return systemFileMapper.selectSystemFileById(id);
    }

    /**
     * 查询系统文件列表
     * 
     * @param systemFile 系统文件
     * @return 系统文件
     */
    @Override
    public List<SystemFile> selectSystemFileList(SystemFile systemFile)
    {
        return systemFileMapper.selectSystemFileList(systemFile);
    }

    /**
     * 新增系统文件
     * 
     * @param systemFile 系统文件
     * @return 结果
     */
    @Override
    public int insertSystemFile(SystemFile systemFile)
    {
        systemFile.setCreateTime(DateUtils.getNowDate());
        return systemFileMapper.insertSystemFile(systemFile);
    }

    /**
     * 修改系统文件
     * 
     * @param systemFile 系统文件
     * @return 结果
     */
    @Override
    public int updateSystemFile(SystemFile systemFile)
    {
        systemFile.setUpdateTime(DateUtils.getNowDate());
        return systemFileMapper.updateSystemFile(systemFile);
    }

    /**
     * 批量删除系统文件
     * 
     * @param ids 需要删除的系统文件ID
     * @return 结果
     */
    @Override
    public int deleteSystemFileByIds(Integer[] ids)
    {
        return systemFileMapper.deleteSystemFileByIds(ids);
    }

    /**
     * 删除系统文件信息
     * 
     * @param id 系统文件ID
     * @return 结果
     */
    @Override
    public int deleteSystemFileById(Integer id)
    {
        return systemFileMapper.deleteSystemFileById(id);
    }
}
