package de.microtema.definition.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ProcessDefinition {

    @NotNull
    private UUID id;

    @NotNull
    private String key;

    @NotNull
    private String name;

    @NotNull
    private String displayName;

    @NotNull
    private Integer version;

    private String description;

    private String diagram;

    @NotNull
    private LocalDateTime deployTime;
}
