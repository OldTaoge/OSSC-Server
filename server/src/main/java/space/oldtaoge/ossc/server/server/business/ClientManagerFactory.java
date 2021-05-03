package space.oldtaoge.ossc.server.server.business;

import lombok.Data;
import space.oldtaoge.ossc.server.provider.entity.UmsClient;
import java.util.Map;

@Data
public class ClientManagerFactory {

    private static ClientManagerFactory clientManagerFactory;

    private Map<String, UmsClient> clientRegister;


    public static ClientManagerFactory getInstance() {
        if (clientManagerFactory == null) {
            synchronized (ClientManagerFactory.class) {
                if (clientManagerFactory == null) {
                    clientManagerFactory = new ClientManagerFactory();
                    // TODO: 临时使用内存保存模式,需要更新为数据库读取模式
                }
            }
        }
        return clientManagerFactory;
    }
}
