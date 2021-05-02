package space.oldtaoge.ossc.server.business.oauth2.service.impl;

import com.google.common.collect.Maps;
import okhttp3.Response;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import space.oldtaoge.ossc.server.business.oauth2.service.LoginService;
import space.oldtaoge.ossc.server.commons.utils.JsonUtils;
import space.oldtaoge.ossc.server.commons.utils.OkHttpClientUtil;

import java.util.Map;
import java.util.Objects;

@Component
@DubboService
public class LoginServiceImpl implements LoginService {

    private static final String URL_OAUTH_TOKEN = "http://localhost:9001/oauth/token";

    @Value("${business.oauth2.grant_type}")
    public String oauth2GrantType;

    @Value("${business.oauth2.client_id}")
    public String oauth2ClientId;

    @Value("${business.oauth2.client_secret}")
    public String oauth2ClientSecret;

    /**
     * 登录核心
     * @param username 用户名
     * @param password 密码
     * @return Map ->{status:成功"0"|失败"1",token:Str(nullable)}
     */
    @Override
    public Map<String, String> baseLogin(@NonNull String username, @NonNull String password) {
        // 封装返回的结果集
        Map<String, String> result = Maps.newHashMap();

        // 通过 HTTP 客户端请求登录接口
        Map<String, String> params = Maps.newHashMap();
        params.put("username", username);
        params.put("password", password);
        params.put("grant_type", oauth2GrantType);
        params.put("client_id", oauth2ClientId);
        params.put("client_secret", oauth2ClientSecret);

        try {
            // 解析响应结果封装并返回
            Response response = OkHttpClientUtil.getInstance().postData(URL_OAUTH_TOKEN, params);
            String jsonString = Objects.requireNonNull(response.body()).string();
            Map<String, Object> jsonMap = JsonUtils.json2map(jsonString);
            String token = String.valueOf(jsonMap.get("access_token"));
            result.put("token", token);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("status", "1");
            return result;
        }
        result.put("status", result.get("token")!=null?"0":"1");
        return result;
    }
}
