package com.dtl.ersm.dtos.student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentSearchCriteria {
  private String code;
  private String fullName;
  private Integer status;
}
