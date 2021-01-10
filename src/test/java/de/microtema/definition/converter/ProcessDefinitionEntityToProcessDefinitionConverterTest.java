package de.microtema.definition.converter;

import de.microtema.definition.repository.ProcessDefinitionEntity;
import de.microtema.definition.vo.ProcessDefinition;
import de.seven.fate.model.builder.annotation.Model;
import de.seven.fate.model.builder.enums.ModelType;
import de.seven.fate.model.builder.util.FieldInjectionUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ProcessDefinitionEntityToProcessDefinitionConverterTest {

    @Inject
    ProcessDefinitionEntityToProcessDefinitionConverter sut;

    @Model(type = ModelType.MAX)
    ProcessDefinitionEntity model;

    @BeforeEach
    void setUp() {
        FieldInjectionUtil.injectFields(this);
    }

    @Test
    void convert() {

        ProcessDefinition answer = sut.convert(model);

        assertNotNull(answer);
        assertEquals(model.getUuid(), answer.getId());
        assertEquals(model.getDefinitionName(), answer.getName());
        assertEquals(model.getDefinitionDescription(), answer.getDescription());
        assertEquals(model.getDefinitionDiagram(), answer.getDiagram());
        assertEquals(model.getDefinitionName(), answer.getName());
        assertEquals(model.getDefinitionDisplayName(), answer.getDisplayName());
        assertEquals(model.getDefinitionMajorVersion(), answer.getVersion());
        assertEquals(model.getDefinitionKey(), answer.getKey());
        assertEquals(model.getDefinitionDeployTime(), answer.getDeployTime());
    }
}
