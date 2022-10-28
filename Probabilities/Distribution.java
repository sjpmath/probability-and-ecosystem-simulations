package Dist;

import java.util.Random;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Arrays;
import java.util.List;
import java.lang.Math;
import java.util.ArrayList;
import Dist.Utilities;

public class Distribution<T> {
  Random rand = new Random();
  Map<T, Double> probability;
  public Distribution() {
    probability = new HashMap<>();
  }
  public Distribution(Map<T,Double> p) {
    probability = p;
  }

  public T next() {
    double p = rand.nextDouble();
    Iterator<T> iter = probability.keySet().iterator();
    T event = iter.next();
    p -= probability.get(event);
    while (iter.hasNext() && p > 0) {
      event = iter.next();
      p -= probability.get(event);
    }
    return event;
  }
}
