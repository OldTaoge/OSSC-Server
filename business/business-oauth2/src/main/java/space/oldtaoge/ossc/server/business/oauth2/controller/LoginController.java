package space.oldtaoge.ossc.server.business.oauth2.controller;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import space.oldtaoge.ossc.server.business.oauth2.dto.LoginParam;
import space.oldtaoge.ossc.server.business.oauth2.service.LoginService;
import space.oldtaoge.ossc.server.commons.CodeStatus;
import space.oldtaoge.ossc.server.commons.dto.AbstractBaseResult;
import space.oldtaoge.ossc.server.commons.dto.BaseResultFactory;
import space.oldtaoge.ossc.server.commons.dto.SuccessResult;
import space.oldtaoge.ossc.server.commons.utils.AuthenticationUtils;
import space.oldtaoge.ossc.server.provider.ums.client.entity.UmsClient;
import space.oldtaoge.ossc.server.provider.ums.client.entity.service.IUmsClientService;
import space.oldtaoge.ossc.server.provider.ums.user.entity.UmsUser;
import space.oldtaoge.ossc.server.provider.ums.user.service.IUmsUserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
@RestController
@RequestMapping("login")
public class LoginController {
    @Resource
    private LoginService loginService;

    @Resource
    HttpServletRequest request;

    @Resource
    private BCryptPasswordEncoder passwordEncoder;

    @Resource(name = "userDetailsServiceBean")
    private UserDetailsService userDetailsService;

    @DubboReference
    private IUmsClientService umsClientService;

    @DubboReference
    private IUmsUserService umsUserService;

    @Resource
    private TokenStore tokenStore;

    /**
     * 登录Restful版
     * @param loginParam 登录参数
     * @return Restful Result
     */
    @PostMapping(value = "client")
    public AbstractBaseResult clientLogin(@RequestBody LoginParam loginParam) {
        String uniqueUUID = "_c" + loginParam.getUsername();
        return baseLogin(uniqueUUID, loginParam.getPassword());
    }

    @PostMapping(value = "user")
    public AbstractBaseResult userLogin(@RequestBody LoginParam loginParam) {
        String uniqueUUID = "_u" + loginParam.getUsername();
        return baseLogin(uniqueUUID, loginParam.getPassword());
    }

    private AbstractBaseResult baseLogin(String u, String p) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(u);
        if (userDetails != null && passwordEncoder.matches(p, userDetails.getPassword())) {
            Map<String, String> loginStatus = loginService.baseLogin(u, p);
            Map<String, Object> result = new HashMap<>();
            result.put("token", loginStatus.get("token"));
            if (loginStatus.get("status").equals("0"))
            {
                return BaseResultFactory.getInstance().build(request.getRequestURI(), new SuccessResult.SuccessData("token", result));
            }
        }
        return BaseResultFactory.getInstance().build(CodeStatus.BadRequest, "Bad credentials", "error:invalid_username,invalid_password,error_description:Bad credentials", "DEBUG");
    }

    /**
     * 获取用户信息
     *
     */
    @PreAuthorize("hasAuthority('client') OR hasAuthority('user')")
    @GetMapping(value = "info")
    public AbstractBaseResult info() {
        AuthenticationUtils.LoginAble loginObj = AuthenticationUtils.getInstance().getLoginObj();

        if(loginObj.getLoginClass().equals("UmsClient"))
        {
            UmsClient umsclient = umsClientService.getByUUID(loginObj.getRealName());
            return BaseResultFactory.getInstance().build(request.getRequestURI(), umsclient);
        } else if(loginObj.getLoginClass().equals("UmsUser"))
        {
            UmsUser umsUser = umsUserService.getByUUID(loginObj.getRealName());
            return BaseResultFactory.getInstance().build(request.getRequestURI(), umsUser);
        }
        return null;
    }

    /**
     * 注销
     */
    @PreAuthorize("hasAuthority('client') OR hasAuthority('user')")
    @PostMapping(value = "logout")
    public AbstractBaseResult logout() {
        // 获取 token
        String token = request.getParameter("access_token");
        if (token == null) {
            return BaseResultFactory.getInstance().build(CodeStatus.BadRequest, "未找到参数access_token", null, null);
        }
        // 删除 token 以注销
        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(token);
        if (oAuth2AccessToken == null) {
            return BaseResultFactory.getInstance().build(CodeStatus.BadRequest, "token已过期", null, null);
        }
        tokenStore.removeAccessToken(oAuth2AccessToken);
        return BaseResultFactory.getInstance().build(CodeStatus.OK, "已注销", null, null);
    }

}
