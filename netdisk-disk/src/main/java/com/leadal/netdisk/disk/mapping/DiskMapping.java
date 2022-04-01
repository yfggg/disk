package com.leadal.netdisk.disk.mapping;


import com.leadal.netdisk.common.mapping.DateMapper;
import com.leadal.netdisk.disk.model.Disk;
import com.leadal.netdisk.disk.view.DiskVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DiskMapping {

    DiskMapping INSTANCE = Mappers.getMapper(DiskMapping.class);

    Disk toEntity(DiskVO vo);
}
