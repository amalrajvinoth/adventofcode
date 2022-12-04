package common;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Util {

  public static List<String> getAllLines(String fileName) {
    InputStream is = Util.class.getResourceAsStream(fileName);
    Scanner scanner = new Scanner(is);
    List<String> input = new ArrayList<>();
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      input.add(line);
    }
    return input;
  }

  public static List<List<Character>> stringToChars(List<String> lines) {
    return lines.stream().map(line -> line.chars()
            .mapToObj(e -> (char) e) // Stream<Character>
            .collect(Collectors.toList()))
        .collect(Collectors.toList());
  }
}
