package dev.root101.spring_template.rest_api.v1.controller;

import dev.root101.clean.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Yo
 */
@Slf4j
@Tag(name = "Test Controller", description = "Test")
@RestController
@RequestMapping("/test")
public class TemplateTestController {

    @GetMapping("/hi")
    @Operation(
            summary = "Say hi.",
            description = "Say helou world."
    )
    public ApiResponse<String> hi() {
        return ApiResponse.success(
                "Helou world"
        );
    }
}
