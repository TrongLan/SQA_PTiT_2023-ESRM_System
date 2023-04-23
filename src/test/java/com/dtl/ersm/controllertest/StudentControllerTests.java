package com.dtl.ersm.controllertest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.dtl.ersm.controllers.StudentController;
import com.dtl.ersm.dtos.student.StudentScoreDTO;
import com.dtl.ersm.service.impl.StudentScoreServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(StudentController.class)
public class StudentControllerTests {
  private static final String API_PATH = "/student/score";
  @Autowired private MockMvc mockMvc;
  @Autowired private ObjectMapper mapper;
  @MockBean private StudentScoreServiceImpl studentScoreServiceImpl;

  @Test
  public void updateStudentScoreShouldReturn400() throws Exception {
    StudentScoreDTO studentScoreDTO = new StudentScoreDTO("", "", null);
    String requestBody = mapper.writeValueAsString(studentScoreDTO);
    mockMvc
        .perform(put(API_PATH).contentType("application/json").content(requestBody))
        .andExpect(status().isBadRequest());
  }
}
