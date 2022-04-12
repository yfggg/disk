package com.leadal.netdisk.resource.model;

import com.leadal.netdisk.common.model.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * <p>
 * 服务器资源表
 * </p>
 *
 * @author yf
 * @since 2022-04-12
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName("ndk_resource")
@ApiModel(value = "Resource对象", description = "服务器资源表")
public class Resource extends BaseModel {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty("文件大小")
    private Long size;

    @ApiModelProperty("文件类型（.xls .ppt...）")
    private String type;

    @ApiModelProperty("md5摘要")
    private String md5;









}
