package de.microtema.definition.rest;

import de.microtema.definition.facade.ProcessDefinitionFacade;
import de.microtema.definition.vo.ProcessDefinition;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/rest/definition")
public class ProcessDefinitionController {

    private final ProcessDefinitionFacade facade;

    @GetMapping
    public ResponseEntity<Page<ProcessDefinition>> getProcessDefinitions(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "25") int size,
            @RequestParam(name = "properties", required = false) String properties,
            @RequestParam(name = "query", required = false) String query) {

        return ResponseEntity.ok(facade.getProcessDefinitions(page, size, properties, query));
    }
}
