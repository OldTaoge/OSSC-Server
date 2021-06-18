package space.oldtaoge.ossc.server.provider.cms.config.mapper;

import space.oldtaoge.ossc.server.provider.entity.FkUmsClientCmsConfig;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author OldTaoge
 * @since 2021-06-15
 */
public interface FkUmsClientCmsConfigMapper extends BaseMapper<FkUmsClientCmsConfig> {
    int checkHasPer(Long cliId, Long confId);
}
