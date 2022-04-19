package com.leadal.netdisk.disk.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@ApiModel(value="文件视图对象")
public class FileVO {

    @ApiModelProperty(value ="传输文件ID集")
    private List<String> fileIds;

    @ApiModelProperty(value ="目标文件夹ID")
    private String folderId;





}
