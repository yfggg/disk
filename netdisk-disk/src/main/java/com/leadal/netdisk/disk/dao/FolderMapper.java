package com.leadal.netdisk.disk.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.leadal.netdisk.disk.model.Folder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文件夹表 Mapper 接口
 * </p>
 *
 * @author yf
 * @since 2022-04-07
 */
@Mapper
public interface FolderMapper extends BaseMapper<Folder> {

    List<Folder> selectList2(@Param("folderId") String folderId, @Param("diskId") String diskId);
}
