public class PrettyMuchBestUnionFind {

  private static final class UnionFind {

    private int[] parent;
    private int[] size;
    private int n;

    UnionFind(int n) {
      this.n = n;
      this.parent = new int[n + 1];
      this.size = new int[n + 1];
      for (int i = 0; i <= n; i++) {
        parent[i] = i;
        size[i] = 1;
      }
    }

    private boolean united() {
      return n == 1;
    }

    private boolean connected(int p, int q) {
      return find(p) == find(q);
    }

    private int find(int p) {
      while (p != parent[p]) {
        parent[p] = parent[parent[p]];
        p = parent[p];
      }
      return p;
    }

    private boolean union(int p, int q) {
      int rootX = find(p);
      int rootY = find(q);
      if (rootX == rootY) {
        return false;
      }
      if (size[rootX] < size[rootY]) {
        parent[rootX] = rootY;
        size[rootX] += size[rootY];
      } else {
        parent[rootY] = rootX;
        size[rootY] += size[rootX];
      }
      n--;
      return true;
    }
  }
}
