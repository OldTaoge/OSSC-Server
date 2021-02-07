package space.oldtaoge.ossc.server.server.controller;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import space.oldtaoge.ossc.server.commons.AbstractBaseController;
import space.oldtaoge.ossc.server.commons.dto.AbstractBaseResult;
import space.oldtaoge.ossc.server.commons.dto.BaseResultFactory;
import space.oldtaoge.ossc.server.server.entity.UmsClient;
import space.oldtaoge.ossc.server.server.service.impl.UmsClientServiceImpl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
            return BaseResultFactory.getInstance().build(HttpStatus.BAD_REQUEST.value(), "Client Not Found", "需要已有的id", getLOG_LEVEL());
        }
    }
}
