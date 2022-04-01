package com.leadal.netdisk.disk.mapping;

import com.leadal.netdisk.disk.model.Disk;
import com.leadal.netdisk.disk.view.DiskVO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-01T16:34:50+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_271 (Oracle Corporation)"
)
public class DiskMappingImpl implements DiskMapping {

    @Override
    public Disk toEntity(DiskVO vo) {
        if ( vo == null ) {
            return null;
        }

        Disk disk = new Disk();

        disk.setName( vo.getName() );

        return disk;
    }
}
