package com.dtl.ersm.repositories;

import com.dtl.ersm.domains.StudentClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface StudentClassRepository extends JpaRepository<StudentClass, Long> {
  List<StudentClass> findAllBySubjectClassCodeIn(Set<String> classCodeSet);
}
