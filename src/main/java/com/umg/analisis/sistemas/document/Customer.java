package com.umg.analisis.sistemas.document;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
@Getter
@Setter
public class Customer {
    @Id
    private String id;
    private Integer userId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String birthDay;
    private LocalDateTime createdAt;
    private LocalDateTime processedAt;
    private LocalDateTime sendAt;
    private LocalDateTime modifiedAt;
}
