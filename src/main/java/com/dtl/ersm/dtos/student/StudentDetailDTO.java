package com.dtl.ersm.dtos.student;

import com.dtl.ersm.config.constants.StudentStatus;
import com.dtl.ersm.domains.Student;
import com.dtl.ersm.domains.StudentClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDetailDTO {
  private String code;
  private String firstName;
  private String lastName;
  private String gender;
  private LocalDate birthDate;
  private Integer status;
  private String statusDescription;

  public StudentDetailDTO(Student student, StudentClass studentClass) {
    this.code = student.getCode();
    this.firstName = student.getFirstName();
    this.lastName = student.getLastName();
    this.gender = student.getGender();
    this.birthDate = student.getBirthDate();
    this.status = studentClass.getStatus();
    this.statusDescription = StudentStatus.getStudentStatusMap().get(studentClass.getStatus());
  }
}
