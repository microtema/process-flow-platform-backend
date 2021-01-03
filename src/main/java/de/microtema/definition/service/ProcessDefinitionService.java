package de.microtema.definition.service;

import de.microtema.common.converter.PageRequestDTOToPageRequestConverter;
import de.microtema.definition.converter.ProcessDefinitionEntityToProcessDefinitionConverter;
import de.microtema.definition.converter.ProcessDefinitionSpecificationToSpecificationConverter;
import de.microtema.definition.repository.ProcessDefinitionEntity;
import de.microtema.definition.repository.ProcessDefinitionRepository;
import de.microtema.definition.vo.PageRequestDTO;
import de.microtema.definition.vo.ProcessDefinition;
import de.microtema.definition.vo.ProcessDefinitionSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProcessDefinitionService {

    private final ProcessDefinitionRepository repository;
    private final ProcessDefinitionEntityToProcessDefinitionConverter converter;
    private final ProcessDefinitionSpecificationToSpecificationConverter specificationConverter;
    private final PageRequestDTOToPageRequestConverter pageRequestConverter;

    public Page<ProcessDefinition> getProcessDefinitions(int page, int size, String properties, String query) {

        ProcessDefinitionSpecification processDefinitionSpecification = ProcessDefinitionSpecification.builder().query(query).groupBy("definitionKey").build();
        PageRequestDTO pageRequestDTO = PageRequestDTO.of(page, size, properties);

        Specification<ProcessDefinitionEntity> specification = specificationConverter.convert(processDefinitionSpecification);
        Pageable pageable = pageRequestConverter.convert(pageRequestDTO);

        return repository.findAll(specification, pageable).map(converter::convert);
    }
}
