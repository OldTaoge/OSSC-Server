package space.oldtaoge.ossc.server.provider.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;
import space.oldtaoge.ossc.server.provider.entity.UmsClient;
import space.oldtaoge.ossc.server.provider.mapper.UmsClientMapper;

import javax.annotation.Resource;

/**
 * <p>
 * 客户端(对端)表 服务实现类
 * </p>
 *
 * @author OldTaoge
 * @since 2021-02-07
 */
@Service
@DubboService
public class UmsClientServiceImpl extends ServiceImpl<UmsClientMapper, UmsClient> implements IUmsClientService {
    @Resource
    UmsClientMapper umsClientMapper;
    @Override
    public UmsClient getByUUID(String u) {
        QueryWrapper<UmsClient> wrapper =  new QueryWrapper<>();
        wrapper.eq("uuid", u);
        return umsClientMapper.selectOne(wrapper);
    }
    /*@DubboReference
    LoginService loginService;

    @Nonnull
    public Map<String, Object> login(@Nonnull String cliId,@Nonnull String cliPass)
    {
        val loginParam = new LoginParam();
        loginParam.setUsername(cliId);
        loginParam.setPassword(cliPass);

        Map<String, String> result = loginService.baseLogin(cliId, cliPass);

        Map<String, Object> resultMap = new HashMap<>();

        if (result.get("status").equals("0")) {
            resultMap.put("code", CodeStatus.OK);
            resultMap.put("data", result.get("token"));
        }
        else {
            resultMap.put("code", CodeStatus.BadRequest);
            resultMap.put("data", "Login failure");
        }
        return resultMap;

    }
*/
}
