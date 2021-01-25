package com.ruoyi.project.system.service;

import java.util.List;
import com.ruoyi.project.system.domain.SystemFile;

/**
 * 系统文件Service接口
 * 
 * @author ruoyi
 * @date 2021-01-25
 */
public interface ISystemFileService 
{
    /**
     * 查询系统文件
     * 
     * @param id 系统文件ID
     * @return 系统文件
     */
    public SystemFile selectSystemFileById(Integer id);

    /**
     * 查询系统文件列表
     * 
     * @param systemFile 系统文件
     * @return 系统文件集合
     */
    public List<SystemFile> selectSystemFileList(SystemFile systemFile);

    /**
     * 新增系统文件
     * 
     * @param systemFile 系统文件
     * @return 结果
     */
    public int insertSystemFile(SystemFile systemFile);

    /**
     * 修改系统文件
     * 
     * @param systemFile 系统文件
     * @return 结果
     */
    public int updateSystemFile(SystemFile systemFile);

    /**
     * 批量删除系统文件
     * 
     * @param ids 需要删除的系统文件ID
     * @return 结果
     */
    public int deleteSystemFileByIds(Integer[] ids);

    /**
     * 删除系统文件信息
     * 
     * @param id 系统文件ID
     * @return 结果
     */
    public int deleteSystemFileById(Integer id);
}
