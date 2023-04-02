package dev.root101.spring_template.template.data.repo.converter;

import dev.root101.clean.core.repo.Converter;
import dev.root101.spring_template.template.core.domain.*;
import dev.root101.spring_template.template.data.entity.*;
import org.springframework.stereotype.Service;

@Service
public class TemplateRandomConverter implements Converter<TemplateRandomDomain, TemplateRandom> {

    @Override
    public TemplateRandomDomain toDomain(TemplateRandom entity) throws RuntimeException {
        return new TemplateRandomDomain(
                entity.getTemplateRandomId(),
                entity.getTemplateRandom()
        );
    }

    @Override
    public TemplateRandom toEntity(TemplateRandomDomain domain) throws RuntimeException {
        return new TemplateRandom(
                domain.getTemplateRandomId(),
                domain.getTemplateRandom()
        );
    }

}
