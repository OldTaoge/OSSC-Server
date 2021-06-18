package space.oldtaoge.ossc.server.provider.ums.client.entity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import space.oldtaoge.ossc.server.provider.ums.client.entity.UmsClient;

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
