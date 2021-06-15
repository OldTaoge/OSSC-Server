package space.oldtaoge.ossc.server.provider.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import space.oldtaoge.ossc.server.provider.entity.CmsConfig;
import space.oldtaoge.ossc.server.provider.entity.CmsConfigLess;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author OldTaoge
 * @since 2021-06-15
 */
public interface ICmsConfigService extends IService<CmsConfig> {
    IPage<CmsConfigLess> getListByCliId(Long cliId, Long current, Long length);
}
