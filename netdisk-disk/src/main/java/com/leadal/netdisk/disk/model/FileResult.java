package com.leadal.netdisk.disk.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.leadal.netdisk.common.model.BaseModel;
import com.leadal.netdisk.disk.enums.FileKind;
import com.leadal.netdisk.disk.enums.TableKind;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 文件表
 * </p>
 *
 * @author yf
 * @since 2022-04-18
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "File对象", description = "文件表")
public class FileResult implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("文件夹和文件")
    private List<File> files;

    @ApiModelProperty("级联路径")
    private List<File> pathTree;


}
