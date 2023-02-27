package com.dtl.ersm.domains;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "COMPONENT_SCORE_TYPE")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ComponentScoreType {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "CODE")
  private String code;

  @Column(name = "NAME")
  private String name;

  @Column(name = "DESCRIPTION")
  private String description;
}
