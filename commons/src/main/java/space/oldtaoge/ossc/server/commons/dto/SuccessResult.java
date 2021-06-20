package space.oldtaoge.ossc.server.commons.dto;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import space.oldtaoge.ossc.server.commons.AbstractBaseDomain;

import java.util.List;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
public class SuccessResult<T extends AbstractBaseDomain> extends AbstractBaseResult{
    private Links links;
    private List<DataBean<T>> data;

    public SuccessResult(String self, T attributes) {
        links = new Links();
        links.setSelf(self);

        createDataBean(null, attributes);
    }

    public SuccessResult(String self, Long current, Long length, Long total, String dataBaseLink, List<? extends T> attributes) {
        links = new Links();
        links.setSelf(self);
        long lastPage = total/length + (length > total? 1:0);
        links.setNext(self + "?length=" + length + "&current=" + current);
        links.setLast(self + "?length=" + length + "&current=" + lastPage);

        attributes.forEach(attribute -> createDataBean(dataBaseLink != null ? dataBaseLink : self, attribute));
    }

    private void createDataBean(String self,T attributes) {
        if (data == null) {
            data = Lists.newArrayList();
        }

        DataBean<T> dataBean = new DataBean<>();
        dataBean.setId(attributes.getId());
        dataBean.setType(attributes.getClass().getSimpleName());
        dataBean.setAttributes(attributes);

        if (StringUtils.isNotBlank(self)) {
            Links links = new Links();
            links.setSelf(self + "/" + attributes.getId());
            dataBean.setLinks(links);
        }

        data.add(dataBean);
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SuccessData extends AbstractBaseDomain {
        private String type;
        private Map<String, Object> attributes;
    }

}
