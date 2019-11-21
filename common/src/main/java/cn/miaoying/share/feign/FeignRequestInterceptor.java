package cn.miaoying.share.feign;

import cn.miaoying.share.common.HeaderConstant;
import cn.miaoying.share.model.UserPrivilegesData;
import com.alibaba.fastjson.JSON;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Configuration
@Slf4j
public class FeignRequestInterceptor implements RequestInterceptor {

    @Autowired
    private UserPrivilegesData userPrivilegesData;

    public FeignRequestInterceptor() {
        log.info("FeignRequestInterceptor()");
    }

    @Override
    public void apply(RequestTemplate template) {
        UserPrivilegesData upd = new UserPrivilegesData();
        upd.setUserLoginData(userPrivilegesData.getUserLoginData());
        upd.setFeatureList(userPrivilegesData.getFeatureList());
        upd.setDataRangeList(userPrivilegesData.getDataRangeList());
        upd.setRoleList(userPrivilegesData.getRoleList());

        String encodeStr = JSON.toJSONString(upd);
        log.info("Send request session header : " + encodeStr);

        try {
            encodeStr = URLEncoder.encode(encodeStr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage());
        }
        template.header(HeaderConstant.SESSION, encodeStr);
        log.info("========== feign apply begin ===========");
    }
}