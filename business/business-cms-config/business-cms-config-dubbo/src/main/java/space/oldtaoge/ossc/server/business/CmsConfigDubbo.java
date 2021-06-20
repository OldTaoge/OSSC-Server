package space.oldtaoge.ossc.server.business;

import space.oldtaoge.ossc.server.commons.dto.AbstractBaseResult;

public interface CmsConfigDubbo {
    AbstractBaseResult getById(Long id);
    AbstractBaseResult getByCliId(Long current, Long length);
}
