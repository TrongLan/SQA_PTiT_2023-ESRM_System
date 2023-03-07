package com.dtl.ersm.dtos.student;

import com.dtl.ersm.domains.Student;
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

  public StudentDetailDTO(Student student){
    this.code = student.getCode();
    this.firstName = student.getFirstName();
    this.lastName =student.getLastName();
    this.gender = student.getGender();
    this.birthDate = student.getBirthDate();
    this.status = student.getStatus();
  }
}
