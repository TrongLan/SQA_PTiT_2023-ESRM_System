package com.dtl.ersm.dtos.componentscore;

import com.dtl.ersm.domains.ComponentScoreInClass;
import com.dtl.ersm.domains.ComponentScoreType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ComponentScoreDetail {
  private Long id;
  private String componentScoreCode;
  private String componentScoreName;
  private Integer weight;

  public ComponentScoreDetail(ComponentScoreInClass scoreInClass, ComponentScoreType type) {
    this.id = scoreInClass.getId();
    this.componentScoreCode = type.getCode();
    this.componentScoreName = type.getName();
    this.weight = scoreInClass.getWeight();
  }
}
