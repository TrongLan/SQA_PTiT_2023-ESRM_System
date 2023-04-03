package com.dtl.ersm.service.impl;

import com.dtl.ersm.config.constants.StudentStatus;
import com.dtl.ersm.config.exceptions.CustomExceptionMessage;
import com.dtl.ersm.config.exceptions.ERSMCustomException;
import com.dtl.ersm.domains.StudentClass;
import com.dtl.ersm.domains.StudentComponentScore;
import com.dtl.ersm.dtos.componentscore.ComponentScoreDetail;
import com.dtl.ersm.dtos.student.StudentScoreDTO;
import com.dtl.ersm.dtos.student.StudentScorePK;
import com.dtl.ersm.repositories.*;
import com.dtl.ersm.service.StudentScoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentScoreServiceImpl implements StudentScoreService {
  private final StudentComponentScoreRepository studentComponentScoreRepository;
  private final SubjectClassRepository subjectClassRepository;
  private final StudentRepository studentRepository;
  private final ComponentScoreInClassRepository componentScoreInClassRepository;
  private final StudentClassRepository studentClassRepository;

  @Override
  public Map<String, Map<Long, Double>> getStudentScoresInClass(String classCode)
      throws ERSMCustomException {
    // validate classCode
    if (!subjectClassRepository.existsByCode(classCode))
      throw new ERSMCustomException(
          String.format(CustomExceptionMessage.SUBJECT_CLASS_NOT_EXIST, classCode));
    List<Object[]> queryResult =
        studentComponentScoreRepository.getStudentComponentScores(classCode);
    Map<String, Map<Long, Double>> studentScoreMap = new HashMap<>();
    for (Object[] data : queryResult) {
      HashMap<Long, Double> componentScoreMap = new HashMap<>();
      List<String> temp = List.of(String.valueOf(data[1]).split(","));
      for (String t : temp) {
        String[] tmp = t.split("-");
        componentScoreMap.put(Long.valueOf(tmp[0]), Double.valueOf(tmp[1]));
      }
      studentScoreMap.put((String) data[0], componentScoreMap);
    }
    return studentScoreMap;
  }

  @Override
  @Transactional
  public void scoreUpdate(StudentScoreDTO dto) throws ERSMCustomException {
    if (studentRepository.findFirstByCode(dto.getStudentCode()).isEmpty()) {
      throw new ERSMCustomException(
          String.format(CustomExceptionMessage.STUDENT_CODE_NOT_EXIST, dto.getStudentCode()));
    }
    if (dto.getScoreMap().values().stream().anyMatch(s -> s < 0 || s > 10)) {
      throw new ERSMCustomException(CustomExceptionMessage.STUDENT_SCORE_INVALID);
    }
    if (!subjectClassRepository.existsByCode(dto.getClassCode())) {
      throw new ERSMCustomException(
          String.format(CustomExceptionMessage.SUBJECT_CLASS_NOT_EXIST, dto.getClassCode()));
    }
    List<Long> componentScoreIdsOfClass =
        componentScoreInClassRepository.getComponentScoresOfClass(dto.getClassCode()).stream()
            .map(ComponentScoreDetail::getId)
            .toList();
    ArrayList<StudentComponentScore> domains = new ArrayList<>();
    for (Long componentScoreId : dto.getScoreMap().keySet()) {
      if (!componentScoreIdsOfClass.contains(componentScoreId)) {
        throw new ERSMCustomException("");
      }
      Optional<StudentComponentScore> optional =
          studentComponentScoreRepository.findFirstByStudentCodeAndClassComponentScoreId(
              dto.getStudentCode(), componentScoreId);
      StudentComponentScore domain =
          optional.isEmpty()
              ? new StudentComponentScore(
                  new StudentScorePK(dto.getStudentCode(), dto.getClassCode(), componentScoreId))
              : optional.get();
      domain.setScore(dto.getScoreMap().get(componentScoreId));
      domains.add(domain);
    }
    studentComponentScoreRepository.saveAll(domains);

    // cập nhật trạng thái sinh viên trong lớp
    Optional<StudentClass> studentClassOptional =
        studentClassRepository.findFirstByStudentCodeAndSubjectClassCode(
            dto.getStudentCode(), dto.getClassCode());
    if (studentClassOptional.isEmpty()) {
      throw new ERSMCustomException("");
    }
    StudentClass studentClass = studentClassOptional.get();
    List<StudentComponentScore> studentScoresInClass =
        studentComponentScoreRepository.findAllByStudentCodeAndClassCode(
            dto.getStudentCode(), dto.getClassCode());
    boolean hasNoZero =
        studentScoresInClass.stream()
            .filter(studentComponentScore -> studentComponentScore.getScore().equals(0f))
            .toList()
            .isEmpty();

    if (!hasNoZero) {
      studentClass.setStatus(StudentStatus.INELIGIBLE.getStatusCode());
    } else {
      if (studentScoresInClass.size() == componentScoreIdsOfClass.size()) {
        studentClass.setStatus(StudentStatus.ELIGIBLE.getStatusCode());
      } else {
        studentClass.setStatus(StudentStatus.UNDEFINED.getStatusCode());
      }
    }
    studentClassRepository.save(studentClass);
  }
}
