package DSA_Graph;

import java.util.ArrayList;
import java.util.LinkedList;

public class P178_GraphBFS_Disjoint {

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
     * - Queue vertices, dequeue vertices and print them, enqueue their linked vertice 
     *   if they have not been visited before and so on.
     * - Initialize queue with the source vertex and set the source as visited.
     * - Iterate until:
     *   - The length of the BFS traversal is not equal to the length of the adjacency list.
     * - Dequeue and store the first element.
     * - If an adjacent vertex has been visted:
     *   Skip and iterate to the next adjacent vertex.
     * - Else:
     *   - Record the vertex as visited.
     *   - Enqueue the adjacent vertex.
     * When after a vertex the graph becomes disjoint, 
     * i.e., the last vertex had no linked vertices that 
     * were not visited, then:
     * Iterate over the length of the adjacency list whose 
     * every index denotes a vertex, and the first vertex 
     * or index value that is not visited is inserted into 
     * the queue and thus the queue is reinitialized.     
     */
    /*Note: This method works for both joint and disjoint graphs. */
    static ArrayList<Integer> bfsTraversalDisjoint(ArrayList<ArrayList<Integer>> adjList, int source)
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

        while(bfsResult.size() != adjList.size())
        {
            /*
             * When after a vertex the graph becomes disjoint, 
             * i.e., the last vertex had no linked vertices that 
             * were not visited, then:
             * Iterate over the length of the adjacency list whose 
             * every index denotes a vertex, and the first vertex 
             * or index value that is not visited is inserted into 
             * the queue and thus the queue is reinitialized.
             */
            if(queue.isEmpty())
            {
                for(int i=0; i<adjList.size(); i++)
                {
                    if(visited[i] == false)
                        queue.add(i);
                }
            }

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

    /* Approach 2: Using method for BFS traversal of a joint graph and wrapper function. */

    /* Method for BFS traversal of a joint graph*/
    static ArrayList<Integer> bfsTraversal(ArrayList<ArrayList<Integer>> adjList, boolean[] visited, int source)
    {
        LinkedList<Integer> queue = new LinkedList<>();

        ArrayList<Integer> bfsResult = new ArrayList<>();

        queue.addLast(source);
        visited[source] = true;

        while(!queue.isEmpty())
        {
            int adjacentVertex = queue.removeFirst();
            bfsResult.add(adjacentVertex);
            for(int vertex : adjList.get(adjacentVertex))
            {
                if(visited[vertex] == false)
                {
                    visited[vertex] = true;
                    queue.addLast(vertex);
                }
            }
        }

        return bfsResult;
    }

    /*
     * The wrapper function does the following:
     * - Create a boolean array that records if 
     *   a vertex has been visited or not.
     * - Iterate over the length of the adjacency 
     *   list.
     * - If a vertex has not been visited:
     *   - Call the BFS function with the vertex 
     *     as the source element and the reference 
     *     of the boolean array which the BFS function 
     *     updates when it visits a vertex.
     */
    static ArrayList<Integer> bfsDisjoint_approach2(ArrayList<ArrayList<Integer>> adjList)
    {
        ArrayList<Integer> bfsResult = new ArrayList<>();

        boolean[] visited = new boolean[adjList.size()];
        for(int i=0; i<visited.length; i++)
        {
            visited[i] = false;
        }

        for(int i=0; i<adjList.size(); i++)
        {
            if(!visited[i])
                bfsResult.addAll(bfsTraversal(adjList, visited, i));
        }


        return bfsResult;
    }


    public static void main(String[] args) {
        int vertices = 7;
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

        /*The first sub-graph. */
        addEdgeUndirected(adjList, 0, 1);
        addEdgeUndirected(adjList, 1, 2);
        addEdgeUndirected(adjList, 2, 3);
        addEdgeUndirected(adjList, 3, 0);

        /*The second sub-graph. */
        addEdgeUndirected(adjList, 4, 5);
        addEdgeUndirected(adjList, 4, 6);
        addEdgeUndirected(adjList, 5, 6);

        printUndirected(adjList);

        System.out.println("The BFS traversal is: "+bfsTraversalDisjoint(adjList, source));

        System.out.println("The BFS traversal is: "+bfsDisjoint_approach2(adjList));

    }
}
