package space.oldtaoge.ossc.server.provider.entity;

import space.oldtaoge.ossc.server.commons.AbstractBaseDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author OldTaoge
 * @since 2021-06-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FkUmsClientCmsConfig extends AbstractBaseDomain {

    private static final long serialVersionUID = 1L;

    private Long clientId;

    private Long configId;


}
