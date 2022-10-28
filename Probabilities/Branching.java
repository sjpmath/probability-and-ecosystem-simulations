package Branch;

import Dist.Distribution;
import Dist.IntDistribution;
import Node.Node;
import java.util.ArrayList;
import java.util.List;


public class Branching {
  public IntDistribution dist;
  public Node node;
  public List<Node> curGen;
  public Branching() {
    node = new Node();
    curGen = new ArrayList<>();
    curGen.add(node);
    dist = new IntDistribution();
  }
  public Branching(IntDistribution d) {
    node = new Node();
    curGen = new ArrayList<>();
    curGen.add(node);
    dist = d;
  }
  public void next() {
    List<Node> nextGen = new ArrayList<>();
    for (Node n : curGen) {
      int k = dist.next();
      for (int i = 0; i < k; i++) {
        Node m = new Node();
        n.children.add(m);
        nextGen.add(m);
      }
    }
    curGen = nextGen;
  }
  public void print() {
    node.print();
  }

}
