package DSA_Graph;

import java.util.ArrayList;
import java.util.LinkedList;

public class P184_DetectCycleGraph {
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
     * BFS Interative solution:
     * - Traverse a graph in BFS fashion.
     * - If a vertex has an adjacent vertex that has already been 
     *   visited before and the adjacent vertex is not the source 
     *   of the current vertex in the traversal path, then, the graph 
     *   has a cycle.
     * - To check if the adjacent vertex has been visited before:
     *   - A boolean array holds a true value for each visited vertex.
     *   - When a vertex is visited first time its corresponding index 
     *     is set to true in the boolean visited array.
     * - To check if the adjacent vertex is not the source of the vertex:
     *   - When a vertex is visited first time its source in the traversal
     *     is recorded.
     *   - The source of the adjacent vertex being traversed is set as the 
     *     current vertex.
     */
    static boolean detectCyclesBFSUnDirectedGraph(ArrayList<ArrayList<Integer>> adjList, int source)
    {
        LinkedList<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[adjList.size()];

        /*
         * This array records the source of a vertex 
         * when it is visited the first time.
         */
        int[] vertexSource = new int[adjList.size()];

        for(int i=0; i<visited.length; i++)
        {
            visited[i] = false;
        }

        boolean found = false;

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
                    vertexSource[adjacentVertex] = vertex;
                    queue.addLast(adjacentVertex);
                }
                /*
                 * A cycle exists if:
                 * - A vertex adjacent to the current vertex is being 
                 *   visited a second time.
                 * - The adjacent vertex is not the source vertex of the 
                 *   current vertex. This is possible as the input graph 
                 *   to this fuction is an undirected graph.
                 */
                else if(adjacentVertex != vertexSource[vertex])
                {
                    System.out.println("Vertex source: "+ vertexSource[vertex]);
                    System.out.println("Vertex: "+vertex+" Adjacent: "+adjacentVertex);
                    found = true;
                }
            }
        }

        System.out.println("The BFS traversal is: "+bfsResult);

        return found;
    }
    /*Approach 1: Produce all cycles in a graph */
    /*
     * DFS
     * Each adjacent vertex from a source vertex could have been visted previously.
     * Recursive solution:
     * - Base case: 
     *   - If the source vertex has been visited before
     *   - Add the source vertex to path and add the path
     *     to the result.
     *   - Return.
     * - Add the current source vertex to path
     * - Update the current vertex as visted.
     * - Recursive case:
     *   - Iterate through the adjacent vertices of source.
     *   - If the previous source vertex, i.e., the previous 
     *     call’s source vertex is same as an adjacent vertex 
     *     of this call’s source vertex, then no recursive call 
     *     is made, as the current recursive call adjacent vertex 
     *     and the previous call’s source vertex are the same vertex
     *   - The recursive calls are made by:
     *   - The adjacent node is the new source node
     *   - To ensure a vertex is not revisited in a path:
     *     - A boolean visited array is passed to not make
     *       recursive calls using adjacent vertices that 
     *       have already been visited.
     */

    static void cyclesInGraphDFSRecursive(ArrayList<ArrayList<Integer>> adjList, int src, int prevSource, boolean[] visited, ArrayList<Integer> path, ArrayList<ArrayList<Integer>> paths)
    {
        /*
         * Base case:
         * - If the source vertex has been visited before
         * - Add the source vertex to path and add the path
         *   to the result.
         * - Return.
         */
        if(visited[src] == true)
        {
            ArrayList<Integer> foundPath = new ArrayList<>();
            foundPath.addAll(path);
            foundPath.add(src);
            paths.add(foundPath);
            System.out.println(foundPath);
            return;
        }
        /*
         * - Add the current source to path.
         * - Set the current source as visited.
         */
        path.add(src);
        visited[src] = true;

        /*Iterate through the adjacent vertices of source. */
        for(int adjacentVertex : adjList.get(src))
        {
            /*Create a clone visited array to pass to each recursive call. */
            boolean[] visitedVertex = visited.clone();

            /*
             * If the previous source vertex, i.e., the previous call’s source 
             * vertex is same as an adjacent vertex of this call’s source vertex, 
             * then no recursive call is made, as the current recursive call 
             * adjacent vertex and the previous call’s source vertex are the 
             * same vertex
             */
            if(adjacentVertex != prevSource)
            {
                /*Creating a new copy of the current path to pass to the recursive call. */
                ArrayList<Integer> currentPath = new ArrayList<>();
                currentPath.addAll(path);

                /*
                 * adjacentVertex: The source vertex for the next recursive call.
                 * src: The source of the current recursive call and the previous 
                 *      source of the next recursive call.
                 */
                cyclesInGraphDFSRecursive(adjList, adjacentVertex, src, visitedVertex, currentPath, paths);
            }
        }
    }

    public static void main(String[] args) {
        int vertices = 5;
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

        // Set vertices to 5 for this test case
        addEdgeUndirected(adjList, 0, 1);
        addEdgeUndirected(adjList, 1, 2);
        addEdgeUndirected(adjList, 2, 3);
        addEdgeUndirected(adjList, 3, 0);
        addEdgeUndirected(adjList, 3, 4);


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

        boolean cyclePresence = detectCyclesBFSUnDirectedGraph(adjList, source);

        System.out.println("Cycle present: "+cyclePresence);

        /*Approach 1: Recursive approach to find all the paths. */
        ArrayList<ArrayList<Integer>> paths = new ArrayList<>();
        ArrayList<Integer> path = new ArrayList<>();
        // path.add(source);
        boolean[] visited = new boolean[adjList.size()];
        for(int i=0; i<visited.length; i++)
        {
            visited[i] = false;
        }
        // visited[source] = true;

        System.out.println("Total unique cycles: ");

        cyclesInGraphDFSRecursive(adjList, source, -1, visited, path, paths);

        System.out.println("Total number of unique cycles: "+paths.size());
    }
}