package space.oldtaoge.ossc.server.provider.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import space.oldtaoge.ossc.server.commons.AbstractBaseDomain;

import java.sql.Timestamp;

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
public class CmsConfigLess extends AbstractBaseDomain {

    private static final long serialVersionUID = 1L;

    /**
     * 配置文件名称
     */
    private String name;

    /**
     * 最后修改
     */
    private Timestamp lastModify;
}
