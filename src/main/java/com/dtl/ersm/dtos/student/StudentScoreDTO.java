package com.dtl.ersm.dtos.student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentScoreDTO {
  private String studentCode;
  private String classCode;
  private Map<Long, Float> scoreMap;
}
