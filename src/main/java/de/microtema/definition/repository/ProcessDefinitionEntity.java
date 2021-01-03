package de.microtema.definition.repository;

import de.microtema.common.repository.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Entity(name = "ProcessDefinition")
@Table(name = "ProcessDefinition")
public class ProcessDefinitionEntity extends BaseEntity implements Comparable<ProcessDefinitionEntity> {

    @NotNull
    private String definitionKey;

    @NotNull
    private String definitionName;

    @NotNull
    private String definitionDisplayName;

    @NotNull
    private Integer definitionVersion;

    @NotNull
    private Integer definitionMajorVersion;

    @NotNull
    private LocalDateTime definitionDeployTime;

    @NotNull
    private String definitionDescription;

    @NotNull
    @Column(columnDefinition = "Text")
    private String definitionDiagram;

    @Override
    public int compareTo(ProcessDefinitionEntity o) {

        return o.getDefinitionDeployTime().compareTo(definitionDeployTime);
    }
}
