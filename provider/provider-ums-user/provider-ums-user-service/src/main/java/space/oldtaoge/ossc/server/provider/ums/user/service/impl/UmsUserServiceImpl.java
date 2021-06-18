package space.oldtaoge.ossc.server.provider.ums.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;
import space.oldtaoge.ossc.server.provider.ums.user.entity.UmsUser;
import space.oldtaoge.ossc.server.provider.ums.user.mapper.UmsUserMapper;
import space.oldtaoge.ossc.server.provider.ums.user.service.IUmsUserService;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author OldTaoge
 * @since 2021-06-18
 */
@Service
@DubboService
public class UmsUserServiceImpl extends ServiceImpl<UmsUserMapper, UmsUser> implements IUmsUserService {

    @Resource
    UmsUserMapper umsUserMapper;

    @Override
    public UmsUser getByUUID(String u) {
        QueryWrapper<UmsUser> wrapper = new QueryWrapper<>();
        wrapper.eq("uuid", u);

        return umsUserMapper.selectOne(wrapper);
    }
}
