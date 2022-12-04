package tttt;

import static org.junit.jupiter.api.Assertions.assertEquals;

import common.Util;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class Day1 {

  private int findMostCaloriesElf(List<List<Integer>> elfCaloriesMap) {
    List<Integer> sumList = elfCaloriesMap.stream()
        .map(c -> c.stream().reduce(0, Integer::sum))
        .collect(Collectors.toList());
    int ix = sumList.stream().max(Integer::compare)
        .map(sumList::indexOf)
        .orElse(-1);
    System.out.println("Elf Number : " + ++ix);
    int max = Collections.max(sumList);
    Collections.sort(sumList, Comparator.reverseOrder());
    int top2Sum = sumList.get(0) + sumList.get(1) + sumList.get(2);
    System.out.println("Max Elf Calories : " + max);
    System.out.println("top2Sum Elf Calories : " + top2Sum);
    return max;
  }

  @Test
  void test_findMostCaloriesElf_0() {

    List<String> lines = Util.getAllLines("/2022/day1_input.txt");
    List<List<Integer>> input = new ArrayList<>();
    List<Integer> calories = new ArrayList<>();
    for (String line : lines) {
      if (line == null || line.isEmpty()) {
        input.add(calories);
        calories = new ArrayList<>();
      } else {
        calories.add(Integer.parseInt(line));
      }
    }

    //System.out.println(input);
    int mostCaloriesElf = findMostCaloriesElf(input);

  }

  @Test
  void test_findMostCaloriesElf_1() {
    List<List<Integer>> elfCaloriesMap = List.of(
        List.of(1000, 2000, 3000),
        List.of(4000),
        List.of(5000, 6000),
        List.of(7000, 8000, 9000),
        List.of(10000)
    );

    assertEquals(24000, findMostCaloriesElf(elfCaloriesMap));
  }

  @Test
  void test_findMostCaloriesElf_2() {
    List<List<Integer>> elfCaloriesMap = List.of(
        List.of(1000, 2000, 3000, 100000),
        List.of(4000),
        List.of(5000, 6000),
        List.of(7000, 8000, 9000),
        List.of(10000)
    );

    assertEquals(106000, findMostCaloriesElf(elfCaloriesMap));
  }

}
