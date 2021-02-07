package space.oldtaoge.ossc.server.commons.dto;

import space.oldtaoge.ossc.server.commons.AbstractBaseDomain;

import java.util.List;

public class BaseResultFactory<T extends AbstractBaseDomain> {

    private static final String LOG_LEVEL = "DEBUG";

    private static BaseResultFactory<AbstractBaseDomain> baseResultFactory;

    private BaseResultFactory() {

    }

    public static BaseResultFactory<AbstractBaseDomain> getInstance() {
        if (baseResultFactory == null) {
            synchronized (BaseResultFactory.class) {
                if (baseResultFactory == null) {
                    baseResultFactory = new BaseResultFactory<>();
                }
            }
        }
        return baseResultFactory;
    }


    public AbstractBaseResult build(String self, T attributes) {
        return new SuccessResult<>(self, attributes);
    }


    public AbstractBaseResult build(String self, int next, int last, List<T> attributes) {
        return new SuccessResult<>(self, next, last, attributes);
    }


    public AbstractBaseResult build(int code, String title, String detail, String level) {
        if (LOG_LEVEL.equals(level)) {
            return new ErrorResult(code, title, detail);
        } else {
            return new ErrorResult(code, title, null);
        }
    }
}