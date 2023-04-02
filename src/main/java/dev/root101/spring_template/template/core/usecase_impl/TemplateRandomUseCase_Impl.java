package dev.root101.spring_template.template.core.usecase_impl;

import dev.root101.clean.core.exceptions.ConflictException;
import dev.root101.clean.core.utils.validation.ValidationService;
import dev.root101.spring_template.template.core.domain.*;
import dev.root101.spring_template.template.core.usecase.*;
import dev.root101.spring_template.template.data.repo.*;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TemplateRandomUseCase_Impl implements TemplateRandomUseCase {

    @Autowired
    private TemplateRandomRepo repo;

    private final Random randomGenerator = new Random();

    //genera N cantidad de numeros
    private final int maxIters = 10;

    @Override
    public TemplateRandomDomain generate(int low, int max) {
        //itero para no pasarme del maximo de veces
        int count = 0;
        while (count++ < maxIters) {
            //genero un random en ese rango
            int generatedRandom = randomGenerator.nextInt(low, max);

            //busco el random en la bd
            TemplateRandomDomain oldRandom = repo.findByTemplateRandom(generatedRandom);

            //si no hay ningun random como ese
            if (oldRandom == null) {
                TemplateRandomDomain domain = new TemplateRandomDomain(generatedRandom);
                ValidationService.validate(domain);

                repo.create(domain);
                return domain;
            }
        }
        throw new ConflictException("More than %s generated randoms in [%s,%s] range.".formatted(maxIters, low, max));
    }

}
