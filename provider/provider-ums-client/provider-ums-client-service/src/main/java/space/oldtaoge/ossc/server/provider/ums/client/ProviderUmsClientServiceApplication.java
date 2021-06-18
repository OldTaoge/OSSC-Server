package space.oldtaoge.ossc.server.provider.ums.client;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
@MapperScan(basePackages = "space.oldtaoge.ossc.server.provider.ums.client.mapper")
public class ProviderUmsClientServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProviderUmsClientServiceApplication.class, args);
    }
}
