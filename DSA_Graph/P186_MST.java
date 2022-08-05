package DSA_Graph;

import java.util.ArrayList;
import java.util.HashMap;

public class P186_MST {
    /* Representing a graph using an ArrayList adjacency list. */
    /*
     * An edge is created by storing each of the vertex of the pair 
     * of vertices representing the edge in the ArrayList storing 
     * the other’s adjacent vertices.
     */
    static void addEdgeUndirected(ArrayList<HashMap<Integer, Integer>> adjList, int firstVertex, int secondVertex, int weight)
    {
        if(!adjList.get(firstVertex).containsKey(secondVertex))
            adjList.get(firstVertex).put(secondVertex, weight);
        
        if(!adjList.get(secondVertex).containsKey(firstVertex))
            adjList.get(secondVertex).put(firstVertex, weight);
    }

    static void printUndirected(ArrayList<HashMap<Integer, Integer>> adjList)
    {
        System.out.println("\nThe undirected weighted graph represented as an adjacency list: ");
        /*
         * A vertex is represented by an index of the adjacency list.
         * The adjacent vertices of the vertex are stored in the ArrayList 
         * at that index.
         */
        for(int i = 0; i < adjList.size(); i++)
        {
            System.out.println("Vertex: "+i+", shares an edge with (Vertex=Weight): "+adjList.get(i));
        }
    }

    static void addEdgeTree(ArrayList<HashMap<Integer, Integer>> adjList, int firstVertex, int secondVertex, int weight)
    {
        if(!adjList.get(firstVertex).containsKey(secondVertex))
            adjList.get(firstVertex).put(secondVertex, weight);
    }

    static void printTree(ArrayList<HashMap<Integer, Integer>> adjList)
    {
        System.out.println("\nThe undirected weighted graph represented as an adjacency list: ");
        /*
         * A vertex is represented by an index of the adjacency list.
         * The adjacent vertices of the vertex are stored in the ArrayList 
         * at that index.
         */
        for(int i = 0; i < adjList.size(); i++)
        {
            System.out.println("Root: "+i+", has child (Vertex=Weight): "+adjList.get(i));
        }
    }

    static void primMSTWrapper(ArrayList<HashMap<Integer, Integer>> adjList)
    {

        ArrayList<HashMap<Integer, Integer>> minSpanningTree = new ArrayList<>();
        for(int i=0; i<adjList.size(); i++)
        {
            minSpanningTree.add(new HashMap<Integer, Integer>());
        }
        boolean[] visited = new boolean[adjList.size()];
        for(int i=0; i<visited.length; i++)
        {
            visited[i] = false;
        }
        ArrayList<Integer> inMSP = new ArrayList<>();
        inMSP.add(0);

        primMST(adjList, inMSP, minSpanningTree);
        printTree(minSpanningTree);

    }

    static void primMST(ArrayList<HashMap<Integer, Integer>> adjList, ArrayList<Integer> inMSP, ArrayList<HashMap<Integer, Integer>> minSpanningTree)
    {   
        int firstFoundVertex = 0;
        int secondFoundVertex = 0;

        int foundVertexMinEdge = Integer.MAX_VALUE;

        /*
         * - Iterate until the MSP has as many vertices as the 
         *   graph:
         * 
         * - Iterate through all the vertices in the tree:
         *   - Iterate through adjacent vertices of the tree. 
         *   - Select vertex that has the lowest edge cost with 
         *     one of the tree's vertices.
         *   - Add the lowest cost edge to the tree.
         *   - Add the vertex to the visited list.
         */
        int minSpanningTreeVertices = 1;
        int graphVertices = adjList.size();

        while(minSpanningTreeVertices < graphVertices)
        {
            foundVertexMinEdge = Integer.MAX_VALUE;
            for(int vertex : inMSP)
            {
                for(int adjacentVertex : adjList.get(vertex).keySet())
                {
                    if(!inMSP.contains(adjacentVertex) && adjList.get(vertex).get(adjacentVertex) < foundVertexMinEdge)
                    {
                        firstFoundVertex = vertex;
                        secondFoundVertex = adjacentVertex;
                        foundVertexMinEdge = adjList.get(vertex).get(adjacentVertex);
                    }  
                }
            }
            addEdgeTree(minSpanningTree, firstFoundVertex, secondFoundVertex, adjList.get(firstFoundVertex).get(secondFoundVertex));
            inMSP.add(secondFoundVertex);
            minSpanningTreeVertices++;
        }
    }

    public static void main(String[] args) {
        int vertices = 7;

        /* Representing a graph using an ArrayList adjacency list. */
        /*
         * An ArrayList of ArrayLists is created to associate an integer
         * vertex value with the vertex values of its adjacent vertices.
         */
        ArrayList<HashMap<Integer, Integer>> adjList = new ArrayList<>();

        /*
         * - Empty ArrayList objects are inserted in the 
         *   ArrayList to record each vertex's adjacent 
         *   vertices.
         * - Each vertex is represented by an integer and 
         *   the list of each vertex's adjacent vertices 
         *   are stored at the corresponding index of the 
         *   adjacency list ArrayList.
         */
        for(int i=0; i<vertices; i++)
        {
            adjList.add(new HashMap<Integer, Integer>());
        }

        // Set vertices to 5 for this test case
        // addEdgeUndirected(adjList, 0, 1, 1);
        // addEdgeUndirected(adjList, 1, 2, 3);
        // addEdgeUndirected(adjList, 2, 3, 2);
        // addEdgeUndirected(adjList, 3, 0, 4);
        // addEdgeUndirected(adjList, 3, 4, 5);


        // Set vertices to 7 for this test case
        addEdgeUndirected(adjList, 0, 1, 1);
        addEdgeUndirected(adjList, 1, 2, 2);
        addEdgeUndirected(adjList, 1, 3, 4);
        addEdgeUndirected(adjList, 2, 4, 3);
        addEdgeUndirected(adjList, 2, 5, 5);
        addEdgeUndirected(adjList, 3, 5, 2);
        addEdgeUndirected(adjList, 4, 5, 1);
        addEdgeUndirected(adjList, 4, 6, 6);
        addEdgeUndirected(adjList, 5, 6, 3);

        printUndirected(adjList);

        primMSTWrapper(adjList);
    }
}
