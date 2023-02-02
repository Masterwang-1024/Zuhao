package com.whl.zuhaowan.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author fyj
 * @ModifyTime 10:04 2021/9/27
 * @Description minio文件信息
 */
@Data
public class FileUploadVO {

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "bucket")
    private String bucket;

    @ApiModelProperty(value = "path")
    private String path;

    @ApiModelProperty(value = "fileName")
    private String fileName;

    @ApiModelProperty(value = "是否是目录")
    private Integer isDir;
}
