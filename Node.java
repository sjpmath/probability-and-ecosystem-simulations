package Node;

import java.util.ArrayList;
import java.util.List;


public class Node {
  public List<Node> children;
  public Node() {
    children = new ArrayList<>();
  }
  public Node(List<Node> c) {
    this.children = c;
  }
  private void print(Node n, int d) {
    System.out.println("- ".repeat(d)+" .");
    for (Node m : n.children) {
      this.print(m, d+1);
    }
  }
  public void print() {
    this.print(this, 0);
  }
}
