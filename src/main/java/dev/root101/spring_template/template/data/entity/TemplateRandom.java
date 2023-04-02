package dev.root101.spring_template.template.data.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "template_random", schema = "random-template", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"template_random"})})
public class TemplateRandom implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "template_random_id", nullable = false)
    private Integer templateRandomId;

    @Basic(optional = false)
    @Column(name = "template_random", nullable = false)
    private int templateRandom;

}
