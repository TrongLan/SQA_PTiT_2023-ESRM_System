package com.dtl.ersm.domains;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Table(name = "SBJ_CLASS")
public class SubjectClass {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "CODE")
  private String code;

  @Column(name = "NAME")
  private String name;

  @Column(name = "SBJ_CODE")
  private String subjectCode;

  @Column(name = "LECTURE_CODE")
  private String lectureCode;
}
