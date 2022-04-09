package com.leadal.netdisk.disk.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="FolderVO对象")
public class FolderVO {

    @ApiModelProperty(value ="文件夹id")
    private String folderId;

    @ApiModelProperty(value ="上级文件夹ID")
    private String parentId;

    @ApiModelProperty(value ="文件夹名称")
    private String folderName;

}
