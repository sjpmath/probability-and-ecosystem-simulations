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

public class GeometricDist extends IntDistribution {
  public GeometricDist(double p) {
    super();
    Utilities u = new Utilities();
    for (int k = 1; k <= 10000; k++) {
      this.probability.put(k, Math.pow(1-p, k-1)*p);
    }
  }
}
