package space.oldtaoge.ossc.server.provider.cms.config.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;
import space.oldtaoge.ossc.server.provider.cms.config.mapper.FkUmsClientCmsConfigMapper;
import space.oldtaoge.ossc.server.provider.cms.config.entity.FkUmsClientCmsConfig;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author OldTaoge
 * @since 2021-06-15
 */
@Service
@DubboService
public class FkUmsClientCmsConfigServiceImpl extends ServiceImpl<FkUmsClientCmsConfigMapper, FkUmsClientCmsConfig> implements IFkUmsClientCmsConfigService {
    @Resource
    FkUmsClientCmsConfigMapper mapper;
    @Override
    public int checkHasPer(Long cliId, Long confId) {
        return mapper.checkHasPer(cliId, confId);
    }
}
