package io.github.jun.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Endpoint {

    @Id
    @SequenceGenerator(name = "endpoint_id_seq", sequenceName = "endpoint_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "endpoint_id_seq")
    private Long id;

    @Column(nullable = false)
    private String endPointName;
}