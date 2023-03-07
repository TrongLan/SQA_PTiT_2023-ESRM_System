package com.dtl.ersm.service;

import com.dtl.ersm.config.exceptions.ERSMCustomException;
import com.dtl.ersm.dtos.student.StudentDetailDTO;
import com.dtl.ersm.dtos.student.StudentSearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentService {
  Page<StudentDetailDTO> listStudentsByCriteria(
      String classCode, StudentSearchCriteria criteria, Pageable pageable)
      throws ERSMCustomException;
}
