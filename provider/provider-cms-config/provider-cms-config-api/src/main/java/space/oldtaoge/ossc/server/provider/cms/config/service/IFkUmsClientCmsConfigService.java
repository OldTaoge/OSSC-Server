package space.oldtaoge.ossc.server.provider.cms.config.service;

import space.oldtaoge.ossc.server.provider.cms.config.entity.FkUmsClientCmsConfig;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author OldTaoge
 * @since 2021-06-15
 */
public interface IFkUmsClientCmsConfigService extends IService<FkUmsClientCmsConfig> {
    int checkHasPer(Long cliId, Long confId);
}
