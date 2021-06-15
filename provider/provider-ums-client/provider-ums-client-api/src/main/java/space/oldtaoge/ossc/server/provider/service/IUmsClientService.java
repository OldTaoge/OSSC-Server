package space.oldtaoge.ossc.server.provider.service;

import space.oldtaoge.ossc.server.provider.entity.UmsClient;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 客户端(对端)表 服务类
 * </p>
 *
 * @author OldTaoge
 * @since 2021-02-07
 */
public interface IUmsClientService extends IService<UmsClient> {
    UmsClient getByUUID(String u);

}
