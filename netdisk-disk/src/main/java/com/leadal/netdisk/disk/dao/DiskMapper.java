package com.leadal.netdisk.disk.dao;

import com.leadal.netdisk.disk.model.entity.Disk;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 网盘表 Mapper 接口
 * </p>
 *
 * @author yf
 * @since 2022-04-01
 */
@Mapper
public interface DiskMapper extends BaseMapper<Disk> {

}
