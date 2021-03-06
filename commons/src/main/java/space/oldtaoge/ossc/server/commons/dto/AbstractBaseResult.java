package space.oldtaoge.ossc.server.commons.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import space.oldtaoge.ossc.server.commons.AbstractBaseDomain;

import java.io.Serializable;

/**
 * 数据传输对象抽象类
 */
@Data
public abstract class AbstractBaseResult implements Serializable {

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected static class Links {
        private String self;
        private String next;
        private String last;
    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected static class DataBean<T extends AbstractBaseDomain> {
        private String type;
        private Long id;
        private T attributes;
        private T relationships;
        private Links links;
    }
}
