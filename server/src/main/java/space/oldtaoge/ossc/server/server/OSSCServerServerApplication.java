package space.oldtaoge.ossc.server.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "space.oldtaoge.ossc.server.server.mapper")
public class OSSCServerServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(OSSCServerServerApplication.class, args);
    }
}
