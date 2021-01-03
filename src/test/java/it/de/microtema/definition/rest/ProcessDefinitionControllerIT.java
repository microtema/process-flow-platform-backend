package it.de.microtema.definition.rest;

import de.microtema.BackendApplication;
import de.microtema.definition.rest.ProcessDefinitionController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = BackendApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProcessDefinitionControllerIT {

    @Inject
    ProcessDefinitionController sut;

    @Inject
    TestRestTemplate restTemplate;

    String url = "/rest/definition?page=0&size=10&query=foo&properties=!definitionName";

    @Test
    void getgetProcessDefinitions(){

        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, null, Map.class);

        assertNotNull(response);
        assertSame(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}
