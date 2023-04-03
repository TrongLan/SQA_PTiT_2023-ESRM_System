package com.dtl.ersm.repositories;

import com.dtl.ersm.domains.StudentComponentScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentComponentScoreRepository
    extends JpaRepository<StudentComponentScore, Long> {
  @Query(
      value =
          "select STUDENT_CODE, GROUP_CONCAT(CLASS_COMPONENT_SCORE_ID, '-', SCORE) as SCORES"
              + " from student_component_score a, component_score_in_class b"
              + " where a.CLASS_COMPONENT_SCORE_ID = b.ID"
              + " and b.SBJ_CLASS_CODE = :classCode"
              + " group by STUDENT_CODE",
      nativeQuery = true)
  List<Object[]> getStudentComponentScores(@Param("classCode") String classCode);

  Optional<StudentComponentScore> findFirstByStudentCodeAndClassComponentScoreId(
      String studentCode, Long id);

  List<StudentComponentScore> findAllByStudentCodeAndClassCode(
      String studentCode, String classCode);
}
