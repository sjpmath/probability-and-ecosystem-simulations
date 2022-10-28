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

public class IntDistributionSum extends IntDistribution{
  public List<Distribution<Integer>> list;
  public IntDistributionSum(List<Distribution<Integer>> distlist) {
    super();
    list = distlist;
  }
  @Override
  public Integer next() {
    int val = 0;
    for (Distribution<Integer> d : list) {
      val = (int)val + (int)d.next();
    }
    return val;
  }
}
