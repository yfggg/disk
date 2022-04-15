package com.leadal.netdisk.disk.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value="FileVO对象")
public class FileVO {

    @ApiModelProperty(value ="文件夹id")
    private String folderId;

    @ApiModelProperty("网盘空间ID")
    private String diskId;

    @ApiModelProperty(value ="传输文件id集合")
    private List<String> fileIds;



}
