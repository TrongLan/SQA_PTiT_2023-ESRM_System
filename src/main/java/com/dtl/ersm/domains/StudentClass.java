package com.dtl.ersm.domains;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "STUDENT_CLASS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class StudentClass {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "SBJ_CLASS_CODE")
  private String subjectClassCode;

  @Column(name = "STUDENT_CODE")
  private String studentCode;

  @Column(name = "STATUS")
  private Integer status;
}
