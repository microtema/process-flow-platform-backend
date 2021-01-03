package de.microtema.common.repository;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@Data
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue
    private UUID uuid;
}
