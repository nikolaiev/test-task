package com.example.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
public class Member {
    @Id
    private String id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private Date birthday;
    private int postalCode;
    private byte[] picture;
}
