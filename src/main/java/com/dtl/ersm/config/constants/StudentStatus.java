package com.dtl.ersm.config.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
@Getter
public enum StudentStatus {
  UNDEFINED(0, "N/A"),
  ELIGIBLE(1, "Đủ điều kiện dự thi"),
  INELIGIBLE(2, "Không đủ điều kiện dự thi");
  private final Integer statusCode;
  private final String statusDescription;

  public static Map<Integer, String> getStudentStatusMap() {
    return Stream.of(StudentStatus.values())
        .collect(
            Collectors.toMap(StudentStatus::getStatusCode, StudentStatus::getStatusDescription));
  }
}
