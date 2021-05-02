package space.oldtaoge.ossc.server.business.oauth2.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 登录参数
 * <p>
 * Description:
 * </p>
 *
 * @author Lusifer
 * @version v1.0.0
 * @date 2019-07-29 11:13:56
 * @see space.oldtaoge.ossc.server.business.oauth2.dto
 */
@Data
public class LoginParam implements Serializable {

    private String username;
    private String password;

}
