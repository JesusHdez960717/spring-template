package dev.root101.spring_template.rest_api.v1.model.random;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TemplateGenerateRandom_Request {

    int low;

    int max;
}
