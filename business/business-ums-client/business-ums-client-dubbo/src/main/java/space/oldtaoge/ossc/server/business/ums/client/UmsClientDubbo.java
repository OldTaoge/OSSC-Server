package space.oldtaoge.ossc.server.business.ums.client;

import space.oldtaoge.ossc.server.commons.dto.AbstractBaseResult;

public interface UmsClientDubbo {
    AbstractBaseResult info(String uuid);
}
