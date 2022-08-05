package DSA_Graph;

import java.util.ArrayList;

public class P185_DetectCycleDirectedGraph {
    /* Representing a graph using an ArrayList adjacency list. */
    /*
     * A directed edge is created by storing the second vertex 
     * in the ArrayList storing the first vertex's adjacent vertices.
     */
    static void addEdgeDirected(ArrayList<ArrayList<Integer>> adjList, int firstVertex, int secondVertex)
    {
        if(adjList.get(secondVertex).contains(firstVertex))
            System.out.println("A directed edge from "+secondVertex+" to "+firstVertex+" already exists.");
        else if(!adjList.get(firstVertex).contains(secondVertex))
            adjList.get(firstVertex).add(secondVertex);
    }

    static void printDirected(ArrayList<ArrayList<Integer>> adjList)
    {
        System.out.println("\nThe undirected graph represented as an adjacency list: ");
        /*
         * A vertex is represented by an index of the adjacency list.
         * The adjacent vertices of the vertex are stored in the ArrayList 
         * at that index.
         */
        for(int i = 0; i < adjList.size(); i++)
        {
            if(adjList.get(i).isEmpty())
                System.out.println("Vertex: "+i+", has an edge towards: NIL");
            else
                System.out.println("Vertex: "+i+", has an edge towards: "+adjList.get(i));
        }
    }

    /* Approach 1: Produce all cycles in a graph. */

    /*
     * Wrapper function:
     * - A call to the recursive function is made from 
     *   all the vertices.
     */
    static void cyclesInGraphRecursiveWrapper(ArrayList<ArrayList<Integer>> adjList, int source)
    {
        // boolean found = false;
        
        ArrayList<ArrayList<Integer>> paths = new ArrayList<>();
        boolean[] visited = new boolean[adjList.size()];
        for(int i=0; i<visited.length; i++)
        {
            visited[i] = false;
        }

        for(int i=source; i<adjList.size(); i++)
        {
            boolean[] vertexVisited = visited.clone();
            ArrayList<Integer> path = new ArrayList<>();

            cyclesInGraphRecursive(adjList, i, vertexVisited, path, paths);

            // found = cyclesInGraphRecursive(adjList, source, visited, path, paths);
            // if(found)
            //     return found;
            
        }
        System.out.println("Total number of unique cycles: "+paths.size());

        // return found;
    }

    /*
     * Each adjacent vertex from a source vertex could have been visted previously.
     * Recursive solution:
     * - Base case: 
     *   - If the source vertex has been visited before
     *   - Add the source vertex to path and add the path
     *     to the result.
     * - A path is not added if it has already been traversed from 
     *   a different source vertex from the wrapper function call. 
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
    static void cyclesInGraphRecursive(ArrayList<ArrayList<Integer>> adjList, int src, boolean[] visited, ArrayList<Integer> path, ArrayList<ArrayList<Integer>> paths)
    {
        // boolean found = false;
        /*
         * Base case:
         * - If the source vertex has been visited before
         * - Add the source vertex to path and add the path
         *   to the result.
         * - A path is not added if it has already been traversed from 
         *   a different source vertex from the wrapper function call. 
         * - Return.
         */
        if(visited[src] == true)
        {
            ArrayList<Integer> foundPath = new ArrayList<>();
            foundPath.addAll(path);
            foundPath.add(src);
            /*
             * A path is not added if it has already been traversed from 
             * a different source vertex from the wrapper function call. 
             */
            for(ArrayList<Integer> cyclicPath : paths)
            {
                /*
                 * A path already traversed is same as the current 
                 * path if:
                 * - All the element in a paths are same
                 * - The size of the paths are the same
                 */
                if(cyclicPath.containsAll(foundPath) && foundPath.size() == cyclicPath.size())
                {
                    return;
                }
            }
            paths.add(foundPath);
            System.out.println("Source: "+foundPath.get(0));
            System.out.println(foundPath);
            return;
            // return true;
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
            /*Creating a new copy of the current path to pass to the recursive call. */
            ArrayList<Integer> currentPath = new ArrayList<>();
            currentPath.addAll(path);

            /*
             * adjacentVertex: The source vertex for the next recursive call.
             * src: The source of the current recursive call and the previous 
             *      source of the next recursive call.
             */
            cyclesInGraphRecursive(adjList, adjacentVertex, visitedVertex, currentPath, paths);
            // found = cyclesInGraphRecursive(adjList, adjacentVertex, visitedVertex, currentPath, paths);
            // if(found)
            //     return found;
        }
        // return found;
    }

    /*A simpler recursive solution: */
    /*
     * The wrapper function:
     * - Interate through all the vertices as the first 
     *   vertex the cyclic path.
     * - If an adjacent vertex of a vertex in path again 
     *   encounters a vertex, then, a cycle exists.
     * - A cycle in a directed graph could start from 
     *   any vertex and all unvisited vertices need to be 
     *   checked individually as any vertex may not have 
     *   a path towards to section of the graph which may 
     *   remain unchecked.
     * - A call to the recursive function is made with: 
     *   - The adjacency list
     *   - A vertex as a start vertex of the path.
     *   - visited: A boolean array to record if a vertex has been 
     *     visited in any recursive call with any vertex as 
     *     the start vertex. No call to the recursive function 
     *     is made with a vertex as the start symbol that has 
     *     already been visited before.
     *   - recStack: A boolean array to record if a vertex has been visited 
     *     in the current path before. If a vertex is revisited, 
     *     then, a cycle exists.
     * - If a call to the recursive function returns true a cycle has been 
     *   found and the wrapper function returns true.
     * 
     * Recursive function:
     * - The vertex is set as visited in both visited and recStack arrays.
     * - Iterate through the adjacent vertices of a vertex.
     * - If an adjacent vertex has not been visited before and a recursive call 
     *   with the adjacent vertex returns true, then:
     *   return true.
     * - Else If an adjacent vertex is visited for the second time in the path:
     *   return true
     * If the recursive calls do not return true:
     * - recStack is set to false for the current source at return time. 
     */
    static boolean dfs(ArrayList<ArrayList<Integer>> adj, int source, boolean[] visited, boolean[] recStack) 
    {
        visited[source] = true;
        recStack[source] = true;
        
        for(int adjacentVertex : adj.get(source)) 
        {
            /*
             * If:
             * - The current adjacent vertex has been visited the first time in any 
             *   recursive call.
             * - The recursive call to the current call gives a true value, i.e., a cycle
             *   exists and the current adjacent vertex is a part of it.
             */
            if(visited[adjacentVertex] == false && dfs(adj, adjacentVertex, visited, recStack) == true) {
                return true;
            }
            /*If the current adjacent vertex is already part of the current path. */
            else if(recStack[adjacentVertex]) {
                return true;
            }
        }
        /*
         * If the fuction does not return true in the loop, 
         * then that means no cycle exists of which the current source node 
         * is a part of.
         * Thus, at return time this vertex is set as not visited for the recursive calls 
         * of the next vertex as the start vertex of a path, executed from the wrapper 
         * function.
         */
        recStack[source] = false;
        return false;
    }

    static boolean dfsCall(ArrayList<ArrayList<Integer>> adj, int v) 
    {
        boolean visited[] = new boolean[v+1];
        for(int i = 1; i < v+1; i++) {
            visited[i] = false;
        }

        boolean recursionStack[] = new boolean[v+1];
        for(int i = 1; i < v+1; i++) {
            recursionStack[i] = false;
        }

        for(int i = 0; i < v; i++){
            if(visited[i] == false) {
                if(dfs(adj, i, visited, recursionStack)) {
                    return true;
                }
            }
        }
        return false;
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
        addEdgeDirected(adjList, 0, 1);
        addEdgeDirected(adjList, 1, 2);
        addEdgeDirected(adjList, 2, 3);
        addEdgeDirected(adjList, 3, 0);
        addEdgeDirected(adjList, 3, 4);


        // Set vertices to 7 for this test case
        // addEdgeDirected(adjList, 0, 1);
        // addEdgeDirected(adjList, 1, 2);
        // addEdgeDirected(adjList, 1, 3);
        // addEdgeDirected(adjList, 2, 4);
        // addEdgeDirected(adjList, 2, 5);
        // addEdgeDirected(adjList, 3, 5);
        // addEdgeDirected(adjList, 4, 5);
        // addEdgeDirected(adjList, 4, 6);
        // addEdgeDirected(adjList, 5, 6);

        printDirected(adjList);

        /*Approach 1: Recursive approach to find all the paths. */
        
        // visited[source] = true;

        System.out.println("Total unique cycles: ");
        cyclesInGraphRecursiveWrapper(adjList, source);

        // System.out.println("Cycle(s) present: "+cyclesInGraphRecursiveWrapper(adjList, source));

    }
}
