package com.dtl.ersm.service.impl;

import com.dtl.ersm.config.exceptions.CustomExceptionMessage;
import com.dtl.ersm.config.exceptions.ERSMCustomException;
import com.dtl.ersm.domains.Lecture;
import com.dtl.ersm.domains.StudentClass;
import com.dtl.ersm.dtos.subjectclass.ClassDTO;
import com.dtl.ersm.dtos.subjectclass.StudentClassFilterCriteria;
import com.dtl.ersm.repositories.LectureRepository;
import com.dtl.ersm.repositories.StudentClassRepository;
import com.dtl.ersm.repositories.SubjectClassRepository;
import com.dtl.ersm.service.SubjectClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubjectClassServiceImpl implements SubjectClassService {
  private final SubjectClassRepository subjectClassRepository;
  private final LectureRepository lectureRepository;
  private final StudentClassRepository studentClassRepository;

  @Override
  public List<ClassDTO> getClassesOfLecture(
      String lectureCode, String subjectCode, String subjectName) throws ERSMCustomException {
    Optional<Lecture> lectureOptional = lectureRepository.findLectureByCode(lectureCode);
    if (lectureOptional.isEmpty())
      throw new ERSMCustomException(
          String.format(CustomExceptionMessage.LECTURE_CODE_NOT_EXIST, lectureCode));
    List<ClassDTO> classesByLectureCode =
        subjectClassRepository.getClassesByLectureCode(
            lectureCode, new StudentClassFilterCriteria(subjectCode, subjectName));
    Set<String> classCodeSet =
        new HashSet<>(classesByLectureCode.stream().map(ClassDTO::getCode).toList());
    Map<String, List<StudentClass>> map =
        studentClassRepository.findAllBySubjectClassCodeIn(classCodeSet).stream()
            .collect(Collectors.groupingBy(StudentClass::getSubjectClassCode));
    classesByLectureCode.forEach(
        classDTO -> {
          int size;
          if (map.get(classDTO.getCode()) != null) size = map.get(classDTO.getCode()).size();
          else size = 0;
          classDTO.setStudentNumber(size);
        });
    return classesByLectureCode;
  }
}
