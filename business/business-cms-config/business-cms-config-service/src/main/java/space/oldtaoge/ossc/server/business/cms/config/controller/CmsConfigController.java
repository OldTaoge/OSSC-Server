package space.oldtaoge.ossc.server.business.cms.config.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import space.oldtaoge.ossc.server.business.CmsConfigDubbo;
import space.oldtaoge.ossc.server.commons.dto.AbstractBaseResult;
import space.oldtaoge.ossc.server.commons.dto.BaseResultFactory;
import space.oldtaoge.ossc.server.provider.entity.CmsConfigLess;
import space.oldtaoge.ossc.server.provider.service.ICmsConfigService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@DubboService
@RestController
@RequestMapping("config")
public class CmsConfigController implements CmsConfigDubbo {

    @DubboReference
    ICmsConfigService cmsConfigService;

    @Resource
    HttpServletRequest request;

    @GetMapping("{id}")
    AbstractBaseResult getById(@PathVariable Long id)
    {
        return BaseResultFactory.getInstance().build(request.getRequestURI(), cmsConfigService.getById(id));
    }

    @GetMapping("cli/{id}")
    AbstractBaseResult getByCliId(@PathVariable Long id, @RequestParam Long current, @RequestParam Long length)
    {
        IPage<CmsConfigLess> iPage = cmsConfigService.getListByCliId(id, current, length);
        return BaseResultFactory.getInstance().build(request.getRequestURI(), iPage.getCurrent(), iPage.getSize(),
                iPage.getTotal(), iPage.getRecords());
    }

}
