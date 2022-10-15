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
import Dist.Utilities;

public class BinomialDist extends IntDistribution {
  public BinomialDist(int n, double p) {
    super();
    Utilities u = new Utilities();
    for (int k = 0; k <= n; k++) {
      this.probability.put(k, ((double)u.factorial(n)/(double)(u.factorial(k)*u.factorial(n-k))) * Math.pow(p, k) * Math.pow(1-p, n-k));
    }
  }
}
