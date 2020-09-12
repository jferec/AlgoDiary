package percolation;

public interface PercolationClient {

  void open(int row, int col);

  boolean isOpen(int row, int col);

  boolean isFull(int row, int col);

  int numberOfOpenSites();

  boolean percolates();
}
