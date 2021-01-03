package de.microtema.definition.vo;

import lombok.Value;

@Value(staticConstructor = "of")
public class PageRequestDTO {

    int page;

    int size;

    String properties;
}
