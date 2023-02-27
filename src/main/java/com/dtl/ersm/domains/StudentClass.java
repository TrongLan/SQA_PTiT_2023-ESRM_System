package com.dtl.ersm.domains;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "STUDENT_CLASS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentClass {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "SBJ_CLASS_CODE")
  private String subjectClassCode;

  @Column(name = "STUDENT_CODE")
  private String studentCode;
}
