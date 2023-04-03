package com.dtl.ersm.controllers;

import com.dtl.ersm.config.exceptions.ERSMCustomException;
import com.dtl.ersm.service.ComponentScoreInClassService;
import com.dtl.ersm.service.StudentScoreService;
import com.dtl.ersm.service.SubjectClassService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subject-class")
@Slf4j
@RequiredArgsConstructor
public class SubjectClassController {
  private final SubjectClassService subjectClassService;
  private final ComponentScoreInClassService componentScoreInClassService;
  private final StudentScoreService studentScoreService;

  /**
   * @apiNote Tìm kiếm môn học theo mã giảng viên, mã môn học, tên môn học
   * @param lectureCode Mã giảng viên
   * @param subjectCode Tìm kiếm theo mã môn học (Không bắt buộc)
   * @param subjectName Tìm kiếm theo tên môn học (Không bắt buộc)
   * @return danh sách tìm kiếm môn học theo mã giảng viên
   * @author Trong Lan
   * @since 2023-03-06
   */
  @GetMapping(value = "/{lectureCode}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> getClassListOfLecture(
      @PathVariable String lectureCode,
      @RequestParam(required = false) String subjectCode,
      @RequestParam(required = false) String subjectName) {
    try {
      return new ResponseEntity<>(
          subjectClassService.getClassesOfLecture(lectureCode, subjectCode, subjectName),
          HttpStatus.OK);
    } catch (ERSMCustomException e) {
      return new ResponseEntity<>(e.getCustomErrorMessage(), HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      log.error(e.getMessage());
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping(value = "/component-score/{classCode}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> getComponentScoresOfClass(@PathVariable String classCode) {
    try {
      return new ResponseEntity<>(
          componentScoreInClassService.getComponentScoresOfClass(classCode), HttpStatus.OK);
    } catch (ERSMCustomException e) {
      return new ResponseEntity<>(e.getCustomErrorMessage(), HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      log.error(e.getMessage());
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping(value = "/student-score/{classCode}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> getStudentScoresOfClass(@PathVariable String classCode) {
    try {
      return new ResponseEntity<>(
          studentScoreService.getStudentScoresInClass(classCode), HttpStatus.OK);
    } catch (ERSMCustomException e) {
      return new ResponseEntity<>(e.getCustomErrorMessage(), HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      log.error(e.getMessage());
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
