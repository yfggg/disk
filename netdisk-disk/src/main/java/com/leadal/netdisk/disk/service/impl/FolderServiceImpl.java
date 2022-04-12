package com.leadal.netdisk.disk.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.leadal.netdisk.disk.model.Folder;
import com.leadal.netdisk.disk.dao.FolderMapper;
import com.leadal.netdisk.disk.service.IFolderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文件夹表 服务实现类
 * </p>
 *
 * @author yf
 * @since 2022-04-07
 */
@Service
public class FolderServiceImpl extends ServiceImpl<FolderMapper, Folder> implements IFolderService {

    @Override
    public List<Folder> selectList(String folderId, String diskId) {
        return this.baseMapper.selectList2(folderId, diskId);
    }
}
