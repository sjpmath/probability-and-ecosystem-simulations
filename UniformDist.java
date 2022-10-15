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

public class UniformDist<T> extends Distribution<T> {
  public UniformDist(List<T> events) {
    super();
    int n = events.size();
    double p = ((double)1) / n;
    for (T e: events) {
      this.probability.put(e, p);
    }
  }
}
