package com.dtl.ersm.repositorytest;

import com.dtl.ersm.domains.Lecture;
import com.dtl.ersm.repositories.LectureRepository;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(
    properties = {
      "spring.datasource.url=jdbc:mysql://db4free.net :3306/esrm_system",
      "spring.datasource.username=esrm_system",
      "spring.datasource.password=esrm@123"
    })
public class LectureRepositoryTest {
  @Autowired private LectureRepository lectureRepository;

  @Test
  public void findLectureByCodeTesting() {
    Lecture lecture =
        Lecture.builder().code("L001").firstName("Trong Lan").lastName("Dinh").build();
    lectureRepository.save(lecture);

    Optional<Lecture> lectureByCodeOptional = lectureRepository.findLectureByCode("L001");
    Assertions.assertThat(lectureByCodeOptional.isPresent()).isTrue();
  }
}
