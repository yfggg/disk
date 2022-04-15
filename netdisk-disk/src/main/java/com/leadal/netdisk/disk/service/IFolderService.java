package com.leadal.netdisk.disk.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.leadal.netdisk.disk.model.Folder;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文件夹表 服务类
 * </p>
 *
 * @author yf
 * @since 2022-04-07
 */
public interface IFolderService extends IService<Folder> {

    List<Folder> queryParentList(String folderId, String diskId);

//    List<Folder> queryChildList(String folderId, String diskId);
}
