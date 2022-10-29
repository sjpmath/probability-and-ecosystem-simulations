package Trees;
import java.util.Random;

public class Tree {

  public int rad;
  public int energy;
  public boolean repel;

  public Tree(int N, int e) {
    Random rand = new Random();
    rad = rand.nextInt(10)+1;
    energy = e;
    repel = false;
  }

  public boolean isDead() {
    return energy<0;
  }

  public int addEnergy(int e) {
    energy += e;
    return energy;
  }


}
