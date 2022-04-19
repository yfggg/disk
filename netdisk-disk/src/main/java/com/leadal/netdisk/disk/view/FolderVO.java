package com.leadal.netdisk.disk.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@ApiModel(value="文件夹视图对象")
public class FolderVO {

    @ApiModelProperty("文件夹ID")
    private String id;

    @ApiModelProperty("上级文件夹ID集")
    private String folderParentIds;

    @ApiModelProperty(value ="文件夹名称")
    private String folderName;





}
