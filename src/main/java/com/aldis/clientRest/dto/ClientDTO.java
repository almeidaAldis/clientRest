package com.aldis.clientRest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {
    @JsonProperty(value = "name", required = true)
    @NotEmpty(message = "Name is required")
    private String name;

    @JsonProperty(value = "lastName", required = true)
    @NotEmpty(message = "lastName is required")
    private String lastName;

    @JsonProperty(value = "age", required = true)
    @NotNull(message = "age cannot be null")
    private Integer age;

    @JsonProperty(value = "birthDate", required = true)
    @NotNull(message = "birthDate cannot be null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    private Date birthDate;
}
