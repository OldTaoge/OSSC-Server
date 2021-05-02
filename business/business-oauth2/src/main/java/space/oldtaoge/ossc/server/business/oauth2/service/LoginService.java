package space.oldtaoge.ossc.server.business.oauth2.service;

import org.springframework.lang.NonNull;

import java.util.Map;

public interface LoginService {
    Map<String, String> baseLogin(@NonNull String username, @NonNull String password);
}
