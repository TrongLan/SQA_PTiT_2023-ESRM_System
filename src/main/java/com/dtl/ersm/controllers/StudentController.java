package com.dtl.ersm.controllers;

import com.dtl.ersm.config.exceptions.ERSMCustomException;
import com.dtl.ersm.dtos.student.StudentSearchCriteria;
import com.dtl.ersm.service.StudentService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
@Slf4j
@RequiredArgsConstructor
public class StudentController {
  private final StudentService studentService;

  @GetMapping(value = "/{classCode}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> listStudent(
      @PathVariable String classCode,
      @RequestParam(required = false) String studentCode,
      @RequestParam(required = false) String fullName,
      @RequestParam(required = false) Integer status,
      @Parameter(hidden = true) Pageable pageable) {
    try {
      StudentSearchCriteria criteria = new StudentSearchCriteria(studentCode, fullName, status);
      return new ResponseEntity<>(
          studentService.listStudentsByCriteria(classCode, criteria, pageable), HttpStatus.OK);
    } catch (ERSMCustomException e) {
      return new ResponseEntity<>(e.getCustomErrorMessage(), HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      log.error(e.getMessage());
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
