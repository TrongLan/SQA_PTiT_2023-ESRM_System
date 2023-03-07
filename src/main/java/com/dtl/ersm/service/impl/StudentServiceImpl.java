package com.dtl.ersm.service.impl;

import com.dtl.ersm.config.exceptions.CustomExceptionMessage;
import com.dtl.ersm.config.exceptions.ERSMCustomException;
import com.dtl.ersm.dtos.student.StudentDetailDTO;
import com.dtl.ersm.dtos.student.StudentSearchCriteria;
import com.dtl.ersm.repositories.StudentRepository;
import com.dtl.ersm.repositories.SubjectClassRepository;
import com.dtl.ersm.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
  private final StudentRepository studentRepository;
  private final SubjectClassRepository subjectClassRepository;

  @Override
  public Page<StudentDetailDTO> listStudentsByCriteria(
      String classCode, StudentSearchCriteria criteria, Pageable pageable)
      throws ERSMCustomException {
    if (!subjectClassRepository.existsByCode(classCode))
      throw new ERSMCustomException(
          String.format(CustomExceptionMessage.SUBJECT_CLASS_NOT_EXIST, classCode));
    if (!StringUtils.hasText(criteria.getFullName()))
      criteria.setFullName(criteria.getFullName().toLowerCase());
    Page<StudentDetailDTO> page =
        studentRepository.getStudentsByCriterias(classCode, criteria, pageable);
    return new PageImpl<>(page.getContent(), page.getPageable(), page.getTotalElements());
  }
}
