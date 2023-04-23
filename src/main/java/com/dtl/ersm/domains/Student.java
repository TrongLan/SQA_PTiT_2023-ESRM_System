package com.dtl.ersm.domains;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "STUDENT")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Student {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "CODE")
  private String code;

  @Column(name = "FIRST_NAME")
  private String firstName;

  @Column(name = "LAST_NAME")
  private String lastName;

  @Column(name = "GENDER")
  private String gender;

  @Column(name = "BIRTH_DATE")
  private LocalDate birthDate;

  @Column(name = "STATUS")
  private Integer status;
}
