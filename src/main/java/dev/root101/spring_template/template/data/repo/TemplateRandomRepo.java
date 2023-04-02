package dev.root101.spring_template.template.data.repo;

import dev.root101.clean.core.repo.DelegatedSpringJpaRepo;
import dev.root101.spring_template.template.core.domain.*;
import dev.root101.spring_template.template.data.entity.*;
import dev.root101.spring_template.template.data.repo.converter.*;
import dev.root101.spring_template.template.data.spring_data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TemplateRandomRepo extends DelegatedSpringJpaRepo<TemplateRandomDomain, TemplateRandom, Integer, TemplateRandomConverter, TemplateRandomJpaRepo> {

    @Autowired
    public TemplateRandomRepo(TemplateRandomJpaRepo springRepo, TemplateRandomConverter converter) {
        super(
                springRepo,
                converter
        );
    }

    public TemplateRandomDomain findByTemplateRandomId(int randomId) {
        TemplateRandom finded = repo().findByTemplateRandomId(randomId);
        return finded != null ? converter.toDomain(finded) : null;
    }

    public TemplateRandomDomain findByTemplateRandom(int randomValue) {
        TemplateRandom finded = repo().findByTemplateRandom(randomValue);
        return finded != null ? converter.toDomain(finded) : null;
    }
}
