package cn.miaoying.share.model;

import cn.miaoying.share.common.FeatureTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@ApiModel(value = "功能点", description = "功能点")
public class Feature {

    @ApiModelProperty(value = "功能点Id")
    private Integer featureId;

    @ApiModelProperty(value = "功能点名称")
    private String featureName;

    @ApiModelProperty(value = "功能点类型")
    private FeatureTypeEnum featureType;

    @ApiModelProperty(value = "所属模块Id")
    private Integer modelId;

}