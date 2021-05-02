package space.oldtaoge.ossc.server.server.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import space.oldtaoge.ossc.server.commons.AbstractBaseDomain;

/**
 * <p>
 * 客户端(对端)表
 * </p>
 *
 * @author OldTaoge
 * @since 2021-02-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UmsClient extends AbstractBaseDomain {

    private static final long serialVersionUID = 1L;

    /**
     * 认证用UUID(虽然使用OAuth2认证)
     */

    private String uuid;

    /**
     * 客户端名称(仅用作标识)
     */
    private String clientName;

    /**
     * 客户端连接密码(加密后)
     */
    @JsonIgnore
    private String password;

    /**
     * 客户端最后连接的时间
     */
    private LocalDateTime loginTime;

    /**
     * 现在是否登录
     */
    private Integer isLogin;

    /**
     * 是否启用
     */
    private Integer isEnable;

    /**
     * IPVersion(4,6)
     */
    private Integer ipv;

    /**
     * IP地址
     */
    private String ip;


}
