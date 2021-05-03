package space.oldtaoge.ossc.server.business.oauth2.controller;

import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import space.oldtaoge.ossc.server.business.oauth2.UmsClientDubbo;
import space.oldtaoge.ossc.server.commons.CodeStatus;
import space.oldtaoge.ossc.server.commons.dto.AbstractBaseResult;
import space.oldtaoge.ossc.server.commons.dto.BaseResultFactory;
import space.oldtaoge.ossc.server.provider.entity.UmsClient;
import space.oldtaoge.ossc.server.provider.service.IUmsClientService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@DubboService
@RestController(value = "clients")
public class UmsClientController implements UmsClientDubbo {

    @DubboReference
    IUmsClientService umsClientService;

    @Resource
    HttpServletRequest request;

    @Override
    @GetMapping("info/{uuid}")
    public AbstractBaseResult info(@PathVariable String uuid) {
        UmsClient umsClient = umsClientService.getByUUID(uuid);
        if (umsClient != null) {
            return BaseResultFactory.getInstance().build(request.getRequestURI(), umsClient);
        }
        else {
            return BaseResultFactory.getInstance().build(CodeStatus.BadRequest, "Client Not Found", null, null);
        }
    }
}
