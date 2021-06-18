package space.oldtaoge.ossc.server.provider.cms.config.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;
import space.oldtaoge.ossc.server.provider.cms.config.mapper.CmsConfigMapper;
import space.oldtaoge.ossc.server.provider.entity.CmsConfig;
import space.oldtaoge.ossc.server.provider.entity.CmsConfigLess;
import space.oldtaoge.ossc.server.provider.service.ICmsConfigService;

import javax.annotation.Resource;
import java.io.Serializable;

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
public class CmsConfigServiceImpl extends ServiceImpl<CmsConfigMapper, CmsConfig> implements ICmsConfigService {
    @Resource
    private CmsConfigMapper cmsConfigMapper;

    @Override
    public IPage<CmsConfigLess> getListByCliId(Long cliId, Long current, Long length){
        return cmsConfigMapper.selectListByCliIdPages(new Page<>(current, length, true), cliId);
    }

    @Override
    public CmsConfig getById(Serializable id) {
        return super.getById(id);
    }
}
