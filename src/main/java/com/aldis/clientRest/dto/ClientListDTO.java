package com.aldis.clientRest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientListDTO {
    private Long id;
    private String name;
    private String lastName;
    private Integer age;
    private Date birthDate;
    private Date dateTeacherRetirement;//Probable date of teacher pension based on Colombia
}
