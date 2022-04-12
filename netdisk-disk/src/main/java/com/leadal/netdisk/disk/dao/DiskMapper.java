package com.leadal.netdisk.disk.dao;

import com.leadal.netdisk.disk.model.Disk;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 网盘空间表 Mapper 接口
 * </p>
 *
 * @author yf
 * @since 2022-04-07
 */
@Mapper
public interface DiskMapper extends BaseMapper<Disk> {

}
