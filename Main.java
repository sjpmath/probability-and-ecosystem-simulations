import java.util.Random;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Arrays;
import java.util.List;
import java.lang.Math;
import java.util.ArrayList;
import Dist.Distribution;
import Dist.IntDistribution;
import Dist.IntDistributionSum;
import Dist.DoubleDistribution;
import Dist.DoubleDistributionSum;
import Dist.BernoulliDist;
import Dist.BinomialDist;
import Dist.GeometricDist;
import Dist.UniformDist;
import Dist.Utilities;
import Dist.RandomSum;
import Branch.Branching;
import Branch.BranchingMult;

public class Main {

  public static void main(String[] args) {
    System.out.println("Bernoulli");
    BernoulliDist ber = new BernoulliDist(0.2);
    for (int i = 0; i < 10; i++) {
      System.out.println(ber.next());
    }
    System.out.println("Uniform");
    List<Integer> events = Arrays.asList(1,2,3,4,5);
    UniformDist<Integer> unif = new UniformDist(events);
    for (int i = 0; i < 10; i++) {
      System.out.println(unif.next());
    }
    System.out.println("Binomial");
    BinomialDist bin = new BinomialDist(10, 0.3);
    for (int i = 0; i < 10; i++) {
      System.out.println(bin.next());
    }
    System.out.println("done");

    // simulating uniform(0,1) using sum of bernoulli rvs
    List<Distribution<Double>> list = new ArrayList<>();
    for (int i = 1; i < 50; i++) {
      BernoulliDist b = new BernoulliDist(0.5);
      list.add(b.scale(Math.pow(2,-i)));
    }
    DoubleDistributionSum u = new DoubleDistributionSum(list);
    for (int i = 0; i < 10; i++) {
      System.out.println(u.next());
    }

    Branching br = new Branching(bin);
    for (int i = 0; i < 5; i++) {
      br.print();
      br.next();
      System.out.println("---".repeat(10));
    }

    BernoulliDist b1 = new BernoulliDist(0.5);
    b1.scale(2);
    IntDistribution[][] d = new IntDistribution[2][2];
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        d[i][j] = b1;
      }
    }
    BranchingMult bra = new BranchingMult(d);
    for (int i = 0; i < 5; i++) {
      bra.print();
      bra.next();
      System.out.println("---".repeat(10));
    }
    RandomSum rsum = new RandomSum(new GeometricDist(0.2), new GeometricDist(0.3));
    for (int i = 0; i < 5; i++) {
      System.out.println(rsum.next());
    }
    System.out.println("---".repeat(10));
    GeometricDist g = new GeometricDist(0.06);
    for (int i = 0; i < 5; i++) {
      System.out.println(g.next());
    }


  }
}
