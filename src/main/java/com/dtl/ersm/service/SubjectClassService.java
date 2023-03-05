package com.dtl.ersm.service;

import com.dtl.ersm.config.exceptions.ERSMCustomException;
import com.dtl.ersm.dtos.subjectclass.ClassDTO;

import java.util.List;

public interface SubjectClassService {
  List<ClassDTO> getClassesOfLecture(String lectureCode, String subjectCode, String subjectName)
      throws ERSMCustomException;
}
