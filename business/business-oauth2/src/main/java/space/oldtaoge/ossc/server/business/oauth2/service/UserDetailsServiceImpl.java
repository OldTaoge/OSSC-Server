package space.oldtaoge.ossc.server.business.oauth2.service;

import com.google.common.collect.Lists;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import space.oldtaoge.ossc.server.provider.ums.client.entity.UmsClient;
import space.oldtaoge.ossc.server.provider.ums.client.entity.service.IUmsClientService;
import space.oldtaoge.ossc.server.provider.ums.user.entity.UmsUser;
import space.oldtaoge.ossc.server.provider.ums.user.service.IUmsUserService;

import java.util.List;

/**
 * 自定义认证
 * <p>
 * Description:
 * </p>
 *
 * @author Lusifer
 * @version v1.0.0
 * @see space.oldtaoge.ossc.server.business.oauth2.service
 *
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final String USERNAME = "admin";
    private static final String PASSWORD = "$2a$10$YNUV/BtCel2orbhgrxyPJeljdKVty6yTAL.Cj4v3XhwHWXBkgyPYW";

    @DubboReference
    IUmsClientService umsClientService;

    @DubboReference
    IUmsUserService umsUserService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = null;
        if (s.startsWith("_c")) {
            UmsClient umsClient = umsClientService.getByUUID(s.substring(2));
            if (umsClient != null) {
                List<GrantedAuthority> grantedAuthorities = Lists.newArrayList();
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("client");
                grantedAuthorities.add(grantedAuthority);
                user = new User(s, umsClient.getPassword(), grantedAuthorities);
            }
        } else if(s.startsWith("_u")) {
            UmsUser umsUser = umsUserService.getByUUID(s.substring(2));
            if (umsUser != null) {
                List<GrantedAuthority> grantedAuthorities = Lists.newArrayList();
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("user");
                grantedAuthorities.add(grantedAuthority);
                user = new User(s, umsUser.getPassword(), grantedAuthorities);
            }
        }
        if (user == null) {
            throw new UsernameNotFoundException(null);
        }
        return user;
    }
}
