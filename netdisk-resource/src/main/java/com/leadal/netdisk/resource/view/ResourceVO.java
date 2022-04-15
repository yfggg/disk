package com.leadal.netdisk.resource.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@ApiModel(value="ResourceVO对象")
public class ResourceVO {

    @ApiModelProperty("网盘空间ID")
    private String diskId;

    @ApiModelProperty("文件夹ID集合")
    private List<String> folderIds;

    @ApiModelProperty("文件ID集合")
    List<String> fileIds;

    @ApiModelProperty("上传文件集合")
    List<MultipartFile> files;





}
