package DSA_Graph;

import java.util.ArrayList;
import java.util.LinkedList;

public class P177_GraphBFS {

    /* Representing a graph using an ArrayList adjacency list. */
    /*
     * An edge is created by storing each of the vertex of the pair 
     * of vertices representing the edge in the ArrayList storing 
     * the other’s adjacent vertices.
     */
    static void addEdgeUndirected(ArrayList<ArrayList<Integer>> adjList, int firstVertex, int secondVertex)
    {
        if(!adjList.get(firstVertex).contains(secondVertex))
            adjList.get(firstVertex).add(secondVertex);
        
        if(!adjList.get(secondVertex).contains(firstVertex))
            adjList.get(secondVertex).add(firstVertex);
    }

    static void printUndirected(ArrayList<ArrayList<Integer>> adjList)
    {
        System.out.println("\nThe undirected graph represented as an adjacency list: ");
        /*
         * A vertex is represented by an index of the adjacency list.
         * The adjacent vertices of the vertex are stored in the ArrayList 
         * at that index.
         */
        for(int i = 0; i < adjList.size(); i++)
        {
            System.out.println("Vertex: "+i+", shares an edge with: "+adjList.get(i));
        }
    }

    /*
     * Level-order BFS traversal
     * - Queue vertices, dequeue vertices and print them, enqueue their linked vertice 
     *   if they have not been visited before and so on.
     * - Initialize queue with the source vertex and set the source as visited.
     * - Iterate until:
     *   - The queue is empty.
     * - Dequeue and store the first element.
     * - If an adjacent vertex has been visted:
     *   Skip and iterate to the next adjacent vertex.
     * - Else:
     *   - Record the vertex as visited.
     *   - Enqueue the adjacent vertex.
     */
    static ArrayList<Integer> bfsTraversal(ArrayList<ArrayList<Integer>> adjList, int source)
    {
        LinkedList<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[adjList.size()];
        for(int i=0; i<visited.length; i++)
        {
            visited[i] = false;
        }

        ArrayList<Integer> bfsResult = new ArrayList<>();

        queue.addLast(source);
        visited[source] = true;

        while(!queue.isEmpty())
        {
            int vertex = queue.removeFirst();
            bfsResult.add(vertex);
            for(int adjacentVertex : adjList.get(vertex))
            {
                if(visited[adjacentVertex] == false)
                {
                    visited[adjacentVertex] = true;
                    queue.addLast(adjacentVertex);
                }
            }
        }

        return bfsResult;
    }

    public static void main(String[] args) {
        int vertices = 4;
        int source = 0;

        /* Representing a graph using an ArrayList adjacency list. */

        /*
         * An ArrayList of ArrayLists is created to associate an integer
         * vertex value with the vertex values of its adjacent vertices.
         */
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();

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
            adjList.add(new ArrayList<Integer>());
        }

        // Set vertices to 4 for this test case
        addEdgeUndirected(adjList, 0, 1);
        addEdgeUndirected(adjList, 0, 2);
        addEdgeUndirected(adjList, 1, 2);
        addEdgeUndirected(adjList, 1, 3);

        // Set vertices to 7 for this test case
        // addEdgeUndirected(adjList, 0, 1);
        // addEdgeUndirected(adjList, 1, 2);
        // addEdgeUndirected(adjList, 1, 3);
        // addEdgeUndirected(adjList, 2, 4);
        // addEdgeUndirected(adjList, 2, 5);
        // addEdgeUndirected(adjList, 3, 5);
        // addEdgeUndirected(adjList, 4, 5);
        // addEdgeUndirected(adjList, 4, 6);
        // addEdgeUndirected(adjList, 5, 6);

        printUndirected(adjList);

        System.out.println("The BFS traversal is: "+bfsTraversal(adjList, source));
    }
}
