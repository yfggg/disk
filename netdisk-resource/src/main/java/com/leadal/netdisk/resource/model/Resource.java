package com.leadal.netdisk.resource.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.leadal.netdisk.common.model.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

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
@TableName("ndk_resource")
@ApiModel(value = "Resource对象", description = "服务器资源表")
public class Resource extends BaseModel {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty("文件大小")
    private Long size;

    @ApiModelProperty("文件类型（.xls .ppt...）")
    private String type;

    @ApiModelProperty("md5摘要")
    private String md5;

    public Resource(String id, String md5, Long size, String type) {
        super();
        this.id = id;
        this.md5 = md5;
        this.type = type;
        this.size = size;
    }

    public Date getCreateTime(){
        return this.createTime;
    }


}
