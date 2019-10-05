package skiplist;

import org.junit.jupiter.api.Test;
import skiplist.Skiplist.Node;

public class SkiplistTest {

  @Test
  public void testToString() {
    Skiplist skiplist = new Skiplist();
    int[] nums = new int[]{9, 1, 6, 2, 8, 10, 3, 4, 5};
    for (int num : nums) {
      skiplist.add(num);
    }
    System.out.println(skiplist);
    skiplist.erase(4);
    System.out.println(skiplist);
    skiplist.erase(1);
    System.out.println(skiplist);
    skiplist.erase(3);
    System.out.println(skiplist);
    for (Node node : skiplist.downwardPath(9)) {
      System.out.print(node.val + ",");
    }
  }
}