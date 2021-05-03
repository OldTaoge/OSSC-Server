package space.oldtaoge.ossc.server.business.oauth2.controller;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;
import space.oldtaoge.ossc.server.business.oauth2.UmsClientDubbo;

@Component
@DubboService
public class UmsClientController implements UmsClientDubbo {
}
