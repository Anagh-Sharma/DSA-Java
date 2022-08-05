package DSA_Graph;

import java.util.ArrayList;
import java.util.LinkedList;

public class P182_CountIslandGraph_DFS {
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
     * Pre-Order DFS traversal
     * - Push vertices on stack, pop vertices and print them, 
     *   push their linked vertice and so on.
     * - Initialize stack with the source vertex.
     * - Iterate until:
     *   - The stack is empty.
     *   - The size of the output containing traversal is not equal to the size of the input 
     *     containing the adjacency list.
     *     The algorithm will go into an infinite loop if this condition is not set as the the 
     *     the adjacent vertices will keep getting added and the algorithm will keep traversing 
     *     the graph.
     * - Interate over the size of the elements on the stack.
     * - Pop and store the element.
     * - If the vertex has been visted:
     *   Skip and iterate to the next vertex.
     * - Else:
     *   - Print it.
     *   - Record the vertex as visited.
     *   - Push all the elements linked to the this vertex.
     */
    static ArrayList<Integer> dfsTraversal(ArrayList<ArrayList<Integer>> adjList, boolean[] visited, int source) 
    {
        LinkedList<Integer> stack = new LinkedList<>();

        ArrayList<Integer> dfsResult = new ArrayList<>();
        stack.addLast(source);


        while(!stack.isEmpty() && dfsResult.size() != adjList.size()) 
        {
            /*The first element is root for the first iteration, then left, then right nodes. */
            int adjacentVertex = stack.removeLast();

            if(visited[adjacentVertex] == false)
            {
                visited[adjacentVertex] = true;
                dfsResult.add(adjacentVertex);
                for(int i = adjList.get(adjacentVertex).size() - 1; i>=0; i--)
                {
                    stack.addLast(adjList.get(adjacentVertex).get(i));
                }
            }
        }

        return dfsResult;
    }

    /*
     * The wrapper function does the following:
     * - Create a boolean array that records if 
     *   a vertex has been visited or not.
     * - Iterate over the length of the adjacency 
     *   list.
     * - If a vertex has not been visited:
     *   - Call the DFS function with the vertex 
     *     as the source element and the reference 
     *     of the boolean array which the DFS function 
     *     updates when it visits a vertex.
     */
    static int disjointGraphIslands_DFS(ArrayList<ArrayList<Integer>> adjList)
    {
        ArrayList<Integer> dfsResult = new ArrayList<>();
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
                dfsResult.addAll(dfsTraversal(adjList, visited, i));
                islands++;
            }
        }

        System.out.println("The DFS traversal is: "+dfsResult);
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
        addEdgeUndirected(adjList, 1, 3);
        addEdgeUndirected(adjList, 3, 0);
        addEdgeUndirected(adjList, 1, 2);

        /*The second sub-graph. */
        addEdgeUndirected(adjList, 4, 5);
        addEdgeUndirected(adjList, 4, 6);
        addEdgeUndirected(adjList, 5, 6);

        printUndirected(adjList);

        System.out.println("The number of islands are: "+disjointGraphIslands_DFS(adjList));
    }
}
