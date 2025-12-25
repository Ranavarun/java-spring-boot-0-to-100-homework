package com.CoadingShuttle.demo.entitites;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Department")
public class DepartmentEntities {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String tittle;
    @JsonProperty("isActive")
    private Boolean isActive;
    private LocalDateTime localDateTime;
}
