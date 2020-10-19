public class SegmentTree {

  private int[] heap;
  private int[] data;
  private int n;

  private int left(int p) {
    return p << 1;
  }

  private int right(int p) {
    return (p << 1) + 1;
  }

  public SegmentTree(int[] data) {
    this.data = data;
    this.n = data.length;
    this.heap = new int[4 * n];
    build(1, 0, n - 1);
  }

  private void build(int p, int L, int R) {
    if (L == R) {
      heap[p] = L;
    } else {
      build(left(p), L, (L + R) / 2);
      build(right(p), (L + R) / 2 + 1, R);
      int p1 = heap[left(p)];
      int p2 = heap[right(p)];
      heap[p] = (data[p1] < data[p2]) ? p1 : p2;
    }
  }

  private int updatePoint(int p, int L, int R, int idx, int value) {
    int i = idx;
    int j = idx;

    // if the current interval does not intersect
    // the update interval, return this st node value!
    if (i > R || j < L) {
      return heap[p];
    }

    // if the current interval is included in the update range,
    // update that st[node]
    if (i == L && j == R) {
      data[i] = value;
      return heap[p] = L;
    }

    // compute the minimum position in the
    // left and right part of the interval
    int p1, p2;
    p1 = updatePoint(left(p), L, (L + R) / 2, idx, value);
    p2 = updatePoint(right(p), (L + R) / 2 + 1, R, idx, value);

    // return the position where the overall minimum is
    return heap[p] = (data[p1] <= data[p2]) ? p1 : p2;
  }

  private int rmq(int p, int L, int R, int i, int j) {          // O(log n)
    if (i > R || j < L) {
      return -1; // current segment outside query range
    }
    if (L >= i && R <= j) {
      return heap[p];               // inside query range
    }

    // compute the min position in the left and right part of the interval
    int p1 = rmq(left(p), L, (L + R) / 2, i, j);
    int p2 = rmq(right(p), (L + R) / 2 + 1, R, i, j);

    if (p1 == -1) {
      return p2;   // if we try to access segment outside query
    }
    if (p2 == -1) {
      return p1;   // same as above
    }
    return (data[p1] <= data[p2]) ? p1 : p2; // as as in build routine
  }

  public int rmq(int i, int j) {
    return rmq(1, 0, n - 1, i, j);
  }

  public int updatePoint(int idx, int value) {
    return updatePoint(1, 0, n - 1, idx, value);
  }
}
