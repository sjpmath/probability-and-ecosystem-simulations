import java.util.Random;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Arrays;
import java.util.List;
import java.lang.Math;
import java.util.ArrayList;

class Utilities {
  Utilities() {}
  public int factorial(int n) {
    if (n==0 || n==1) return 1;
    return n*factorial(n-1);
  }
}

class Distribution<T> {
  Random rand = new Random();
  Map<T, Double> probability;
  Distribution() {
    probability = new HashMap<>();
  }
  Distribution(Map<T,Double> p) {
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

class IntDistribution extends Distribution<Integer>{
  IntDistribution(){
    super();
  }
  public void scale(int r){
    Map<Integer,Double> newprob = new HashMap<>();
    for (Integer event : this.probability.keySet()) {
      newprob.put(r*event, this.probability.get(event));
    }
    this.probability = newprob;
  }
}

class IntDistributionSum extends IntDistribution{
  public List<Distribution<Integer>> list;
  IntDistributionSum(List<Distribution<Integer>> distlist) {
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

class DoubleDistribution extends Distribution<Double>{
  DoubleDistribution(){
    super();
  }
  public void scale(double r){
    Map<Double,Double> newprob = new HashMap<>();
    for (Double event : this.probability.keySet()) {
      newprob.put(r*event, this.probability.get(event));
    }
    this.probability = newprob;
  }
}

class DoubleDistributionSum extends DoubleDistribution{
  public List<Distribution<Double>> list;
  DoubleDistributionSum(List<Distribution<Double>> distlist) {
    super();
    list = distlist;
  }
  @Override
  public Double next() {
    double val = (double)0;
    for (Distribution<Double> d : list) {
      val = (double)val + (double)d.next();
    }
    return val;
  }
}

class BernoulliDist extends DoubleDistribution {
  BernoulliDist(double p) {
    super();
    this.probability.put(0.0, p);
    this.probability.put(1.0, 1-p);
  }
}

class UniformDist<T> extends Distribution<T> {
  UniformDist(List<T> events) {
    super();
    int n = events.size();
    double p = ((double)1) / n;
    for (T e: events) {
      this.probability.put(e, p);
    }
  }
}

class BinomialDist extends IntDistribution {
  BinomialDist(int n, double p) {
    super();
    Utilities u = new Utilities();
    for (int k = 0; k <= n; k++) {
      this.probability.put(k, ((double)u.factorial(n)/(double)(u.factorial(k)*u.factorial(n-k))) * Math.pow(p, k) * Math.pow(1-p, n-k));
    }
  }
}



class Main {

  public static void main(String[] args) {
    System.out.println("Bernoulli");
    BernoulliDist ber = new BernoulliDist(0.2);
    for (int i = 0; i < 10; i++) {
      System.out.println(ber.next());
    }
    System.out.println("Uniform");
    List<Integer> events = Arrays.asList(1,2,3,4,5);
    UniformDist<Integer> unif = new UniformDist(events);
    for (int i = 0; i < 10; i++) {
      System.out.println(unif.next());
    }
    System.out.println("Binomial");
    BinomialDist bin = new BinomialDist(10, 0.3);
    for (int i = 0; i < 10; i++) {
      System.out.println(bin.next());
    }
    System.out.println("done");

    // simulating uniform(0,1) using sum of bernoulli rvs
    List<Distribution<Double>> list = new ArrayList<>();
    for (int i = 1; i < 20; i++) {
      BernoulliDist b = new BernoulliDist(0.5);
      b.scale(Math.pow(2,-i));
      list.add(b);
    }
    DoubleDistributionSum sum = new DoubleDistributionSum(list);
    for (int i = 0; i < 10; i++) {
      System.out.println(sum.next());
    }


  }
}
