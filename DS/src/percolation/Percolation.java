package percolation;

import unionfind.UnionFind;
import unionfind.WeightedQuickUnionByHeightUF;

public class Percolation implements PercolationClient {

  private static final int[][] DIRECTIONS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

  private final UnionFind unionFind;
  private final boolean[][] grid;
  private final int n;
  private int openSites;

  public Percolation(int size) {
    if (size < 1) {
      throw new IllegalArgumentException("Provided size is invalid");
    }
    this.n = size;
    this.grid = new boolean[size][size];
    this.openSites = 0;
    this.unionFind = new WeightedQuickUnionByHeightUF(n * n);
  }

  @Override
  public void open(int row, int col) {
    assertCoordinates(row, col);
    if (isOpen(row, col)) {
      return;
    }
    grid[row][col] = true;
    openSites++;
    for (int[] dir : DIRECTIONS) {
      int nRow = row + dir[0];
      int nCol = col + dir[1];
      if (isIn(nRow, nCol) && isOpen(nRow, nCol)) {
        unionFind.union(linearIndex(row, col), linearIndex(nRow, nCol));
      }
    }
  }

  @Override
  public boolean isOpen(int row, int col) {
    assertCoordinates(row, col);
    return grid[row][col];
  }

  @Override
  public boolean isFull(int row, int col) {
    return !isOpen(row, col);
  }


  @Override
  public int numberOfOpenSites() {
    return openSites;
  }

  @Override
  public boolean percolates() {
    for (int r = 0; r < n; r++) {
      for (int c = 0; c < n; c++) {
        if (isOpen(0, r) && isOpen(n - 1, c) &&
            unionFind.connected(linearIndex(0, r), linearIndex(n - 1, c))) {
          return true;
        }
      }
    }
    return false;
  }

  private void assertCoordinates(int r, int c) {
    if (!isIn(r, c)) {
      throw new IllegalArgumentException("Coordinates are not valid.");
    }
  }

  private boolean isIn(int r, int c) {
    return r >= 0 && r < n && c >= 0 && c < n;
  }

  private int linearIndex(int r, int c) {
    return n * r + c;
  }
}
