package space.oldtaoge.ossc.server.business.oauth2.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import space.oldtaoge.ossc.server.business.oauth2.dto.LoginParam;
import space.oldtaoge.ossc.server.business.oauth2.service.LoginService;
import space.oldtaoge.ossc.server.commons.CodeStatus;
import space.oldtaoge.ossc.server.commons.dto.AbstractBaseResult;
import space.oldtaoge.ossc.server.commons.dto.BaseResultFactory;
import space.oldtaoge.ossc.server.commons.dto.SuccessResult;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录管理
 * <p>
 * Description:
 * </p>
 *
 * @author Lusifer
 * @version v1.0.0
 * @see space.oldtaoge.ossc.server.business.oauth2.controller
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class LoginController {
    @Resource
    private LoginService loginService;

    @Resource
    public BCryptPasswordEncoder passwordEncoder;

    @Resource(name = "userDetailsServiceBean")
    public UserDetailsService userDetailsService;

    @Resource
    public TokenStore tokenStore;

    /**
     * 登录Restful版
     * @param loginParam 登录参数
     * @param request 自动填充
     * @return Restful Result
     */
    @PostMapping(value = "/user/login")
    public AbstractBaseResult login(@RequestBody LoginParam loginParam, HttpServletRequest request) {

        // 验证密码是否正确
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginParam.getUsername());
        if (userDetails != null && passwordEncoder.matches(loginParam.getPassword(), userDetails.getPassword())) {
            Map<String, String> loginStatus = loginService.baseLogin(loginParam.getUsername(), loginParam.getPassword());
            Map<String, Object> result = new HashMap<>();
            result.put("token", loginStatus.get("token"));
            if (loginStatus.get("status").equals("0"))
            {
                return BaseResultFactory.getInstance().build(request.getRequestURI(), new SuccessResult.SuccessData("token", result));
            }
        }
        return BaseResultFactory.getInstance().build(CodeStatus.BadRequest, "Bad credentials", "error:invalid_grant,error_description:Bad credentials", "DEBUG");
    }

    /**
     * 获取用户信息
     *
     */
    @GetMapping(value = "/user/info")
    public Serializable info(HttpServletRequest request) {
        // 获取 token
        String token = request.getParameter("access_token");
        // 获取认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 封装并返回结果
//        LoginInfo loginInfo = new LoginInfo();
//        loginInfo.setName(authentication.getName());
//        loginInfo.setAvatar("");
//        loginInfo.setToken(token);
//        return new ResponseResult<LoginInfo>(ResponseResult.CodeStatus.OK, "获取用户信息", loginInfo);
//        return BaseResultFactory.getInstance().build(request.getRequestURI(), new SuccessResult<AbstractBaseDomain>(request.getRequestURI(), authentication));
        return authentication;
    }

    /**
     * 注销
     */
    @PostMapping(value = "/user/logout")
    public AbstractBaseResult logout(HttpServletRequest request) {
        // 获取 token
        String token = request.getParameter("access_token");
        if (token == null) {
            return BaseResultFactory.getInstance().build(CodeStatus.BadRequest, "未找到参数access_token", null, null);
        }
        // 删除 token 以注销
        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(token);
        tokenStore.removeAccessToken(oAuth2AccessToken);
        return BaseResultFactory.getInstance().build(CodeStatus.OK, "已注销", null, null);
    }

}
