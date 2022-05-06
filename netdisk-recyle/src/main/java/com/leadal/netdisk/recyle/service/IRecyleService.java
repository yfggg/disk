package com.leadal.netdisk.recyle.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.leadal.netdisk.disk.model.File;
import com.leadal.netdisk.recyle.model.Recyle;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 回收站表 服务类
 * </p>
 *
 * @author yf
 * @since 2022-05-05
 */
public interface IRecyleService extends IService<Recyle> {

    IPage<File> selectPage(Page page, Wrapper<Recyle> queryWrapper);

    boolean create(List<String> fileIds, String diskId);

    boolean recover(List<String> fileIds, String diskId);

    Long delete(List<String> fileIds, String diskId);
}
