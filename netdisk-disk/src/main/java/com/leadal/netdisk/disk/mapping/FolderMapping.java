package com.leadal.netdisk.disk.mapping;

import com.leadal.netdisk.disk.model.Folder;
import com.leadal.netdisk.disk.view.FolderVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FolderMapping {
    FolderMapping INSTANCE = Mappers.getMapper(FolderMapping.class);
    Folder toModel(FolderVO vo);
}
