package DSA_Graph;

import java.util.ArrayList;
import java.util.LinkedList;

public class P183_ShortestPath_UnweightedGraph {
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
     *   and so on.
     * - Initialize queue with the source vertex.
     * - Iterate until:
     *   - The queue is empty.
     *   - The size of the output containing traversal is not equal to the size of the input 
     *     containing the adjacency list.
     *     The algorithm will go into an infinite loop if this condition is not set as the the 
     *     the adjacent vertices will keep getting added and the algorithm will keep traversing 
     *     the graph.
     * - Interate over the size of the elements of the queue.
     * - Dequeue and store the first element.
     * - If the vertex has been visted:
     *   Skip and iterate to the next vertex.
     * - Else:
     *   - Print it.
     *   - Record the vertex as visited.
     *   - Enqueue all the elements linked to the this vertex.
     */
    /*
     * BFS Traversal
     * - Compute cost at a vertex by: 
     *   Cost to the source vertex + cost to the adjacent vertex (1 for unweighted)
     * - Cost of shortest path is the cost of the path when the vertex is first 
     *   encountered.
     */
    static int[] verticesShortestPath(ArrayList<ArrayList<Integer>> adjList, int source, int dst)
    {
        LinkedList<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[adjList.size()];
        for(int i=0; i<visited.length; i++)
        {
            visited[i] = false;
        }

        int[] destPathCost = new int[adjList.size()];

        ArrayList<Integer> bfsResult = new ArrayList<>();
    
        queue.addLast(source);
        visited[source] = true;
        // The path until the source is initialized as 0
        destPathCost[source] = 0;

        while(!queue.isEmpty())
        {
            int vertex = queue.removeFirst();
            bfsResult.add(vertex);
            for(int adjacentVertex : adjList.get(vertex))
            {
                if(visited[adjacentVertex] == false)
                {
                    destPathCost[adjacentVertex] = destPathCost[vertex] + 1;
                    visited[adjacentVertex] = true;
                    queue.addLast(adjacentVertex);
                }
            }
        }

        System.out.println("The BFS traversal is: "+bfsResult);

        return destPathCost;
    }

    /*Approach 2: Produce all the paths to destination vertex in a graph */
    /*
     * DFS
     * Each adjacent vertex from a source vertex can lead to a unique path.
     * Recursive solution:
     * - Add the current source vertex to path
     * - Update the current vertex as visted.
     * - Base case: 
     *   - The destination node has been found
     *   - No recursive call is made
     *   - The path is added to the ArrayList of found paths
     * - Recursive case:
     *   - Recursive calls are made towards all adjacent nodes to the current source
     *   - The recursive calls are made by:
     *   - The adjacent node is the new source node
     *   - To ensure a vertex is not revisited in a path:
     *     - A boolean visited array is passed to not make
     *       recursive calls using adjacent vertices that 
     *       have already been visited.
     */

    static void shortestPathDestRecursive(ArrayList<ArrayList<Integer>> adjList, int src, int dst, boolean[] visited, ArrayList<Integer> path, ArrayList<ArrayList<Integer>> paths)
    {
        path.add(src);
        visited[src] = true;

        if(src == dst)
        {
            ArrayList<Integer> foundPath = new ArrayList<>();
            foundPath.addAll(path);
            paths.add(foundPath);
            System.out.println(foundPath);
            return;
        }

        for(int adjacentVertex : adjList.get(src))
        {
            boolean[] visitedVertex = visited.clone();

            if(visitedVertex[adjacentVertex] == false)
            {
                ArrayList<Integer> currentPath = new ArrayList<>();
                currentPath.addAll(path);

                shortestPathDestRecursive(adjList, adjacentVertex, dst, visitedVertex, currentPath, paths);
            }
        }
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

        int[] verticesSPResult = verticesShortestPath(adjList, source, 2);

        int vertexCounter = 0;      
        for(int vertexSPResult : verticesSPResult)
            System.out.println("Cost of shortest path to reach vertex "+(vertexCounter++)+" is: " +vertexSPResult);

        /*Approach 2: Recursive approach to find all the paths. */
        ArrayList<ArrayList<Integer>> paths = new ArrayList<>();
        ArrayList<Integer> path = new ArrayList<>();
        // path.add(source);
        boolean[] visited = new boolean[adjList.size()];
        for(int i=0; i<visited.length; i++)
        {
            visited[i] = false;
        }
        // visited[source] = true;

        shortestPathDestRecursive(adjList, source, 2, visited, path, paths);

        System.out.println("Total number of unique paths: "+paths.size());
    }
}
