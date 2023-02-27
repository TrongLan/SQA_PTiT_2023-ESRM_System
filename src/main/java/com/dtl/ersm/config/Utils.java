package com.dtl.ersm.config;

import java.util.Map;

public class Utils {
  private Utils() {
    throw new IllegalStateException("Utility class");
  }

  /**
   * @param scoreMap Map chứa các giá trị điểm thành phần kèm trọng số tương ứng
   * @return giá trị điểm trung bình cuối cùng
   * @author Trong Lan
   * @since 2023-02-27
   */
  public static Float calculateFinalScore(Map<Integer, Float> scoreMap) {
    float result = 0;
    for (Map.Entry<Integer, Float> entry : scoreMap.entrySet()) {
      Integer weight = entry.getKey();
      Float score = entry.getValue();
      float x = (weight / 100f) * score;
      result = result + x;
    }
    return result;
  }

  /**
   * @param scoreMap Map chứa các giá trị điểm thành phần kèm trọng số tương ứng
   * @param threshold Ngưỡng điểm liệt
   * @return true - nếu có ít nhất một điểm thành phần nhỏ hơn hoặc bằng ngưỡng, false - ngược
   *     lại
   * @author Trong Lan
   * @since 2023-02-27
   */
  public static boolean isIneligible(Map<Integer, Float> scoreMap, float threshold) {
    return scoreMap.values().stream().anyMatch(s -> s <= threshold);
  }
}
