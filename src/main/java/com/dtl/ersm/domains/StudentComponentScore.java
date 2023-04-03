package com.dtl.ersm.domains;

import com.dtl.ersm.dtos.student.StudentScorePK;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "STUDENT_COMPONENT_SCORE")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class StudentComponentScore {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "CLASS_COMPONENT_SCORE_ID")
  private Long classComponentScoreId;

  @Column(name = "STUDENT_CODE")
  private String studentCode;

  @Column(name = "CLASS_CODE")
  private String classCode;

  @Column(name = "SCORE")
  private Float score;

  public StudentComponentScore(StudentScorePK pk) {
    this.classComponentScoreId = pk.componentScoreId();
    this.studentCode = pk.studentCode();
    this.classCode = pk.classCode();
  }
}
