package com.leadal.netdisk.disk.mapping;

import com.leadal.netdisk.disk.model.File;
import com.leadal.netdisk.disk.view.FolderVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FileMapping {

    FileMapping INSTANCE = Mappers.getMapper(FileMapping.class);

    File toModel(FolderVO vo);

}
