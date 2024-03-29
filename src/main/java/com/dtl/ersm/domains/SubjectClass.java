package com.dtl.ersm.domains;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
@Table(name = "SBJ_CLASS")
public class SubjectClass {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "CODE")
  private String code;

  @Column(name = "No")
  private String name;

  @Column(name = "SBJ_CODE")
  private String subjectCode;

  @Column(name = "LECTURE_CODE")
  private String lectureCode;
}
