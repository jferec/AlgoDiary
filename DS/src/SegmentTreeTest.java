import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SegmentTreeTest {

  @Test
  public void testSegmentTreeRMQ() {
    int[] data = {1, 2, 3, 9, 15, 10, 0, 2, 2, 2, 20};
    SegmentTree segmentTree = new SegmentTree(data);
    int n = data.length;
    Assertions.assertEquals(1, segmentTree.rmq(1, 1));
    Assertions.assertEquals(4, segmentTree.rmq(4, 4));
    Assertions.assertEquals(7, segmentTree.rmq(7, 7));

    Assertions.assertEquals(0, segmentTree.rmq(0, 5));
    Assertions.assertEquals(6, segmentTree.rmq(0, 6));
    Assertions.assertEquals(6, segmentTree.rmq(0, 6));
    Assertions.assertEquals(6, segmentTree.rmq(0, n - 1));
    Assertions.assertEquals(7, segmentTree.rmq(n - 4, n - 1));
  }
}