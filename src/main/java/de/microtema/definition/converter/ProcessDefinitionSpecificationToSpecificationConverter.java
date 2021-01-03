package de.microtema.definition.converter;

import de.microtema.definition.repository.ProcessDefinitionEntity;
import de.microtema.definition.vo.ProcessDefinitionSpecification;
import de.microtema.model.converter.Converter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class ProcessDefinitionSpecificationToSpecificationConverter implements Converter<Specification<ProcessDefinitionEntity>, ProcessDefinitionSpecification> {

    @Override
    public Specification<ProcessDefinitionEntity> convert(ProcessDefinitionSpecification orig) {
        return (root, criteriaQuery, criteriaBuilder) -> {

            Predicate predicate = getGroupByPredicate(orig, root, criteriaBuilder, criteriaQuery);
            Predicate orPredicate = getOrPredicate(orig, root, criteriaBuilder);

            if (Objects.nonNull(orPredicate)) {
                predicate = criteriaBuilder.and(predicate, orPredicate);
            }

            return criteriaQuery.where(predicate).getGroupRestriction();
        };
    }

    private Predicate getOrPredicate(ProcessDefinitionSpecification orig, Root<ProcessDefinitionEntity> root, CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<>();

        Predicate predicate = getLikePredicate(root, criteriaBuilder, "definitionDisplayName", orig.getQuery());
        CollectionUtils.addIgnoreNull(predicates, predicate);

        predicate = getLikePredicate(root, criteriaBuilder, "definitionDescription", orig.getQuery());
        CollectionUtils.addIgnoreNull(predicates, predicate);

        if (CollectionUtils.isEmpty(predicates)) {
            return null;
        }

        return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
    }

    private Predicate getLikePredicate(Root<ProcessDefinitionEntity> root, CriteriaBuilder criteriaBuilder, String property, String query) {

        if (StringUtils.isEmpty(query)) {
            return null;
        }

        Path<String> path = root.get(property);

        return criteriaBuilder.like(criteriaBuilder.lower(path), "%" + query.toLowerCase() + "%");
    }

    private Predicate getGroupByPredicate(ProcessDefinitionSpecification orig, Root<ProcessDefinitionEntity> root, CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery) {

        Subquery<Integer> subQuery = criteriaQuery.subquery(Integer.class);

        Root<ProcessDefinitionEntity> subRoot = subQuery.from(ProcessDefinitionEntity.class);
        subQuery.select(criteriaBuilder.max(subRoot.get("definitionVersion"))).where(criteriaBuilder.equal(root.get(orig.getGroupBy()), subRoot.get(orig.getGroupBy())));

        return criteriaBuilder.equal(root.get("definitionVersion"), subQuery);
    }
}
