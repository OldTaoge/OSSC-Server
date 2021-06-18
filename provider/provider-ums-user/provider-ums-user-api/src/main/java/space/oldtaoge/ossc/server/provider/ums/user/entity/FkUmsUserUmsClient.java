package space.oldtaoge.ossc.server.provider.ums.user.entity;

import space.oldtaoge.ossc.server.commons.AbstractBaseDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author OldTaoge
 * @since 2021-06-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FkUmsUserUmsClient extends AbstractBaseDomain {

    private static final long serialVersionUID = 1L;

    private Long userId;

    private Long clientId;


}
