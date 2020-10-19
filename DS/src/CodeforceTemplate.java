import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CodeforceTemplate {

  private static final int[][] DIRS_2D = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};

  private final static class FastScanner {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer("");

    String next() {
      while (!st.hasMoreTokens()) {
        try {
          st = new StringTokenizer(br.readLine());
        } catch (IOException e) {
        }
      }
      return st.nextToken();
    }

    int nextInt() {
      return Integer.parseInt(next());
    }

    long nextLong() {
      return Long.parseLong(next());
    }
  }

  public static void main(String[] args) {
    FastScanner fastScanner = new FastScanner();
    int tests = fastScanner.nextInt();
    for (int i = 0; i < tests; i++) {
      runTest();
    }
  }

  private static void runTest() {
    //test
  }
}