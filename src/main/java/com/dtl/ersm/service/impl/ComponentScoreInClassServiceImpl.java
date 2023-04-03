package com.dtl.ersm.service.impl;

import com.dtl.ersm.config.exceptions.CustomExceptionMessage;
import com.dtl.ersm.config.exceptions.ERSMCustomException;
import com.dtl.ersm.dtos.componentscore.ComponentScoreDetail;
import com.dtl.ersm.repositories.ComponentScoreInClassRepository;
import com.dtl.ersm.repositories.SubjectClassRepository;
import com.dtl.ersm.service.ComponentScoreInClassService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ComponentScoreInClassServiceImpl implements ComponentScoreInClassService {
  private final ComponentScoreInClassRepository componentScoreInClassRepository;
  private final SubjectClassRepository subjectClassRepository;

  @Override
  public List<ComponentScoreDetail> getComponentScoresOfClass(String classCode)
      throws ERSMCustomException {
    if (!subjectClassRepository.existsByCode(classCode))
      throw new ERSMCustomException(
          String.format(CustomExceptionMessage.SUBJECT_CLASS_NOT_EXIST, classCode));
    return componentScoreInClassRepository.getComponentScoresOfClass(classCode);
  }
}
