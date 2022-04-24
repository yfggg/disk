package com.leadal.netdisk.disk.model;


import com.baomidou.mybatisplus.core.metadata.IPage;
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
@ApiModel(value = "FileResult对象", description = "文件结果集")
public class FileResult implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("文件夹和文件(分页)")
    private IPage<File> pageFiles;

    @ApiModelProperty("级联路径")
    private List<File> pathTree;


}
