package com.leadal.netdisk.common.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Date;

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
@ApiModel(value = "FileURL对象", description = "文件传输URL")
public class FileURL implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("文件")
    private MultipartFile mFile;

    @ApiModelProperty("主键路径")
    private String idUrl;

//    @ApiModelProperty("创建时间路径")
//    private Date createTimeUrl;



}
