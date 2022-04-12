package com.leadal.netdisk.disk.personal.model;

import com.leadal.netdisk.common.model.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.leadal.netdisk.disk.model.Disk;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * <p>
 * 个人网盘空间表
 * </p>
 *
 * @author yf
 * @since 2022-04-07
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName("ndk_personal_disk")
@ApiModel(value = "PersonalDisk对象", description = "个人网盘空间表")
public class PersonalDisk extends Disk {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty("拥有者ID")
    private String ownerId;

    @ApiModelProperty("网盘空间ID")
    private String diskId;









}
