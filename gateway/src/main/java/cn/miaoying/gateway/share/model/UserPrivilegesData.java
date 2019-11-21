package cn.miaoying.gateway.share.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@ApiModel(value = "用户权限信息", description = "用户权限信息")
public class UserPrivilegesData {

    @ApiModelProperty(value = "用户信息")
    private UserLoginData userLoginData;

    @ApiModelProperty(value = "功能点列表")
    private List<Feature> featureList;

    @ApiModelProperty(value = "数据权限范围列表")
    private List<DataRange> dataRangeList;

    @ApiModelProperty(value = "角色列表")
    private List<Role> roleList;

}