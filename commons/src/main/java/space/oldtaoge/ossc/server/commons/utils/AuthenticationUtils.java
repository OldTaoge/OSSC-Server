package space.oldtaoge.ossc.server.commons.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticationUtils {
    private static final AuthenticationUtils instance = new AuthenticationUtils();

    public LoginAble getLoginObj() {
        try {
            return new LoginAble();
        } catch (Exception e) {
            return null;
        }
    }

    public static class LoginAble {
        private final Authentication authentication;

        public LoginAble() throws Exception {
            authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null) {
                throw new Exception("No login info");
            }
        }

        public String getLoginClass() {
            String loginName = authentication.getName();
            if(loginName.startsWith("_c")) {
                return "UmsClient";
            } else if(loginName.startsWith("_u")) {
                return null;
            } else {
                return null;
            }
        }

        public String getRealName() {
            return authentication.getName().substring(2);
        }

        public Authentication getAuthentication() {
            return authentication;
        }
    }
    public static AuthenticationUtils getInstance() {
        return instance;
    }
}
