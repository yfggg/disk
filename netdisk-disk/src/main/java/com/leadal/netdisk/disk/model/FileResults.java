package com.leadal.netdisk.disk.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "FileResults对象")
public class FileResults implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty("文件夹列表")
    private List<Folder> folders;

    @ApiModelProperty("文件列表")
    private List<File> files;

}
