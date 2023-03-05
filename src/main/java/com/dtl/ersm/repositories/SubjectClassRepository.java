package com.dtl.ersm.repositories;

import com.dtl.ersm.domains.SubjectClass;
import com.dtl.ersm.dtos.subjectclass.ClassDTO;
import com.dtl.ersm.dtos.subjectclass.StudentClassFilterCriteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubjectClassRepository extends JpaRepository<SubjectClass, Long> {
  @Query(
      value =
          "select new com.dtl.ersm.dtos.subjectclass.ClassDTO(c, s, l) "
              + "from SubjectClass c "
              + "join Subject s on s.code = c.subjectCode "
              + "join Lecture l on l.code = c.lectureCode "
              + "where l.code = :lectureCode "
              + "and (:#{#criteria.subjectCode} is null or c.subjectCode = :#{#criteria.subjectCode}) "
              + "and (:#{#criteria.subjectName} is null or s.name like %:#{#criteria.subjectName}%) "
              + "order by c.code")
  List<ClassDTO> getClassesByLectureCode(
      String lectureCode, @Param("criteria") StudentClassFilterCriteria criteria);
}
