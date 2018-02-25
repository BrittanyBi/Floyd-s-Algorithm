// Brittany Bianco - Project 5: Floyd's Algorithm, Algorithms and Algorithm Analysis
// Credit to Professor Shultz for clarification of the recursion in Floyd's algorithm.

import java.util.Scanner;

public class Floyd{
   private int numVertices;
   private Cell[][] graph;
   private final int INFINITY = 100000000;
   
   public Floyd(){
   }
   
   // Cell class simplifies containment and access of data.
   private class Cell{
      public int weight, vertexLink;
      
      public Cell(int weight){
         if(weight == -1) weight = INFINITY;
         this.weight = weight;
         this.vertexLink = -1; // Intermediate vertices are printed as 1 + their index
      }
      
      public String toString(){
         if(weight == INFINITY) return "(-," + (vertexLink+1) + ")";
         return "(" + weight + "," + (vertexLink+1) + ")";
      }
   }
   
   //----------------------------------------
   
   public static void main(String[] args){
      Floyd p = new Floyd();
      
      p.takeInput();
      
      System.out.println("Original Graph:");
      p.displayGraph();
      
      p.findDGraphs();
   }
   
   //----------------------------------------
   
   public void takeInput(){
   // Obtain the number of vertices and the graph weights from the user.
   
      //----------- Testing Material ----------//
      /*
      int[][] test1 = {{0, 12, 5, -1, -1, -1},
                      {-1, 0, -1, 17, -1, -1},
                      {-1, 5, 0, 13, 6, -1},
                      {-1, -1, -1, 0, -1, 20},
                      {-1, -1, -1, 1, 0, 10},
                      {8, -1, -1, -1, -1, 0}};
      
      int[][] test2 = {{0, 1, -1, 1, 5},
                      {9, 0, 3, 2, -1},
                      {-1, -1, 0, 4, -1},
                      {-1, -1, 2, 0, 3},
                      {3, -1, -1, -1, 0}};
      
      int[][] test3 = {{0, 5, 8, 12, 15},
                      {-1, 0, 5, 8, -1},
                      {-1, -1, 0, 2, 4},
                      {-1, -1, -1, 0, 1},
                      {6, -1, -1, -1, 0}};
      
      numVertices = test.length;
      
      graph = new Cell[numVertices][numVertices];
      
      for(int i = 0; i < numVertices; i++){
         for(int k = 0; k < numVertices; k++){
            graph[i][k] = new Cell(test[i][k]);
         }
      }
      */
      
      //----------- Taking User Input ----------//
      
      Scanner s = new Scanner(System.in);
      
      System.out.println("Please enter the number of vertices in the graph:");
      numVertices = s.nextInt();
      s.nextLine(); // to progress past the newline character
      
      graph = new Cell[numVertices][numVertices];
      
      System.out.println("Please enter the rows of weights as single lines one at a time,"
                       + "\n each weight in one row separated by a space:");
      
      for(int i = 0; i < numVertices; i++){
         
         System.out.print("Row " + (i+1) + ": ");
         String[] weights = s.nextLine().split(" ");
         
         for(int k = 0; k < numVertices; k++){
            graph[i][k] = new Cell(Integer.parseInt(weights[k]));
         }
      }
      
      System.out.println(); // for readability
   }
   
   //----------------------------------------
   
   public void findDGraphs(){
   
      for(int d = 0; d < numVertices; d++){ // Vertex we're checking for shorter paths through
         for(int i = 0; i < numVertices; i++){
            for(int k = 0; k < numVertices; k++){ // (i,k): Cell we're checking for a shorter path in
               if(i==k || i==d || k==d) continue;
               // Compare stored weight to (weight in (i,d) + weight in (d,k))
               // If the sum < stored weight, use that path instead.
               int sum = graph[i][d].weight + graph[d][k].weight;
               if(sum < graph[i][k].weight){
                  graph[i][k].weight = sum;
                  graph[i][k].vertexLink = d;
               }
            }
         }
         
         System.out.println("Graph D" + (d+1) + ":");
         displayGraph();
      }
      
   }
   
   //----------------------------------------
   
   public void displayGraph(){
      System.out.print("  "); // Initial indentation
      
      // Top labels:
      for(int k = 0; k < numVertices; k++){
         System.out.printf("   " + (k+1) + "   ");
      }
      
      // Graph:
      for(int i = 0; i < numVertices; i++){
         System.out.print("\n" + (i+1)); // Side labels
         
         for(int k = 0; k < numVertices; k++){
            System.out.printf("%7s", graph[i][k]);
         }
         
         System.out.println();
      }
      
      System.out.println("Key: (weight, intermediate vertex)\n");
   }
   
   //----------------------------------------
   
   public String displayOptimalPath(int start, int goal){
      return "unimplemented";
   }
}