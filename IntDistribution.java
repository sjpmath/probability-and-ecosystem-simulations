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
import Dist.DoubleDistribution;

public class IntDistribution extends Distribution<Integer>{
  public IntDistribution(){
    super();
  }
  public DoubleDistribution scale(double r){
    DoubleDistribution scaledist = new DoubleDistribution();
    for (Integer event : this.probability.keySet()) {
      scaledist.probability.put(r*(double)event, this.probability.get(event));
    }
    return scaledist;
  }
}
