package com.leadal.netdisk.recyle.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.leadal.netdisk.disk.model.File;
import com.leadal.netdisk.recyle.model.Recyle;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 回收站表 Mapper 接口
 * </p>
 *
 * @author yf
 * @since 2022-05-05
 */
@Mapper
public interface RecyleMapper extends BaseMapper<Recyle> {

    IPage<File> selectPageList(Page page, @Param(Constants.WRAPPER) Wrapper wrapper);
}
