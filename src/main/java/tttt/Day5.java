package tttt;

import static org.junit.jupiter.api.Assertions.assertEquals;

import common.Util;
import java.util.List;
import org.junit.jupiter.api.Test;

public class Day5 {

  public static final String SAMPLE_FILE_NAME = "/2022/day5_sample.txt";
  public static final String INPUT_FILE_NAME = "/2022/day5_input.txt";

  // --- Day 5 ---

  private long findFullOverlapPart1(List<String> lines) {
    return 0L;
  }

  private long findFullOverlapPart2(List<String> lines) {
    return 0L;
  }

  @Test
  void test_findTotalScore_sample() {
    List<String> lines = Util.getAllLines(SAMPLE_FILE_NAME);
    long count = findFullOverlapPart1(lines);
    System.out.println(count);
    assertEquals(2, count);

    long countPart2 = findFullOverlapPart2(lines);
    System.out.println(countPart2);
    assertEquals(4, countPart2);
  }

  @Test
  void test_findTotalScore_input() {
    List<String> lines = Util.getAllLines(INPUT_FILE_NAME);
    long count = findFullOverlapPart1(lines);
    System.out.println(count);
    assertEquals(588, count);

    long countPart2 = findFullOverlapPart2(lines);
    System.out.println(countPart2);
    assertEquals(911, countPart2);

  }
}
/**
 *
 */
