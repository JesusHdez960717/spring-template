package dev.root101.spring_template.template.core.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TemplateRandomDomain {

    private Integer templateRandomId;

    private int templateRandom;

    public TemplateRandomDomain(int templateRandom) {
        this.templateRandom = templateRandom;
    }

}
