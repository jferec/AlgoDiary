import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BestTeamScoreLC1626 {

  public int bestTeamScore(int[] scores, int[] ages) {
    Comparator<Player> playerComparator = Comparator.comparing(p -> -p.age);
    playerComparator = playerComparator.thenComparing(p -> -p.score);
    List<Player> players = IntStream.range(0, scores.length)
        .mapToObj(i -> new Player(scores[i], ages[i]))
        .sorted(playerComparator)
        .collect(Collectors.toList());
    int answer = 0;
    int[] memo = new int[scores.length];
    for (int i = 0; i < players.size(); i++) {
      int score = players.get(i).score;
      memo[i] = score;
      for (int j = 0; j < i; j++) {
        if (players.get(j).score >= players.get(i).score) {
          memo[i] = Math.max(memo[i], memo[j] + score);
        }
      }
      answer = Math.max(answer, memo[i]);
    }
    return answer;
  }

  private static final class Player {

    final int age;
    final int score;

    Player(int age, int score) {
      this.age = age;
      this.score = score;
    }
  }


  public static void main(String[] args) {
    new BestTeamScoreLC1626();
  }
}
