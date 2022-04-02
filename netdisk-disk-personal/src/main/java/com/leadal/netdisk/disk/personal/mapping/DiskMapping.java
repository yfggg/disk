package com.leadal.netdisk.disk.personal.mapping;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DiskMapping {

    DiskMapping INSTANCE = Mappers.getMapper(DiskMapping.class);

//    Disk toEntity(DiskVO vo);
}
