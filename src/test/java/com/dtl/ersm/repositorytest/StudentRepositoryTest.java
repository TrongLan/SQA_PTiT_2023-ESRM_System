package com.dtl.ersm.repositorytest;

import com.dtl.ersm.domains.Student;
import com.dtl.ersm.domains.StudentClass;
import com.dtl.ersm.domains.SubjectClass;
import com.dtl.ersm.dtos.student.StudentDetailDTO;
import com.dtl.ersm.dtos.student.StudentSearchCriteria;
import com.dtl.ersm.repositories.StudentClassRepository;
import com.dtl.ersm.repositories.StudentRepository;
import com.dtl.ersm.repositories.SubjectClassRepository;
import java.time.LocalDate;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(
    properties = {
      "spring.datasource.url=jdbc:mysql://db4free.net :3306/esrm_system",
      "spring.datasource.username=esrm_system",
      "spring.datasource.password=esrm@123"
    })
public class StudentRepositoryTest {
  @Autowired private StudentRepository studentRepository;
  @Autowired private StudentClassRepository studentClassRepository;
  @Autowired private SubjectClassRepository subjectClassRepository;

  @Test
  public void findStudentsByCriteriasTesting() {
    // Tạo list student
    Student s1 =
        Student.builder()
            .code("S00001")
            .firstName("Trong Lan")
            .lastName("Dinh")
            .birthDate(LocalDate.of(2001, 12, 8))
            .gender("Nam")
            .build();
    Student s2 =
        Student.builder()
            .code("S00002")
            .firstName("Ngoc Lam")
            .lastName("Dinh")
            .birthDate(LocalDate.of(2001, 10, 28))
            .gender("Nam")
            .build();
    Student s3 =
        Student.builder()
            .code("S00003")
            .firstName("Trung Hieu")
            .lastName("Dinh")
            .birthDate(LocalDate.of(2001, 12, 26))
            .gender("Nam")
            .build();
    Student s4 =
        Student.builder()
            .code("S00004")
            .firstName("Tien Phat")
            .lastName("Dinh")
            .birthDate(LocalDate.of(2001, 3, 26))
            .gender("Nam")
            .build();
    Student s5 =
        Student.builder()
            .code("S00005")
            .firstName("Thuy Hang")
            .lastName("Vu")
            .birthDate(LocalDate.of(2001, 12, 1))
            .gender("Nam")
            .build();
    studentRepository.saveAll(List.of(s1, s2, s3, s4, s5));

    // Tạo lớp học
    SubjectClass subjectClass =
        SubjectClass.builder()
            .subjectCode("SJ001")
            .code("SJC01")
            .name("01")
            .lectureCode("L001")
            .build();
    subjectClassRepository.save(subjectClass);

    // Gán list student cho class
    StudentClass studentClass1 =
        StudentClass.builder().studentCode("S00001").subjectClassCode("SJC01").status(0).build();
    StudentClass studentClass2 =
        StudentClass.builder().studentCode("S00002").subjectClassCode("SJC01").status(1).build();
    StudentClass studentClass3 =
        StudentClass.builder().studentCode("S00003").subjectClassCode("SJC01").status(1).build();
    StudentClass studentClass4 =
        StudentClass.builder().studentCode("S00004").subjectClassCode("SJC01").status(1).build();
    StudentClass studentClass5 =
        StudentClass.builder().studentCode("S00005").subjectClassCode("SJC01").status(2).build();
    studentClassRepository.saveAll(
        List.of(studentClass1, studentClass2, studentClass3, studentClass4, studentClass5));

    // Tiêu chí tìm kiếm
    StudentSearchCriteria criteria = new StudentSearchCriteria(null, null, 1);
    Page<StudentDetailDTO> studentsByCriteria = studentRepository.getStudentsByCriterias("SJC01", criteria, null);

    Assertions.assertThat(studentsByCriteria.getTotalElements()).isEqualTo(3L);
  }
}
