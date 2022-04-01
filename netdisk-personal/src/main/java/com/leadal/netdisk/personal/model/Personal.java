package com.leadal.netdisk.personal.model;

import com.leadal.netdisk.common.model.BaseModel;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author yf
 * @since 2022-04-01
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Personal对象", description = "用户表")
public class Personal extends BaseModel {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty("姓名")
    private String name;









}
