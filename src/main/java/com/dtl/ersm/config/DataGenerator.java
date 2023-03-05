package com.dtl.ersm.config;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Slf4j
public class DataGenerator {
  public static void main(String[] args) {
    Random random = new Random();
    List<String> firstName =
        Arrays.asList(
            "An", "Chi", "Hoa", "Nhung", "Nam", "Lan", "Phong", "Chung", "Trung", "Dương", "Duy",
            "Tuấn", "Hồng", "Diệu", "Huy", "Huệ");
    List<String> middleName =
        Arrays.asList("An", "Việt", "Nguyên", "Khắc", "Ngọc", "Đình", "Trọng", "Văn", "Thị");
    List<String> lastName =
        Arrays.asList("Nguyễn", "Trần", "Đinh", "Lương", "Tạ", "Bùi", "Hoàng", "Đào");
    List<String> gender = Arrays.asList("Nam", "Nữ", "Khác");

    //    log.info(valuesForInsertingLecture(20, firstName, middleName, lastName, random));
    log.info(valuesForInsertingStudent(1000, firstName, middleName, lastName, gender, random));
  }

  public static String valuesForInsertingLecture(
      int numberOfRecords,
      List<String> firstName,
      List<String> middleName,
      List<String> lastName,
      Random random) {
    StringBuilder s = new StringBuilder();
    for (int i = 0; i < numberOfRecords; i++) {
      s.append(
          String.format(
              "(\"%s\", \"%s\", \"%s\")",
              String.format("LECTURE_%02d", i + 1),
              String.format("%s", firstName.get(random.nextInt(firstName.size()))),
              String.format(
                  "%s %s",
                  lastName.get(random.nextInt(lastName.size())),
                  middleName.get(random.nextInt(middleName.size())))));
      if (i < numberOfRecords - 1) s.append(", ");
    }
    return s.toString();
  }

  public static String valuesForInsertingStudent(
      int numberOfRecords,
      List<String> firstName,
      List<String> middleName,
      List<String> lastName,
      List<String> gender,
      Random random) {
    StringBuilder s = new StringBuilder();
    for (int i = 0; i < numberOfRecords; i++) {
      s.append(
          String.format(
              "(\"%s\", \"%s\", \"%s\", \"%s\", \"%s\")",
              String.format("STUDENT_%05d", i + 1),
              String.format("%s", firstName.get(random.nextInt(firstName.size()))),
              String.format(
                  "%s %s",
                  lastName.get(random.nextInt(lastName.size())),
                  middleName.get(random.nextInt(middleName.size()))),
              String.format("2001-%02d-%02d", random.nextInt(1, 12), random.nextInt(1, 28)),
              String.format("%s", gender.get(random.nextInt(gender.size())))));
      if (i < numberOfRecords - 1) s.append(", ");
    }
    return s.toString();
  }
}
