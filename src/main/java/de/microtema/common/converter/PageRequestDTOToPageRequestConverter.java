package de.microtema.common.converter;

import de.microtema.definition.vo.PageRequestDTO;
import de.microtema.model.converter.Converter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Stream;

@Component
public class PageRequestDTOToPageRequestConverter implements Converter<PageRequest, PageRequestDTO> {

    private static final String NEGATION = "!";
    private static final String DEFAULT_PROPERTY = "startTime";
    private static final String STRING_SEPARATOR = ",";

    @Override
    public PageRequest convert(PageRequestDTO orig) {

        String properties = Optional.of(StringUtils.trimToEmpty(orig.getProperties())).orElse(DEFAULT_PROPERTY);
        Sort sort = getSort(properties);

        return PageRequest.of(orig.getPage(), orig.getSize(), sort);
    }

    private Sort getSort(String properties) {
        String[] propertiesAsArray = StringUtils.split(properties, STRING_SEPARATOR);

        Sort.Order[] orders = Stream.of(propertiesAsArray).map(this::getOrder).toArray(Sort.Order[]::new);

        return Sort.by(orders);
    }

    private Sort.Order getOrder(String property) {

        if (property.startsWith(NEGATION)) {
            return Sort.Order.asc(property.replace(NEGATION, StringUtils.EMPTY));
        }

        return Sort.Order.desc(property).nullsLast();
    }
}
