package space.oldtaoge.ossc.server.provider;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
@MapperScan(basePackages = "space.oldtaoge.ossc.server.provider.mapper")
public class ProviderCmsConfigServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProviderCmsConfigServiceApplication.class, args);
    }
}