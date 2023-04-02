package dev.root101.spring_template.rest_api.v1.rest;

import dev.root101.clean.core.rest.ApiResponse;
import dev.root101.spring_template.rest_api.v1.model.random.*;
import dev.root101.spring_template.template.core.usecase.*;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "Template Random Controller", description = "Random number controller")
@RestController
@RequestMapping("/random")
public class TemplateRandomController {

    public static final String GENERATE = "/generate";

    @Autowired
    private TemplateRandomUseCase randomUC;

    @PostMapping(GENERATE)
    @Operation(
            summary = "Generate random number in range",
            description = "Generate random number in range specified in body."
    )
    public ApiResponse<TemplateRandomResponse> generate(@RequestBody TemplateGenerateRandom_Request body) {
        return ApiResponse.success(
                new TemplateRandomResponse(
                        randomUC.generate(
                                body.getLow(),
                                body.getMax()
                        ).getTemplateRandom()
                )
        );
    }

}
