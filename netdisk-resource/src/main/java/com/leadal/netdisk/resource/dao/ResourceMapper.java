package com.leadal.netdisk.resource.dao;

import com.leadal.netdisk.resource.model.Resource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 服务器资源表 Mapper 接口
 * </p>
 *
 * @author yf
 * @since 2022-04-12
 */
@Mapper
public interface ResourceMapper extends BaseMapper<Resource> {

    int conditionInsert(@Param("et") Resource resource);
}
