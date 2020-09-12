package unionfind;

public class WeightedQuickUnionByHeightUF implements UnionFind {

  private int[] parent;
  private int[] height;
  private int count;

  public WeightedQuickUnionByHeightUF(int n) {
    this.count = n;
    parent = new int[n];
    height = new int[n];
    for (int i = 0; i < n; i++) {
      parent[i] = 0;
      height[i] = 0;
    }
  }

  public int count() {
    return count;
  }

  public boolean connected(int p, int q) {
    return find(p) == find(q);
  }

  public void union(int p, int q) {
    int x = find(p);
    int y = find(q);
    if (x == y) {
      return;
    }
    if (height[x] < height[y]) {
      parent[x] = y;
    } else if (height[x] > height[y]) {
      parent[y] = x;
    } else {
      parent[y] = x;
      height[x]++;
    }
    count--;
  }

  private int find(int p) {
    while (p != parent[p]) {
      // Path compression
      parent[p] = parent[parent[p]];
      p = parent[p];
    }
    return p;
  }
}
