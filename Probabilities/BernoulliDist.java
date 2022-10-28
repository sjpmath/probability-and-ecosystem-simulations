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

public class BernoulliDist extends IntDistribution {
  public BernoulliDist(double p) {
    super();
    this.probability.put(0, p);
    this.probability.put(1, 1-p);
  }
}
