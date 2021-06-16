package space.oldtaoge.ossc.server.business.cms.config.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import space.oldtaoge.ossc.server.business.CmsConfigDubbo;
import space.oldtaoge.ossc.server.commons.CodeStatus;
import space.oldtaoge.ossc.server.commons.dto.AbstractBaseResult;
import space.oldtaoge.ossc.server.commons.dto.BaseResultFactory;
import space.oldtaoge.ossc.server.provider.entity.CmsConfigLess;
import space.oldtaoge.ossc.server.provider.entity.UmsClient;
import space.oldtaoge.ossc.server.provider.service.ICmsConfigService;
import space.oldtaoge.ossc.server.provider.service.IFkUmsClientCmsConfigService;
import space.oldtaoge.ossc.server.provider.service.IUmsClientService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@DubboService
@RestController
@RequestMapping("config")
public class CmsConfigController implements CmsConfigDubbo {

    @DubboReference
    ICmsConfigService cmsConfigService;

    @DubboReference
    IFkUmsClientCmsConfigService fkUmsClientCmsConfigService;

    @DubboReference
    IUmsClientService umsClientService;

    @Resource
    HttpServletRequest request;

    @Override
    @GetMapping("{id}")
//    @PreAuthorize("hasAuthority('CLI')")
    public AbstractBaseResult getById(@PathVariable Long id)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UmsClient umsclient = umsClientService.getByUUID(authentication.getName());

        if(fkUmsClientCmsConfigService.checkHasPer(umsclient.getId(), id) == 1)
            return BaseResultFactory.getInstance().build(request.getRequestURI(), cmsConfigService.getById(id));
        else {
           return BaseResultFactory.getInstance().build(CodeStatus.BadRequest, "You have no permission to get this config", null, null);
        }
    }

    @Override
    @GetMapping( "cli")
//    @PreAuthorize("hasAuthority('CLI')")
    public AbstractBaseResult getByCliId(@RequestParam Long current, @RequestParam Long length)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UmsClient umsclient = umsClientService.getByUUID(authentication.getName());

        IPage<CmsConfigLess> iPage = cmsConfigService.getListByCliId(umsclient.getId(), current, length);
        return BaseResultFactory.getInstance().build(request.getRequestURI(), iPage.getCurrent(), iPage.getSize(),
                iPage.getTotal(), iPage.getRecords());
    }

}
