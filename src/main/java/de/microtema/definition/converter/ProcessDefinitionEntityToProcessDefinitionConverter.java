package de.microtema.definition.converter;

import de.microtema.definition.repository.ProcessDefinitionEntity;
import de.microtema.definition.vo.ProcessDefinition;
import org.springframework.stereotype.Component;
import de.microtema.model.converter.Converter;

@Component
public class ProcessDefinitionEntityToProcessDefinitionConverter implements Converter<ProcessDefinition, ProcessDefinitionEntity> {

    @Override
    public void update(ProcessDefinition dest, ProcessDefinitionEntity orig) {

        dest.setKey(orig.getDefinitionKey());
        dest.setName(orig.getDefinitionName());
        dest.setDisplayName(orig.getDefinitionDisplayName());
        dest.setDescription(orig.getDefinitionDescription());

        dest.setDeployTime(orig.getDefinitionDeployTime());
        dest.setVersion(orig.getDefinitionMajorVersion());

        dest.setDiagram(orig.getDefinitionDiagram());
        dest.setId(orig.getUuid());
    }
}
