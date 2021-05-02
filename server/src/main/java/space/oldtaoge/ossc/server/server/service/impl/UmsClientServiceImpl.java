package space.oldtaoge.ossc.server.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.val;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;
import space.oldtaoge.ossc.server.business.oauth2.dto.LoginParam;
import space.oldtaoge.ossc.server.business.oauth2.service.LoginService;
import space.oldtaoge.ossc.server.commons.CodeStatus;
import space.oldtaoge.ossc.server.server.entity.UmsClient;
import space.oldtaoge.ossc.server.server.mapper.UmsClientMapper;
import space.oldtaoge.ossc.server.server.service.IUmsClientService;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 客户端(对端)表 服务实现类
 * </p>
 *
 * @author OldTaoge
 * @since 2021-02-07
 */
@Service
public class UmsClientServiceImpl extends ServiceImpl<UmsClientMapper, UmsClient> implements IUmsClientService {
    @DubboReference
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

}
