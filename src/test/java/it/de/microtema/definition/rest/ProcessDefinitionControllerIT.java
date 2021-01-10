package it.de.microtema.definition.rest;

import de.microtema.BackendApplication;
import de.microtema.definition.rest.ProcessDefinitionController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;
import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = BackendApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProcessDefinitionControllerIT {

    @Inject
    ProcessDefinitionController sut;

    @Inject
    TestRestTemplate restTemplate;

    String url = "/rest/definition?page=0&size=10&query=foo&properties=!definitionName";

    @BeforeEach
    public void setup() {
        restTemplate.getRestTemplate().setInterceptors(Collections.singletonList(((request, body, execution) -> {

            String token = "Bearer eyJraWQiOiJoa1FJYU5qMGM3M3k2ekQ5M3Z6eTluM2k4czRqb21pQmViWDI0d3dGNlNvIiwiYWxnIjoiUlMyNTYifQ.eyJ2ZXIiOjEsImp0aSI6IkFULk1qVmVkWExFLXF1NTJzZW1CbzFtVXZzdGZXTVVQQ3N2dTM0aUgzTk01VDAiLCJpc3MiOiJodHRwczovL2Rldi0xMDY1NzgyLm9rdGEuY29tL29hdXRoMi9kZWZhdWx0IiwiYXVkIjoiYXBpOi8vZGVmYXVsdCIsImlhdCI6MTYxMDI3NjI0NSwiZXhwIjoxNjEwMjc5ODQ1LCJjaWQiOiIwb2EzaDBtcTJrd1J4ZTloTzVkNiIsInVpZCI6IjAwdTNoMjFjbTFDdXpoQVhWNWQ2Iiwic2NwIjpbInByb2ZpbGUiLCJlbWFpbCIsIm9wZW5pZCJdLCJzdWIiOiJtaWNyb3RlbWFAd2ViLmRlIn0.bJDZyj7cadvn5zqdG6xgPp8uBkCJPgbmb7te2uckWAOp_9BkziJMmrN0tHxHO-3r8B_jNy09TckzQXp-Ev4Ha5S3zQllfblSUyzvHFQKROI8vWKBFn8Xu7AUa8iRW03z4HUxl3Evtgqk2RL1BPedHsOAwSY5U69qPPJN1GpJ1VyNBFcbOfm0H02xHo_Vlk4Y4s2_4IXo6N4oNFkqFmy_WSTCugzhHRBYHeSKFtGmqX5hBe9xjn_lrzrh2MjY_rr6eFt6OLsEXxmYDm0ASewBqj7NKiw3Og7sRgF0rg5R890AV7gN3uYgrTAG8pjHtbJ1MIMvtoldDrYuhMa7321JSw";

            HttpHeaders headers = request.getHeaders();

            headers.add("Authorization", token);

            return execution.execute(request, body);
        })));
    }

    @Test
    void processDefinitions() {

        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, null, Map.class);

        assertNotNull(response);
        assertSame(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}
