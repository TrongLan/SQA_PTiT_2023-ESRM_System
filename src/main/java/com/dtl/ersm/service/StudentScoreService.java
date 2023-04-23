package com.dtl.ersm.service;

import com.dtl.ersm.config.exceptions.ERSMCustomException;
import com.dtl.ersm.domains.StudentComponentScore;
import com.dtl.ersm.dtos.student.StudentScoreDTO;

import java.util.List;
import java.util.Map;

public interface StudentScoreService {
  Map<String, Map<Long, Double>> getStudentScoresInClass(String classCode)
      throws ERSMCustomException;

  List<StudentComponentScore> scoreUpdate(StudentScoreDTO dto) throws ERSMCustomException;
}
