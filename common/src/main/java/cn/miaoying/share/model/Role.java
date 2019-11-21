package cn.miaoying.share.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@ApiModel(value = "角色", description = "角色")
public class Role {

    @ApiModelProperty(value = "角色Id")
    private Integer roleId;

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "所属模块Id")
    private Integer modelId;

}