package space.oldtaoge.ossc.server.business.oauth2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import space.oldtaoge.ossc.server.commons.dto.AbstractBaseResult;

import javax.servlet.http.HttpServletRequest;

public interface UmsClientDubbo {
    @GetMapping("info/{uuid}")
    AbstractBaseResult info(@PathVariable("uuid") String uuid);
}
