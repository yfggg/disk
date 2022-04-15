package com.leadal.netdisk.disk.dao;

import com.leadal.netdisk.disk.model.File;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 文件表 Mapper 接口
 * </p>
 *
 * @author yf
 * @since 2022-04-07
 */
@Mapper
public interface FileMapper extends BaseMapper<File> {

    int conditionInsert(@Param("et") File entity, @Param("md5") String md5);
}
