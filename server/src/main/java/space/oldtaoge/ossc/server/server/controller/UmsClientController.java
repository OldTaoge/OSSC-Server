package space.oldtaoge.ossc.server.server.controller;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import space.oldtaoge.ossc.server.commons.AbstractBaseController;
import space.oldtaoge.ossc.server.commons.CodeStatus;
import space.oldtaoge.ossc.server.commons.dto.AbstractBaseResult;
import space.oldtaoge.ossc.server.commons.dto.BaseResultFactory;
import space.oldtaoge.ossc.server.commons.dto.SuccessResult;
import space.oldtaoge.ossc.server.server.entity.UmsClient;
import space.oldtaoge.ossc.server.server.service.impl.UmsClientServiceImpl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 客户端(对端)表 前端控制器
 * </p>
 *
 * @author OldTaoge
 * @since 2021-02-07
 */
@RestController
@RequestMapping("clients")
public class UmsClientController extends AbstractBaseController {

    @Resource
    UmsClientServiceImpl umsClientService;

    @RequestMapping(method = RequestMethod.GET, path = "{id}")
    AbstractBaseResult getById(@PathVariable(name = "id") Long id, HttpServletRequest request) {
        UmsClient umsClient = umsClientService.getById(id);
        if (umsClient != null) {
            return BaseResultFactory.getInstance().build(request.getRequestURI(), umsClient);
        }
        else
        {
            return BaseResultFactory.getInstance().build(CodeStatus.BadRequest, "Client Not Found", "需要已有的id", getLOG_LEVEL());
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "login")
    AbstractBaseResult conn(@RequestParam("cliId") String id, @RequestParam("password") String password, HttpServletRequest request) {
        if (id != null && password != null) {
            Map<String, Object> loginStatus = umsClientService.login(id, password);
            if (loginStatus.get("code").equals(CodeStatus.OK)) {
                return BaseResultFactory.getInstance().build(request.getRequestURI(), new SuccessResult.SuccessData("token", loginStatus));
            }
            else {
                return BaseResultFactory.getInstance().build(CodeStatus.BadRequest, "401 Bad Request", loginStatus.get("message").toString(), "DEBUG");
            }

        }
        return BaseResultFactory.getInstance().build(CodeStatus.BadRequest, "param error", "No Param cliId and password", "DEBUG");
    }
}
