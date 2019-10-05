package skiplist;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * LC #1206 - Design SkipList
 */
class Skiplist {

  private Node sentinel = new Node(0, null, null);
  private final Random random = new Random();
  private int maxHeight = 0;

  public boolean search(int target) {
    Node prev = searchPath(target);
    return prev.right != null && prev.right.val == target;
  }

  public void add(int num) {
    int height = drawHeight();
    int heightInc = height - this.maxHeight;
    for (int i = 0; i < heightInc; i++) {
      addSentinel();
    }
    List<Node> path = downwardPath(num).subList(this.maxHeight - height, this.maxHeight);
    List<Node> toConnect = new ArrayList<>();
    for (Node node : path) {
      Node newNode = new Node(num, null, null);
      newNode.right = node.right;
      node.right = newNode;
      //toConnect contains the newly created nodes (we need to connect them top-down)
      toConnect.add(newNode);
    }
    for (int i = 0; i < toConnect.size() - 1; i++) {
      toConnect.get(i).down = toConnect.get(i + 1);
    }
  }

  public boolean erase(int x) {
    Node curr = sentinel;
    while (true) {
      if (curr.right == null || curr.right.val > x) {
        if (curr.down == null) {
          cleanUpSentinels();
          return false;
        }
        curr = curr.down;
      } else if (curr.right.val == x) {
        curr.right = curr.right.right;
        if (curr.down == null) {
          cleanUpSentinels();
          return true;
        }
        curr = curr.down;
      } else {
        curr = curr.right;
      }
    }
  }

  private Node searchPath(int x) {
    Node curr = sentinel;
    while (true) {
      if (curr.right == null || curr.right.val >= x) {
        if (curr.down == null) {
          return curr;
        }
        curr = curr.down;
      } else {
        curr = curr.right;
      }
    }
  }

  List<Node> downwardPath(int x) {
    ArrayList<Node> path = new ArrayList<>();
    Node curr = sentinel.down;
    if (curr == null) {
      return path;
    }
    while (true) {
      if (curr.right == null || curr.right.val >= x) {
        path.add(curr);
        if (curr.down == null) {
          return path;
        }
        curr = curr.down;
      } else {
        curr = curr.right;
      }
    }
  }

  private int drawHeight() {
    int height = 1;
    while (!heads()) {
      height++;
    }
    return height;
  }

  private boolean heads() {
    return random.nextInt() % 2 == 0;
  }

  private void addSentinel() {
    maxHeight++;
    Node node = new Node(0, null, null);
    node.down = sentinel;
    sentinel = node;
  }

  private void cleanUpSentinels() {
    while (sentinel.down != null && sentinel.down.right == null) {
      this.maxHeight--;
      sentinel = sentinel.down;
    }
  }

  static class Node {

    final int val;
    Node down;
    Node right;

    Node(int value, Node down, Node right) {
      this.val = value;
      this.down = down;
      this.right = right;
    }

  }

  @Override
  public String toString() {
    StringBuilder result = new StringBuilder();
    if (maxHeight == 0) {
      return result.toString();
    }
    Node traverse = sentinel.down;
    int level = 0;
    while(traverse != null){
      result.append(String.format("L%s: ", level++));
      Node t1 = traverse;
      while(t1 != null){
        result.append(String.format("%s -> ", t1.val));
        t1 = t1.right;
      }
      traverse = traverse.down;
      result.append("\n");
    }
    return result.toString();
  }
}
