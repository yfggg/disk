package com.leadal.netdisk.disk.view;

import com.leadal.netdisk.disk.model.File;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value="BlukFolderVO对象")
public class BlukFolderVO {

    @ApiModelProperty(value ="文件夹id")
    private String folderId;

    @ApiModelProperty(value ="文件id集合")
    private List<String> fileIds;

    @ApiModelProperty(value ="文件集合")
    private List<File> files;

}
