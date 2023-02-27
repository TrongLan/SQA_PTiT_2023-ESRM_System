package com.dtl.ersm.domains;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "COMPONENT_SCORE_IN_CLASS")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ComponentScoreInClass {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "SBJ_CLASS_CODE")
  private String subjectClassCode;

  @Column(name = "COMPONENT_SCORE_TYPE_CODE")
  private String componentScoreTypeCode;

  @Column(name = "WEIGHT")
  private Integer weight;
}
