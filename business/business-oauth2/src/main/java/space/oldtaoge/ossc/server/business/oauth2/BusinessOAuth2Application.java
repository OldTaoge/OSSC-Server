package space.oldtaoge.ossc.server.business.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;
import java.io.Console;

@SpringBootApplication
public class BusinessOAuth2Application {

    public static void main(String[] args) {
        SpringApplication.run(BusinessOAuth2Application.class, args);
    }

}
