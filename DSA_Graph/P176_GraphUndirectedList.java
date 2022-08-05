package DSA_Graph;

import java.util.ArrayList;
import java.util.HashMap;

/* Approach 1: */
/* Representing a graph using a HashMap adjacency list. */
class GraphList
{
    /*
     * Representing the vertices as strings and 
     * storing the corresponding integer hash value 
     * of each vertex to enable simpler adjacency 
     * list representing the graph.
     */
    HashMap<String, Integer> vertexHash = new HashMap<>();
    
    int vertices;
    HashMap<Integer, ArrayList<String>> adjList = new HashMap<>();
    GraphList(int vertices)
    {
        /*Initializing the size of the adjacency list with the number of vertices */
        this.vertices = vertices;

        /*
         * Inserting empty ArrayLists in the HashMap 
         * adjacency list corresponding to each vertex 
         * to record their adjacent vertices.
         */
        for(int i=0; i<vertices; i++)
        {
            adjList.put(i, new ArrayList<String>());
        }
    }

    /*Creating a vertex by storing the String value in the vertexHash hashmap. */
    int vertexCounter = -1;
    void createVertex(String vertex)
    {
        if(vertexHash.containsKey(vertex))
            return;
        else
            vertexHash.put(vertex, ++vertexCounter);
    }

    boolean edgeExists(int keyVertex, String valueVertex)
    {
        if(adjList.get(keyVertex).contains(valueVertex))
            return true;
        else
            return false;
    }

    /*
     * An edge is created by storing each of the vertex of the pair 
     * of vertices representing the edge in the ArrayList storing 
     * the other’s adjacent vertices.
     */
    void addEdgeUndirected(String firstVertex, String secondVertex)
    {
        /*Getting the integer has of the string vertex value. */
        int firstVertexInteger = vertexHash.get(firstVertex);
        int secondVertexInteger = vertexHash.get(secondVertex);

        /*
         * Storing one string vertex value to the other vertex's 
         * list of adjacent vertices by storing the string in the 
         * HashMap's ArrayList of strings at the key equal to the 
         * other vertex's integer hash value.
         */
        if(!edgeExists(firstVertexInteger, secondVertex))
            adjList.get(firstVertexInteger).add(secondVertex);
        
        if(!edgeExists(secondVertexInteger, firstVertex))
            adjList.get(secondVertexInteger).add(firstVertex);
    }

    void printUndirected()
    {
        System.out.println("\nThe undirected graph represented as an adjacency list: ");
        /*
         * A vertex is represented by an index of the adjacency list.
         * The adjacent vertices of the vertex are stored in the ArrayList 
         * at that index.
         */
        for(String vertex : vertexHash.keySet())
        {
            int vertexInteger = vertexHash.get(vertex);

            System.out.println("Vertex: "+vertex+", shares an edge with: "+adjList.get(vertexInteger));
        }
    }
}

/* Approach 2: */

/*
 * A class whose objects store a vertex's adjacent vertices and
 * the corresponding weight of this pairing.
 */
class Edges
{
    HashMap<String, Integer> edges = new HashMap<>();
}

class Graph
{
    /*
     * The vertices hash map stores:
     * - Key    :   A string denoting a vertex in the graph
     * - Value  :   An object of class Edges. The object
     *              contains a hashmap memeber that maps 
     *              the vertices adjacent to the key with 
     *              the cost of the corresponding edge.
     */
    HashMap<String, Edges> vertices = new HashMap<>();

    /*
     * A vertex is added to the graph by creating an 
     * entry in the vertices hashmap by mapping the 
     * string denoting a vertex with an instance of 
     * class Edge.
     */
    void addVertex(String vertex)
    {
        Edges vertexEdges = new Edges();
        if(!vertices.containsKey(vertex))
            vertices.put(vertex, vertexEdges);
        else
            System.out.println("The vertex "+vertex+" already exists.");
    }

    /*
     * An edge exists if:
     * - The first vertex is present in the vertices hash map.
     * - The hash map member of Edge object mapped to the first 
     *   vertex contains the second vertex as a key.
     */
    boolean edgeExists(String firstVertex, String secondVertex)
    {
        if(vertices.get(firstVertex) != null && vertices.get(firstVertex).edges.containsKey(secondVertex))
            return true;
        else
            return false;
    }

    /*
     * An edge is added by:
     * - Accessing the hash map member of the Edge object 
     *   mapped to the first vertex and storing the information 
     *   of the edge by mapping the second vertex to the weight 
     *   of the resultant edge.
     * - Vice versa for the second and first vertices.
     */
    void addEdge(String firstVertex, String secondVertex, int weight)
    {
        if(!edgeExists(firstVertex, secondVertex))
            vertices.get(firstVertex).edges.put(secondVertex, weight);
        else
            System.out.println("The edge "+firstVertex+" -> "+secondVertex+" already exists.");
        if(!edgeExists(secondVertex, firstVertex))
            vertices.get(secondVertex).edges.put(firstVertex, weight);
        else
            System.out.println("The edge "+secondVertex+" -> "+firstVertex+" already exists.");
    }

    void printUndirected()
    {
        System.out.println("\nThe undirected graph represented using edges from vertices: ");
        /*
         * A vertex is represented by an index of the adjacency list.
         * The adjacent vertices of the vertex are stored in the ArrayList 
         * at that index.
         */
        for(String vertex : vertices.keySet())
        {
            System.out.println("\nVertex: "+vertex);
            
            System.out.println("Edge(s): ");
            for(String secondVertex : vertices.get(vertex).edges.keySet())
                System.out.println(vertex+" -> "+secondVertex+" ; Cost: "+ vertices.get(vertex).edges.get(secondVertex));
        }
    }
}


public class P176_GraphUndirectedList {

    /* Approach 3: */
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

    public static void main(String[] args) {
        int vertices = 4;

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

        addEdgeUndirected(adjList, 0, 1);
        addEdgeUndirected(adjList, 0, 2);
        addEdgeUndirected(adjList, 1, 2);
        addEdgeUndirected(adjList, 1, 3);

        printUndirected(adjList);

        /* Representing a graph using a HashMap adjacency list. */

        GraphList graphUndirected = new GraphList(vertices);

        graphUndirected.createVertex("A");
        graphUndirected.createVertex("B");
        graphUndirected.createVertex("C");
        graphUndirected.createVertex("D");

        graphUndirected.addEdgeUndirected("A", "B");
        graphUndirected.addEdgeUndirected("A", "C");
        graphUndirected.addEdgeUndirected("B", "C");
        graphUndirected.addEdgeUndirected("B", "D");

        graphUndirected.printUndirected();

        /* Representing a graph using a HashMap adjacency list. */

        Graph graph = new Graph();

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");

        graph.addEdge("A", "B", 1);
        graph.addEdge("A", "C", 2);
        graph.addEdge("B", "C", 3);
        graph.addEdge("B", "D", 4);

        graph.printUndirected();
    }
}
