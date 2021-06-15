package space.oldtaoge.ossc.server.provider.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import space.oldtaoge.ossc.server.commons.AbstractBaseController;
import space.oldtaoge.ossc.server.commons.dto.AbstractBaseResult;
import space.oldtaoge.ossc.server.commons.dto.BaseResultFactory;
import space.oldtaoge.ossc.server.provider.entity.CmsConfigLess;
import space.oldtaoge.ossc.server.provider.mapper.CmsConfigMapper;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author OldTaoge
 * @since 2021-06-15
 */
@RestController
@RequestMapping("/cms-config")
public class CmsConfigController extends AbstractBaseController {

}
