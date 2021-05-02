package space.oldtaoge.ossc.server.commons;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class AbstractBaseDomain implements Serializable {
    private Long id;
}
