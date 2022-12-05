package tttt;

import static org.junit.jupiter.api.Assertions.assertEquals;

import common.Util;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class Day5 {

  public static final String SAMPLE_FILE_NAME = "/2022/day5_sample.txt";
  public static final String INPUT_FILE_NAME = "/2022/day5_input.txt";

  // --- Day 5: Supply Stacks ---

  private String prepareElvesMessage(List<String> lines, boolean multi) {
    Crates crates = Crates.initCrates(lines);
    lines.stream().filter(line -> line.startsWith("move"))
        .forEach(line -> {
          if (multi) {
            crates.moveMulti(line);
          } else {
            crates.move(line);
          }
        });
    String result = crates.getResult();
    System.out.println(result);
    return result;
  }

  static class Crates {

    List<Stack<String>> crates = new ArrayList<>();


    public static Crates initCrates(List<String> lines) {
      Crates crates = new Crates();
      int N = 0;
      int i = 0;
      List<String> stackLines = new ArrayList<>();
      for (i = 0; i < lines.size(); i++) {
        if (lines.get(i).contains("1")) {
          String ids[] = lines.get(i).split(" ");
          N = Integer.parseInt(ids[ids.length - 1].trim());
          break;
        }
        stackLines.add(lines.get(i));
      }
      crates.initStack(N);
      for (int j = stackLines.size() - 1; j >= 0; j--) {
        String[] items = stackLines.get(j).split("");
        crates.init(N, items);
      }

      return crates;
    }

    public void init(int N, String[] items) {
      int L = 1;
      int P = 1;
      int stack = 0;
      for (int i = 0; i < items.length && stack < N; i++) {
        L = i == 0 ? 1 : P + 4;
        if (!items[L].trim().isEmpty()) {
          this.crates.get(stack).push(items[L]);
        }
        P = L;
        stack++;
      }
    }

    private void initStack(int N) {
      if (this.crates.isEmpty()) {
        for (int i = 0; i < N; i++) {
          Stack stack = new Stack<>();
          crates.add(stack);
        }
      }
    }

    void move(String line) {
      String[] command = line.split(" ");
      int c = Integer.parseInt(command[1]);
      int source = Integer.parseInt(command[3]);
      int target = Integer.parseInt(command[5]);
      for (int i = 0; i < c; i++) {
        Stack<String> sourceCrate = this.crates.get(source - 1);
        Stack<String> targetCrate = this.crates.get(target - 1);
        String top = sourceCrate.pop();
        targetCrate.push(top);
      }
    }

    void moveMulti(String line) {
      String[] command = line.split(" ");
      int c = Integer.parseInt(command[1]);
      int source = Integer.parseInt(command[3]);
      int target = Integer.parseInt(command[5]);
      Stack<String> sourceCrate = this.crates.get(source - 1);
      Stack<String> targetCrate = this.crates.get(target - 1);
      if (c == 1) {
        String top = sourceCrate.pop();
        targetCrate.push(top);
      } else {
        System.out.println(sourceCrate);
        List<String> list = new ArrayList();
        for (int j = 0; j < c; j++) {
          list.add(sourceCrate.pop());
        }
        System.out.println(list);
        for (int i = list.size() - 1; i >= 0; i--) {
          targetCrate.push(list.get(i));
        }
        System.out.println(targetCrate);
      }
    }

    String getResult() {
      return this.crates.stream().map(s -> s.peek()).collect(Collectors.joining());
    }
  }

  @Test
  void test_sample() {
    List<String> lines = Util.getAllLines(SAMPLE_FILE_NAME);
    String message = prepareElvesMessage(lines, false);
    assertEquals("CMZ", message);
    String message1 = prepareElvesMessage(lines, true);
    assertEquals("MCD", message1);
  }

  @Test
  void test_input() {
    List<String> lines = Util.getAllLines(INPUT_FILE_NAME);
    String message = prepareElvesMessage(lines, false);
    assertEquals("RNZLFZSJH", message);
    String message1 = prepareElvesMessage(lines, true);
    assertEquals("CNSFCGJSM", message1);

  }
}
/**
 * --- Day 5: Supply Stacks ---
 * The expedition can depart as soon as the final supplies have been unloaded from the ships. Supplies are stored in stacks of marked crates, but because the needed supplies are buried under many other crates, the crates need to be rearranged.
 *
 * The ship has a giant cargo crane capable of moving crates between stacks. To ensure none of the crates get crushed or fall over, the crane operator will rearrange them in a series of carefully-planned steps. After the crates are rearranged, the desired crates will be at the top of each stack.
 *
 * The Elves don't want to interrupt the crane operator during this delicate procedure, but they forgot to ask her which crate will end up where, and they want to be ready to unload them as soon as possible so they can embark.
 *
 * They do, however, have a drawing of the starting stacks of crates and the rearrangement procedure (your puzzle input). For example:
 *
 *     [D]
 * [N] [C]
 * [Z] [M] [P]
 *  1   2   3
 *
 * move 1 from 2 to 1
 * move 3 from 1 to 3
 * move 2 from 2 to 1
 * move 1 from 1 to 2
 * In this example, there are three stacks of crates. Stack 1 contains two crates: crate Z is on the bottom, and crate N is on top. Stack 2 contains three crates; from bottom to top, they are crates M, C, and D. Finally, stack 3 contains a single crate, P.
 *
 * Then, the rearrangement procedure is given. In each step of the procedure, a quantity of crates is moved from one stack to a different stack. In the first step of the above rearrangement procedure, one crate is moved from stack 2 to stack 1, resulting in this configuration:
 *
 * [D]
 * [N] [C]
 * [Z] [M] [P]
 *  1   2   3
 * In the second step, three crates are moved from stack 1 to stack 3. Crates are moved one at a time, so the first crate to be moved (D) ends up below the second and third crates:
 *
 *         [Z]
 *         [N]
 *     [C] [D]
 *     [M] [P]
 *  1   2   3
 * Then, both crates are moved from stack 2 to stack 1. Again, because crates are moved one at a time, crate C ends up below crate M:
 *
 *         [Z]
 *         [N]
 * [M]     [D]
 * [C]     [P]
 *  1   2   3
 * Finally, one crate is moved from stack 1 to stack 2:
 *
 *         [Z]
 *         [N]
 *         [D]
 * [C] [M] [P]
 *  1   2   3
 * The Elves just need to know which crate will end up on top of each stack; in this example, the top crates are C in stack 1, M in stack 2, and Z in stack 3, so you should combine these together and give the Elves the message CMZ.
 *
 * After the rearrangement procedure completes, what crate ends up on top of each stack?
 *
 * Your puzzle answer was RNZLFZSJH.
 *
 * --- Part Two ---
 * As you watch the crane operator expertly rearrange the crates, you notice the process isn't following your prediction.
 *
 * Some mud was covering the writing on the side of the crane, and you quickly wipe it away. The crane isn't a CrateMover 9000 - it's a CrateMover 9001.
 *
 * The CrateMover 9001 is notable for many new and exciting features: air conditioning, leather seats, an extra cup holder, and the ability to pick up and move multiple crates at once.
 *
 * Again considering the example above, the crates begin in the same configuration:
 *
 *     [D]
 * [N] [C]
 * [Z] [M] [P]
 *  1   2   3
 * Moving a single crate from stack 2 to stack 1 behaves the same as before:
 *
 * [D]
 * [N] [C]
 * [Z] [M] [P]
 *  1   2   3
 * However, the action of moving three crates from stack 1 to stack 3 means that those three moved crates stay in the same order, resulting in this new configuration:
 *
 *         [D]
 *         [N]
 *     [C] [Z]
 *     [M] [P]
 *  1   2   3
 * Next, as both crates are moved from stack 2 to stack 1, they retain their order as well:
 *
 *         [D]
 *         [N]
 * [C]     [Z]
 * [M]     [P]
 *  1   2   3
 * Finally, a single crate is still moved from stack 1 to stack 2, but now it's crate C that gets moved:
 *
 *         [D]
 *         [N]
 *         [Z]
 * [M] [C] [P]
 *  1   2   3
 * In this example, the CrateMover 9001 has put the crates in a totally different order: MCD.
 *
 * Before the rearrangement process finishes, update your simulation so that the Elves know where they should stand to be ready to unload the final supplies. After the rearrangement procedure completes, what crate ends up on top of each stack?
 *
 * Your puzzle answer was CNSFCGJSM..
 */
