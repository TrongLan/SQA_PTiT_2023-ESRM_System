package com.dtl.ersm.repositories;

import com.dtl.ersm.domains.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
  Optional<Lecture> findLectureByCode(String lectureCode);
}
