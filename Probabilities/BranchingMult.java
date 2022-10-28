package Branch;

import Dist.Distribution;
import Dist.IntDistribution;
import Node.Node;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import Node.LNode;

public class BranchingMult {
  public List<Map<Integer, IntDistribution>> dists; // dist[species] gives offspring dist for species
  public int numTypes;
  public LNode node;
  public List<LNode> curGen;
  public BranchingMult() {
    dists = new ArrayList<>();
    numTypes = 0;
    node = new LNode(0);
    curGen = new ArrayList<>();
    curGen.add(node);
  }
  public BranchingMult(IntDistribution[][] distributions) {
    node = new LNode(0);
    dists = new ArrayList<>();
    curGen = new ArrayList<>();
    curGen.add(node);
    int n = distributions.length;
    numTypes = n;
    for (int i = 0; i < n; i++) {
      Map<Integer, IntDistribution> m = new HashMap<>();
      for (int j = 0; j < n; j++) {
        m.put(j, distributions[i][j]);
      }
      dists.add(m);
    }
  }

  public void next() {
    //System.out.println("next called");
    List<LNode> nextGen = new ArrayList<>();
    for (LNode n : curGen) {
      //System.out.println(n.label);
      int i = n.label;
      for (int j = 0; j < numTypes; j++) {
        int k = dists.get(i).get(j).next();
        for (int l = 0; l < k; l++) {
          LNode newNode = new LNode(j);
          n.children.add(newNode);
          nextGen.add(newNode);
        }
      }
    }
    curGen = nextGen;
  }
  public void print() {
    node.print();
  }

}
