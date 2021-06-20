package space.oldtaoge.ossc.server.provider.cms.config.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import space.oldtaoge.ossc.server.provider.cms.config.entity.CmsConfig;
import space.oldtaoge.ossc.server.provider.cms.config.entity.CmsConfigLess;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author OldTaoge
 * @since 2021-06-15
 */
public interface CmsConfigMapper extends BaseMapper<CmsConfig> {
    IPage<CmsConfigLess> selectListByCliIdPages(Page<CmsConfigLess> page, @Param("value")Long cliId);

}
