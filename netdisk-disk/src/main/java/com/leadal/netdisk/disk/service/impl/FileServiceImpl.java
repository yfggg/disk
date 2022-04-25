package com.leadal.netdisk.disk.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.leadal.netdisk.disk.enums.TableKind;
import com.leadal.netdisk.disk.model.File;
import com.leadal.netdisk.disk.dao.FileMapper;
import com.leadal.netdisk.disk.service.IFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.leadal.netdisk.disk.view.FileVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 文件表 服务实现类
 * </p>
 *
 * @author yf
 * @since 2022-04-18
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements IFileService {

    @Override
    public List<File> moveAndCopyById(String diskId, FileVO vo, boolean needId) {

        List<String> fileIds = vo.getFileIds();
        String folderId = vo.getFolderId();

        // 查询文件
        QueryWrapper<File> fiWrapper = new QueryWrapper<>();
        fiWrapper
                .in("id", fileIds)
                .eq("disk_id", diskId)
                .eq("table_kind", TableKind.FILE)
                .eq("del_flag", "0");
        List<File> files = this.baseMapper.selectList(fiWrapper);

        // 查询目标目录
        QueryWrapper<File> foWrapper = new QueryWrapper<>();
        foWrapper
                .eq("id", folderId)
                .eq("disk_id", diskId)
                .eq("table_kind", TableKind.FOLDER)
                .eq("del_flag", "0");
        File folder = this.baseMapper.selectOne(foWrapper);
        String folderName = folder.getFolderName();
        String folderParentId = folder.getFolderParentId();
        String folderParentIds = folder.getFolderParentIds();

        // 组合
        List<File> finas = files.stream().map(file -> {
            if(needId) {
                file.setId(IdUtil.simpleUUID());
            }
            file.setFolderName(folderName);
            file.setFolderParentId(folderParentId);
            file.setFolderParentIds(folderParentIds);
            return file;
        }).collect(Collectors.toList());

        return finas;
    }



}
