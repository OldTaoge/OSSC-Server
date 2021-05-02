package space.oldtaoge.ossc.server.commons;

import org.springframework.context.ConfigurableApplicationContext;

import javax.annotation.Resource;

public abstract class AbstractBaseController {
    @Resource
    protected ConfigurableApplicationContext applicationContext;

    protected String getLOG_LEVEL() {
        return applicationContext.getEnvironment().getProperty("logging.level.space.oldtaoge.ossc.server");
    }
}
