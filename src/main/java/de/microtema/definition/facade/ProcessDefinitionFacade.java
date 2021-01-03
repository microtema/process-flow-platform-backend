package de.microtema.definition.facade;

import de.microtema.definition.service.ProcessDefinitionService;
import de.microtema.definition.vo.ProcessDefinition;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProcessDefinitionFacade {

    private final ProcessDefinitionService service;

    public Page<ProcessDefinition> getProcessDefinitions(int page, int size, String properties, String query) {

        return service.getProcessDefinitions(page, size, properties, query);
    }
}
