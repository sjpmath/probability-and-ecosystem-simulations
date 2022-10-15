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

public class DoubleDistributionSum extends DoubleDistribution{
  public List<Distribution<Double>> list;
  public DoubleDistributionSum(List<Distribution<Double>> distlist) {
    super();
    list = distlist;
  }
  @Override
  public Double next() {
    double val = 0.0;
    for (Distribution<Double> d : list) {
      val = (double)val + (double)d.next();
    }
    return val;
  }
}
