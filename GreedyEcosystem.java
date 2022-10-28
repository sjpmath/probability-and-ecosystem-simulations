package Trees;
import Dist.*;
import Trees.Tree;
import java.util.Collections;
import java.util.Random;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Arrays;
import java.util.List;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Queue;
import java.util.PriorityQueue;

public class GreedyEcosystem extends Ecosystem {

  public GreedyEcosystem(int n) {
    super(n);
  }

  @Override
  public void sunperiod() {
    // sun patches
    Random rand = new Random();
    Tree[][] count = new Tree[N][N];
    for (int r = 0; r < N; r++) {
      for (int c = 0; c < N; c++) {
        board[r][c] = rand.nextInt(15)+8;
      }
    }
    // absorb equal shares of energy
    for (int r = 0; r < N; r++) {
      for (int c = 0; c < N; c++) {
        Tree t = forest[r][c];
        if (t!=null) {
          int startr = Math.max(0,r-t.rad); int endr = Math.min(N-1, r+t.rad);
          int startc = Math.max(0,c-t.rad); int endc = Math.min(N-1, c+t.rad);
          for (int i = startr; i <= endr; i++) {
            for (int j = startc; j <= endc; j++) {
              if (count[i][j]==null || t.rad > count[i][j].rad) {
                count[i][j] = t;
              }
            }
          }
        }
      }
    }
    for (int r = 0; r < N; r++) {
      for (int c = 0; c < N; c++) {
        if (count[r][c] != null) {
          count[r][c].addEnergy(board[r][c]);
        }
      }
    }


  }


  @Override
  public void bugperiod() {
    // num bugs global var
    // each bug needs record of which trees it has visited
    // terminate when no more allowed trees for all bugs
    boolean[][][] bugvisited = new boolean[N][N][numBugs];
    int[][] bugpos = new int[numBugs][2];

    // start positions of bugs
    ArrayList<Integer> list = new ArrayList<>();
    for (int i = 0; i < 225; i++) list.add(i);
    Collections.shuffle(list);
    for (int i = 0; i < numBugs; i++) {
      int num = list.get(i);
      int r = num/N; int c = num%N;
      board[r][c] = i+1;
      bugpos[i][0] = r;
      bugpos[i][1] = c;
    }

    int a = 0;
    while (true) {
      //updateEnergyRec();
      //print();
      int noMore = 0;

      for (int b = 0; b < numBugs; b++) { // for each bug
        board[bugpos[b][0]][bugpos[b][1]] = 0;
        boolean ans = bfs(b, bugpos[b][0], bugpos[b][1], bugpos, bugvisited);
        board[bugpos[b][0]][bugpos[b][1]] = b+1;
        if (!ans) {
          noMore += 1;
        }
      }
      if (noMore==numBugs) break;

      // kill the trees who died
      for (int r = 0; r < N; r++) {
        for (int c = 0; c < N; c++) {
          if (forest[r][c]!=null && forest[r][c].isDead()) {
            forest[r][c] = null;
            energies[r][c] = 0;
          }
        }
      }

      a+=1;
    }





    System.out.println(a);

  }


}
