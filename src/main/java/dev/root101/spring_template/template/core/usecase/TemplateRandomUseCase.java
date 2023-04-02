package dev.root101.spring_template.template.core.usecase;

import dev.root101.spring_template.template.core.domain.*;

public interface TemplateRandomUseCase {

    public TemplateRandomDomain generate(int low, int max);

}
