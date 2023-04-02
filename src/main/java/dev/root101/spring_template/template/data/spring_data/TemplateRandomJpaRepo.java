package dev.root101.spring_template.template.data.spring_data;

import dev.root101.spring_template.template.data.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplateRandomJpaRepo extends JpaRepository<TemplateRandom, Integer> {

    public TemplateRandom findByTemplateRandomId(int randomId);

    public TemplateRandom findByTemplateRandom(int randomValue);

}
