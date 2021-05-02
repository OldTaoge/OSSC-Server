package space.oldtaoge.ossc.server.business.oauth2.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
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

    /**
     * 登录Restful版
     * @param loginParam 登录参数
     * @param request 自动填充
     * @return Restful Result
     */
    @PostMapping(value = "/user/login")
    public AbstractBaseResult login(@RequestBody LoginParam loginParam, HttpServletRequest request) {

        Map<String, String> loginStatus = loginService.baseLogin(loginParam.getUsername(), loginParam.getPassword());
        Map<String, Object> result = new HashMap<>();
        result.put("token", loginStatus.get("token"));
        if (loginStatus.get("status").equals("0"))
        {
            return BaseResultFactory.getInstance().build(request.getRequestURI(), new SuccessResult.SuccessData("token", result));
        }
        return BaseResultFactory.getInstance().build(CodeStatus.BadRequest, "Bad credentials", "error:invalid_grant,error_description:Bad credentials", "DEBUG");
    }



}
