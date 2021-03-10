package space.oldtaoge.ossc.server.business.oauth2.controller;

import space.oldtaoge.ossc.server.business.oauth2.dto.LoginParam;
import com.google.common.collect.Maps;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import space.oldtaoge.ossc.server.commons.dto.AbstractBaseResult;
import space.oldtaoge.ossc.server.commons.dto.BaseResultFactory;
import space.oldtaoge.ossc.server.commons.dto.SuccessResult;
import space.oldtaoge.ossc.server.commons.utils.JsonUtils;
import space.oldtaoge.ossc.server.commons.utils.OkHttpClientUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

/**
 * 登录管理
 * <p>
 * Description:
 * </p>
 *
 * @author Lusifer
 * @version v1.0.0
 * @date 2019-07-29 11:14:58
 * @see space.oldtaoge.ossc.server.business.oauth2.controller
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class LoginController {

    private static final String URL_OAUTH_TOKEN = "http://localhost:9001/oauth/token";

    @Value("${business.oauth2.grant_type}")
    public String oauth2_grant_type;

    @Value("${business.oauth2.client_id}")
    public String oauth2_client_id;

    @Value("${business.oauth2.client_secret}")
    public String oauth2_client_secret;

    @PostMapping(value = "/user/login")
    public AbstractBaseResult login(@RequestBody LoginParam loginParam, HttpServletRequest request) {
		// 封装返回的结果集
        Map<String, Object> result = Maps.newHashMap();

		// 通过 HTTP 客户端请求登录接口
        Map<String, String> params = Maps.newHashMap();
        params.put("username", loginParam.getUsername());
        params.put("password", loginParam.getPassword());
        params.put("grant_type", oauth2_grant_type);
        params.put("client_id", oauth2_client_id);
        params.put("client_secret", oauth2_client_secret);

        try {
			// 解析响应结果封装并返回
            Response response = OkHttpClientUtil.getInstance().postData(URL_OAUTH_TOKEN, params);
            String jsonString = Objects.requireNonNull(response.body()).string();
            Map<String, Object> jsonMap = JsonUtils.json2map(jsonString);
            String token = String.valueOf(jsonMap.get("access_token"));
            result.put("token", token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return BaseResultFactory.getInstance().build(request.getRequestURI(), new SuccessResult.SuccessData("token", result));

    }

}
