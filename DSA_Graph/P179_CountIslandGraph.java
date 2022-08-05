package DSA_Graph;

import java.util.ArrayList;
import java.util.LinkedList;

public class P179_CountIslandGraph {

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

    /* Method for BFS traversal of a joint graph*/
    static ArrayList<Integer> bfsTraversal(ArrayList<ArrayList<Integer>> adjList, boolean[] visited, int source)
    {
        LinkedList<Integer> queue = new LinkedList<>();
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

    /*
     * - Traverse the disjoint graph and increment a counter 
     *   whenever the new island is traversed, i.e., the source 
     *   vertex needs to be updated. 
     * - The source vertex of a non-disjoint graph will only 
     *   need to be initialized once.
     * 
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
    static int disjointGraphIslands(ArrayList<ArrayList<Integer>> adjList)
    {
        ArrayList<Integer> bfsResult = new ArrayList<>();
        int islands = 0;

        boolean[] visited = new boolean[adjList.size()];
        for(int i=0; i<visited.length; i++)
        {
            visited[i] = false;
        }

        for(int i=0; i<adjList.size(); i++)
        {
            if(!visited[i])
            {
                bfsResult.addAll(bfsTraversal(adjList, visited, i));
                islands++;
            }
        }

        System.out.println("The BFS traversal is: "+bfsResult);

        return islands;
    }


    public static void main(String[] args) {
        int vertices = 7;

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

        System.out.println("The number of islands are: "+disjointGraphIslands(adjList));

    }
}
