package com.CoadingShuttle.demo.Dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {
    private Long id;
    private String tittle;
    @JsonProperty("isActive")
    private Boolean isActive;
    private LocalDateTime localDateTime;
}
