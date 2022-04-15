package com.leadal.netdisk.disk.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value="FolderVO对象")
public class FolderVO {

    @ApiModelProperty(value ="文件夹id")
    private String id;

    @ApiModelProperty(value ="文件夹名称")
    private String name;

    @ApiModelProperty("上级文件夹ID")
    private String parentId;

    @ApiModelProperty("网盘空间ID")
    private String diskId;




}
