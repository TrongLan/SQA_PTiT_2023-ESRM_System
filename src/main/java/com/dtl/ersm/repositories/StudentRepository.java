package com.dtl.ersm.repositories;

import com.dtl.ersm.domains.Student;
import com.dtl.ersm.dtos.student.StudentDetailDTO;
import com.dtl.ersm.dtos.student.StudentSearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
  @Query(
      value =
          "select new com.dtl.ersm.dtos.student.StudentDetailDTO(s, sc) "
              + "from Student s "
              + "join StudentClass sc on s.code = sc.studentCode "
              + "join SubjectClass sjc on sc.subjectClassCode = sjc.code "
              + "where sc.subjectClassCode = :classCode "
              + "and (:#{#criteria.code} is null or s.code = :#{#criteria.code}) "
              + "and (:#{#criteria.fullName} is null or lower(concat(s.lastName, ' ', s.firstName)) like %:#{#criteria.fullName}%) "
              + "and (:#{#criteria.status} is null or sc.status = :#{#criteria.status}) "
              + "order by s.firstName asc, s.lastName asc, s.code asc ")
  Page<StudentDetailDTO> getStudentsByCriterias(
      String classCode, @Param("criteria") StudentSearchCriteria criteria, Pageable pageable);

  Optional<Student> findFirstByCode(String code);
}
