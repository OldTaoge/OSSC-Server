package space.oldtaoge.ossc.server.provider.ums.user.service;

import space.oldtaoge.ossc.server.provider.ums.user.entity.UmsUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author OldTaoge
 * @since 2021-06-18
 */
public interface IUmsUserService extends IService<UmsUser> {
    UmsUser getByUUID(String u);
}
