package de.microtema.definition.vo;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Value
public class ProcessDefinitionSpecification {

    @NotNull
    String query;

    @NotNull
    String groupBy;
}
