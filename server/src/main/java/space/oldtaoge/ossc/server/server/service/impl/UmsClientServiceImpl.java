package space.oldtaoge.ossc.server.server.service.impl;

import org.springframework.lang.NonNull;
import space.oldtaoge.ossc.server.server.entity.UmsClient;
import space.oldtaoge.ossc.server.server.mapper.UmsClientMapper;
import space.oldtaoge.ossc.server.server.service.IUmsClientService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

    @Nonnull
    public Map<String, Object> login(@Nonnull String cliId,@Nonnull String cliPass)
    {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("code", 0);
        resultMap.put("data", "noMessage");

        return resultMap;
    }

}
