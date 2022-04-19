package com.leadal.netdisk.disk.dao;

import com.leadal.netdisk.disk.model.File;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 文件表 Mapper 接口
 * </p>
 *
 * @author yf
 * @since 2022-04-18
 */
@Mapper
public interface FileMapper extends BaseMapper<File> {

}
