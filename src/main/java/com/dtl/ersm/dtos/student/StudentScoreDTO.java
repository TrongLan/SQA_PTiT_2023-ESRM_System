package com.dtl.ersm.dtos.student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
public class StudentScoreDTO {
  private String studentCode;
  private String classCode;
  private Map<Long, Float> scoreMap;

}
