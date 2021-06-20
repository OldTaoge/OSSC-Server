package space.oldtaoge.ossc.server.provider.ums.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;
import space.oldtaoge.ossc.server.provider.ums.user.entity.FkUmsUserUmsClient;
import space.oldtaoge.ossc.server.provider.ums.user.mapper.FkUmsUserUmsClientMapper;
import space.oldtaoge.ossc.server.provider.ums.user.service.IFkUmsUserUmsClientService;

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
public class FkUmsUserUmsClientServiceImpl extends ServiceImpl<FkUmsUserUmsClientMapper, FkUmsUserUmsClient> implements IFkUmsUserUmsClientService {

}
