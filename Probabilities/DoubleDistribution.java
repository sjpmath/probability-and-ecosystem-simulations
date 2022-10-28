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

public class DoubleDistribution extends Distribution<Double>{
  public DoubleDistribution(){
    super();
  }
  public DoubleDistribution scale(int r){
    DoubleDistribution scaledist = new DoubleDistribution();
    for (Double event : this.probability.keySet()) {
      scaledist.probability.put(r*(double)event, this.probability.get(event));
    }
    return scaledist;
  }
}
