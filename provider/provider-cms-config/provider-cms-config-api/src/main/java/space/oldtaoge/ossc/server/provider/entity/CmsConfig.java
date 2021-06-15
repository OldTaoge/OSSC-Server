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
public class CmsConfig extends AbstractBaseDomain {

    private static final long serialVersionUID = 1L;

    /**
     * 配置文件名称
     */
    private String name;

    /**
     * 内容
     */
    private String content;


}
