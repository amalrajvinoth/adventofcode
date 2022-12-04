package tttt;

import static org.junit.jupiter.api.Assertions.assertEquals;

import common.Util;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class Day2 {

  Map<Character, Character> map = Map.of(
      'X', 'L', // LOSE
      'Y', 'D', // DRAW
      'Z', 'W'); // WIN
  Map<Character, Character> winMap = Map.of(
      'A', 'Y',
      'B', 'Z',
      'C', 'X');

  Map<Character, Character> drawMap = Map.of(
      'A', 'X',
      'B', 'Y',
      'C', 'Z');

  Map<Character, Character> loseMap = Map.of(
      'A', 'Z',
      'B', 'X',
      'C', 'Y');

  private int findTotalScore(List<List<Character>> strategyGuide) {
    int totalScore = 0;
    for (int i = 0; i < strategyGuide.size(); i++) {
      Character player = strategyGuide.get(i).get(0);
      Character you = strategyGuide.get(i).get(1);
      totalScore += play(player, you);
    }
    return totalScore;
  }

  private int findTotalScorePart2(List<List<Character>> strategyGuide) {
    int totalScore = 0;
    for (int i = 0; i < strategyGuide.size(); i++) {
      Character player = strategyGuide.get(i).get(0);
      Character you = strategyGuide.get(i).get(1);
      totalScore += palyPart2(player, you);
    }
    return totalScore;
  }

  private int play(Character opponent, Character me) {
    int opponentValue = opponent - 65 + 1;
    int meValue = me - 88 + 1;
    int score = meValue;
    String result = "";
    if (opponent == 'C' && me == 'X' || opponent == 'B' && me == 'Z'
        || opponent == 'A' && me == 'Y') {
      score += 6;
      result = "WIN";
    } else if (opponentValue == meValue) {
      score += 3;
      result = "DRAW";
    } else if (opponent == 'A' && me == 'Z' || opponent == 'C' && me == 'Y'
        || opponent == 'B' && me == 'X') {
      score += 0;
      result = "LOSE";
    }
    System.out.println("opponent=" + opponent + ", " + me + "=" + "(" + score + ") = " + result);
    return score;
  }

  private int palyPart2(Character opponent, Character me) {
    int score = 0;
    String result = "";
    if (map.get(me) == 'W') {
      result = "WIN";
      score = (winMap.get(opponent) - 88) + 1 + 6;
    } else if (map.get(me) == 'D') {
      result = "DRAW";
      score = (drawMap.get(opponent) - 88) + 1 + 3;
    } else if (map.get(me) == 'L') {
      result = "LOSE";
      score = (loseMap.get(opponent) - 88) + 1 + 0;
    }
    System.out.println("opponent=" + opponent + ", " + me + "=" + "(" + score + ") = " + result);
    return score;
  }

  @Test
  void test_findTotalScore_0() {
    List<String> lines = Util.getAllLines("/2022/day2_input.txt");
    List<List<Character>> stategyGuide = Util.stringToChars(lines);
    int totalScore = findTotalScore(stategyGuide);
    int part2TotalScore = findTotalScorePart2(stategyGuide);

    System.out.println(totalScore);
    System.out.println(part2TotalScore);
    assertEquals(14297, totalScore);
    assertEquals(10498, part2TotalScore);
  }

  @Test
  void test_findTotalScore_1() {
    List<List<Character>> strategyGuide = List.of(
        List.of('A', 'Y'),
        List.of('B', 'X'),
        List.of('C', 'Z')
    );

    int totalScore = findTotalScore(strategyGuide);
    int totalScorePart2 = findTotalScorePart2(strategyGuide);
    System.out.println(totalScore);
    System.out.println(totalScorePart2);
    assertEquals(15, totalScore);
    assertEquals(12, totalScorePart2);

  }

  @Test
  void test_findTotalScore_2() {
    List<List<Character>> strategyGuide = List.of(
        List.of('C', 'Y')
    );

    int totalScore = findTotalScore(strategyGuide);
    int totalScorePart2 = findTotalScorePart2(strategyGuide);
    System.out.println(totalScore);
    System.out.println(totalScorePart2);
    assertEquals(2, totalScore);
    assertEquals(6, totalScorePart2);
  }

}
/**
 * --- Day 2: Rock Paper Scissors --- The Elves begin to set up camp on the beach. To decide whose
 * tent gets to be closest to the snack storage, a giant Rock Paper Scissors tournament is already
 * in progress.
 * <p>
 * Rock Paper Scissors is a game between two players. Each game contains many rounds; in each round,
 * the players each simultaneously choose one of Rock, Paper, or Scissors using a hand shape. Then,
 * a winner for that round is selected: Rock defeats Scissors, Scissors defeats Paper, and Paper
 * defeats Rock. If both players choose the same shape, the round instead ends in a draw.
 * <p>
 * Appreciative of your help yesterday, one Elf gives you an encrypted strategy guide (your puzzle
 * input) that they say will be sure to help you win. "The first column is what your opponent is
 * going to play: A for Rock, B for Paper, and C for Scissors. The second column--" Suddenly, the
 * Elf is called away to help with someone's tent.
 * <p>
 * The second column, you reason, must be what you should play in response: X for Rock, Y for Paper,
 * and Z for Scissors. Winning every time would be suspicious, so the responses must have been
 * carefully chosen.
 * <p>
 * The winner of the whole tournament is the player with the highest score. Your total score is the
 * sum of your scores for each round. The score for a single round is the score for the shape you
 * selected (1 for Rock, 2 for Paper, and 3 for Scissors) plus the score for the outcome of the
 * round (0 if you lost, 3 if the round was a draw, and 6 if you won).
 * <p>
 * Since you can't be sure if the Elf is trying to help you or trick you, you should calculate the
 * score you would get if you were to follow the strategy guide.
 * <p>
 * For example, suppose you were given the following strategy guide:
 * <p>
 * A Y B X C Z This strategy guide predicts and recommends the following:
 * <p>
 * In the first round, your opponent will choose Rock (A), and you should choose Paper (Y). This
 * ends in a win for you with a score of 8 (2 because you chose Paper + 6 because you won). In the
 * second round, your opponent will choose Paper (B), and you should choose Rock (X). This ends in a
 * loss for you with a score of 1 (1 + 0). The third round is a draw with both players choosing
 * Scissors, giving you a score of 3 + 3 = 6. In this example, if you were to follow the strategy
 * guide, you would get a total score of 15 (8 + 1 + 6).
 * <p>
 * What would your total score be if everything goes exactly according to your strategy guide?
 * <p>
 * Your puzzle answer was 14297.
 * <p>
 * --- Part Two --- The Elf finishes helping with the tent and sneaks back over to you. "Anyway, the
 * second column says how the round needs to end: X means you need to lose, Y means you need to end
 * the round in a draw, and Z means you need to win. Good luck!"
 * <p>
 * The total score is still calculated in the same way, but now you need to figure out what shape to
 * choose so the round ends as indicated. The example above now goes like this:
 * <p>
 * In the first round, your opponent will choose Rock (A), and you need the round to end in a draw
 * (Y), so you also choose Rock. This gives you a score of 1 + 3 = 4. In the second round, your
 * opponent will choose Paper (B), and you choose Rock so you lose (X) with a score of 1 + 0 = 1. In
 * the third round, you will defeat your opponent's Scissors with Rock for a score of 1 + 6 = 7. Now
 * that you're correctly decrypting the ultra top secret strategy guide, you would get a total score
 * of 12.
 * <p>
 * Following the Elf's instructions for the second column, what would your total score be if
 * everything goes exactly according to your strategy guide?
 * <p>
 * Your puzzle answer was 10498.
 * <p>
 * Both parts of this puzzle are complete! They provide two gold stars: **
 * <p>
 * At this point, you should return to your Advent calendar and try another puzzle.
 * <p>
 * If you still want to see it, you can get your puzzle input.
 * <p>
 * You can also [Share] this puzzle.
 */
