package com.leadal.netdisk.disk.model;

import com.leadal.netdisk.common.model.BaseModel;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * <p>
 * 网盘表
 * </p>
 *
 * @author yf
 * @since 2022-04-01
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Disk对象", description = "网盘表")
public class Disk extends BaseModel {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty("名称")
    private String name;









}
