package com.dtl.ersm.dtos.subjectclass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentClassFilterCriteria {
  private String subjectCode;
  private String subjectName;
}
