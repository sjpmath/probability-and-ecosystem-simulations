package Dist;

import java.util.Random;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Arrays;
import java.util.List;
import java.lang.Math;
import java.util.ArrayList;

public class Utilities {
  public Utilities() {}
  public int factorial(int n) {
    if (n==0 || n==1) return 1;
    return n*factorial(n-1);
  }
}
