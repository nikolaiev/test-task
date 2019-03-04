package com.example.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by vladyslav on 03.03.19.
 */
@Data
@AllArgsConstructor
public class Member {
    @Id
    private String id;

    private String firstName;
    private String lastName;
    private Date birthday;
    private int postalCode;
    private byte[] picture;
}
