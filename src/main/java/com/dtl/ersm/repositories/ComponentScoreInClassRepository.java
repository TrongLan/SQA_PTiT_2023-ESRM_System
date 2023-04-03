package com.dtl.ersm.repositories;

import com.dtl.ersm.domains.ComponentScoreInClass;
import com.dtl.ersm.dtos.componentscore.ComponentScoreDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ComponentScoreInClassRepository
    extends JpaRepository<ComponentScoreInClass, Long> {

  @Query(
      value =
          "select new com.dtl.ersm.dtos.componentscore.ComponentScoreDetail(inClass, type) "
              + "from ComponentScoreInClass inClass "
              + "join ComponentScoreType type on inClass.componentScoreTypeCode = type.code "
              + "where inClass.subjectClassCode = :classCode "
              + "order by type.id asc ")
  List<ComponentScoreDetail> getComponentScoresOfClass(String classCode);
}
