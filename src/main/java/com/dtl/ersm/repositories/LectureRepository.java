package com.dtl.ersm.repositories;

import com.dtl.ersm.domains.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
}
