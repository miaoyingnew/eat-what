package cn.miaoying.gateway.share.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@ApiModel(value = "数据权限范围", description = "数据权限范围")
public class DataRange {

    @ApiModelProperty(value = "可见数据范围类型")
    private Integer dataRange;

    @ApiModelProperty(value = "可见数据范围类型名称")
    private String rangeName;

    @ApiModelProperty(value = "数据Id")
    private String dataId;

    @ApiModelProperty(value = "所属模块Id")
    private Integer modelId;

}