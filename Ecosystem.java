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
import java.util.LinkedList;

public class Ecosystem {

  public int N;
  public int[][] board;
  public Tree[][] forest;
  public int[][] energies;
  public int[][] rads;
  public int alertrad;
  public int numTrees;
  public int startenergy;
  public int numBugs;
  public int buglife;
  public int eatenergy;

  public Ecosystem(int n) {
    N = n;
    board = new int[N][N];
    forest = new Tree[N][N];
    energies = new int[N][N];
    rads = new int[N][N];

    alertrad = 4;
    numTrees = 110;
    startenergy = 10;
    numBugs = 25;
    eatenergy = 7;


    ArrayList<Integer> list = new ArrayList<>();
    for (int i = 0; i < 225; i++) list.add(i);
    Collections.shuffle(list);
    for (int i = 0; i < numTrees; i++) {
      int num = list.get(i);
      int r = num/N; int c = num%N;
      forest[r][c] = new Tree(N, startenergy);
      energies[r][c] = startenergy;
      rads[r][c] = forest[r][c].rad;
    }
  }

  public void sunperiod() {
    // sun patches
    Random rand = new Random();
    List<Tree>[][] count = new List[N][N];
    for (int r = 0; r < N; r++) {
      for (int c = 0; c < N; c++) {
        board[r][c] = rand.nextInt(15)+20;
        count[r][c] = new ArrayList<>();
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
              count[i][j].add(t);
            }
          }
        }
      }
    }
    for (int r = 0; r < N; r++) {
      for (int c = 0; c < N; c++) {
        if (count[r][c].size()!=0) {
          int num = count[r][c].size();
          int e = board[r][c]/num;
          for (Tree t : count[r][c]) {
            t.addEnergy(e);
          }
        }
      }
    }


  }

  public boolean bfs(int b, int sr, int sc, int[][] bugpos, boolean[][][] bugvisited) {
    int[] dr = new int[]{1,-1,0,0};
    int[] dc = new int[]{0,0,1,-1};
    boolean[][] visited = new boolean[N][N];
    Queue<List<Integer>> que = new LinkedList<>();
    visited[sr][sc] = true;
    que.add(Arrays.asList(sr,sc));
    while (!que.isEmpty()) {
      List<Integer> current = que.remove();
      int r = current.get(0); int c = current.get(1);
      if (forest[r][c]!=null && !bugvisited[r][c][b] && !forest[r][c].repel) { // if found tree not yet visited, closest
        // eat
        forest[r][c].addEnergy(-eatenergy);
        bugvisited[r][c][b] = true;
        bugpos[b][0] = r; bugpos[b][1] = c;
        return true;
      }
      for (int i = 0; i < 4; i++) {
          int nr = r+dr[i]; int nc = c+dc[i];
          if (nr<0 || nr>=N || nc<0 || nc>=N) continue;
          if (!visited[nr][nc]){
              //System.out.println(Arrays.toString(new int[]{nr, nc}));
              visited[nr][nc]=true;
              //bugvisited[nr][nc][b] = true;
              que.add(Arrays.asList(nr,nc));
          }
      }
    }
    return false;
  }

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

      // trees who just got eaten and still alive alert other trees, alert oneself too
      for (int b = 0; b < numBugs; b++) {
        int r = bugpos[b][0]; int c = bugpos[b][1];
        if (forest[r][c]!=null) {
          int startr = Math.max(0, r-alertrad); int endr = Math.min(N-1, r+alertrad);
          int startc = Math.max(0, c-alertrad); int endc = Math.min(N-1, c+alertrad);
          for (int i = startr; i <= endr; i++) {
            for (int j = startc; j <= endc; j++) {
              if (forest[r][c]!=null) forest[r][c].repel = true;
            }
          }
        }
      }

      a+=1;
    }





    System.out.println(a);

  }

  public void updateEnergyRec() {
    for (int r = 0; r < N; r++) {
      for (int c = 0; c < N; c++) {
        if (forest[r][c] != null) {
          energies[r][c] = forest[r][c].energy;
          rads[r][c] = forest[r][c].rad;
        } else rads[r][c] = 0;
      }
    }
  }

  public void print() {
    System.out.println("--------------- Current State --------------");
    System.out.println("Forest state");
    for (int i = 0; i < N; i++) {
      System.out.println(Arrays.toString(energies[i]));
    }

    System.out.println("Board state");
    for (int i = 0; i < N; i++) {
      System.out.println(Arrays.toString(board[i]));
    }

  }

  public void printrad(){
    System.out.println("Radii");
    for (int r = 0; r < N; r++) {
      System.out.println(Arrays.toString(rads[r]));
    }
  }

  public void resetboard() {
    for (int r = 0; r < N; r++) {
      for (int c = 0; c < N; c++) {
        board[r][c] = 0;
      }
    }
  }

  public void resetrepel() {
    for (int r = 0; r < N; r++) {
      for (int c = 0; c < N; c++) {
        if (forest[r][c]!=null) forest[r][c].repel=false;
      }
    }
  }

  public void resetenergy() {
    for (int r = 0; r < N; r++) {
      for (int c = 0; c < N; c++) {
        if (forest[r][c]!=null) {
          forest[r][c].energy = startenergy;
          energies[r][c] = startenergy;
        }
      }
    }
  }

  public void day() {
    printrad();
    print();
    resetboard();
    System.out.println("----------- Sun -----------");
    sunperiod();
    updateEnergyRec();
    print();
    resetboard();
    System.out.println("----------- Bug -----------");
    bugperiod();
    updateEnergyRec();
    print();
    resetrepel();
    printrad();
    //resetboard();
    //resetenergy();
    //print();
  }

  public void simulate() {
    for (int i = 0; i < 300; i++) {
      day();
    }
  }


}
