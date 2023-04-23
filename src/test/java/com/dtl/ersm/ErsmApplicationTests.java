package com.dtl.ersm;

import com.dtl.ersm.domains.StudentComponentScore;
import com.dtl.ersm.dtos.student.StudentScoreDTO;
import com.dtl.ersm.service.StudentScoreService;
import com.dtl.ersm.service.impl.StudentScoreServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import static org.junit.Assert.assertThat;

@SpringBootTest
@Slf4j
class ErsmApplicationTests {

//  @MockBean private StudentScoreServiceImpl studentScoreService;
//
//  @Test
//  void testUpdateStudentScore() {
//    StudentScoreDTO dto = new StudentScoreDTO();
//    Map<Long, Float> scoreMap = new HashMap<>();
//    scoreMap.put(38L, 8f);
//    scoreMap.put(39L, 6.5f);
////    scoreMap.put(34L, 4f);
//    dto.setStudentCode("STUDENT_00051");
//    dto.setClassCode("SJ_10_01");
//    dto.setScoreMap(scoreMap);
//    try {
//      List<StudentComponentScore> saved = studentScoreService.scoreUpdate(dto);
//      Map<Long, Float> savedScoreMap =
//          saved.stream()
//              .collect(
//                  Collectors.toMap(
//                      StudentComponentScore::getClassComponentScoreId,
//                      StudentComponentScore::getScore));
//      Assertions.assertEquals(savedScoreMap, dto.getScoreMap(), "Ok");
//    } catch (Exception e) {
//      log.error(e.getMessage());
//    }
//  }

  @Test
  void contextLoads() {}
}
