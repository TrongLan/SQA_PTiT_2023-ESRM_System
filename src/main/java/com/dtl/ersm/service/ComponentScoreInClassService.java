package com.dtl.ersm.service;

import com.dtl.ersm.config.exceptions.ERSMCustomException;
import com.dtl.ersm.dtos.componentscore.ComponentScoreDetail;

import java.util.List;

public interface ComponentScoreInClassService {
  List<ComponentScoreDetail> getComponentScoresOfClass(String classCode) throws ERSMCustomException;
}
