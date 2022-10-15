package Node;

import java.util.ArrayList;
import java.util.List;
import Node.Node;

public class LNode {
  public int label;
  public List<LNode> children;
  public LNode(int label) {
    children = new ArrayList<>();
    this.label = label;
  }

  private void print(LNode n, int d) {
    System.out.println("- ".repeat(d)+n.label);
    for (LNode m : n.children) {
      this.print(m, d+1);
    }
  }

  public void print() {
    this.print(this, 0);
  }
}
