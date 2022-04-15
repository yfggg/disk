package com.leadal.netdisk.resource.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="RdownLoadVO对象")
public class RsDownloadVO {

    @ApiModelProperty("服务器资源ID")
    private String resouseId;

    @ApiModelProperty("文件名 例如 新建文本文档2.txt 记得要带后缀")
    private String realFileName;



}
