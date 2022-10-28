package Dist;

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

public class RandomSum extends IntDistribution{
  public Distribution<Integer> X;
  public Distribution<Integer> N;
  public RandomSum(Distribution<Integer> xdist, Distribution<Integer> ndist) {
    super();
    X = xdist;
    N = ndist;
  }
  @Override
  public Integer next() {
    List<Distribution<Integer>> list = new ArrayList<>();
    int n = N.next();
    int val = 0;
    for (int i = 0; i < n; i++) {
      val += X.next();
    }
    return val;
  }
}
