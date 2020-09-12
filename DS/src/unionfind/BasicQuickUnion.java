package unionfind;

public class BasicQuickUnion implements UnionFind {

  private int[] id;

  public BasicQuickUnion(int n) {
    this.id = new int[n];
    for (int i = 0; i < n; i++) {
      id[i] = i;
    }
  }

  private int findRoot(int i) {
    while (i != id[i]) {
      i = id[i];
    }
    return i;
  }

  public boolean connected(int p, int q) {
    return findRoot(p) == findRoot(q);
  }

  public void union(int p, int q) {
    id[findRoot(p)] = findRoot(q);
  }
}
