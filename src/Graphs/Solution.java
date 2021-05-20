package Graphs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Solution {
    /**
     * Checks if vertex b is reachable from vertex a.
     * @param a Vertex to start from.
     * @param b Vertex to reach.
     * @return true if vertex b is reachable.
     */
    public static boolean solve(VertexV a, VertexV b) {
        if(a.equals(b)){
            return true;
        }
        Queue<VertexV> q = new LinkedList<>();
        Set<VertexV> visited = new HashSet<>();
        q.add(a);
        visited.add(a);
        while(!q.isEmpty()){
            VertexV tmp = q.poll();
            if(tmp.equals(b)){
                return true;
            }
            if(!visited.contains(tmp)){
                visited.add(tmp);
            }
            List<VertexV> neighbours = new ArrayList<>();
            neighbours.addAll(tmp.getNeighbours());
            for(int i=0;i<neighbours.size();i++){
                if(!visited.contains(neighbours.get(i))){
                    q.add(neighbours.get(i));
                }
            }
        }
        return false;
        // TODO
    }
}

class VertexV {

    private int id;

    private Set<VertexV> neighbours;

    public VertexV(int id) {
        this.id = id;
        neighbours = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public void addNeighbour(VertexV v) {
        neighbours.add(v);
    }

    @Override
    public String toString() {
        return "<vertex: " + id + ">";
    }

    public Collection<VertexV> getNeighbours() {
        return new ArrayList<>(this.neighbours);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Vertex && ((VertexV) o).getId() == this.getId();
    }
}

class GraphG {

    private Map<Integer, VertexV> vertices = new HashMap<>();

    /**
     * Creates a new graph with n vertices.
     *
     * @param n Amount of vertices in the graph.
     */
    public GraphG(int n) {
        for (int i = 0; i < n; i++) vertices.put(i, new VertexV(i));
    }

    /**
     * Returns the vertex with the given ID.
     *
     * @param id The ID for the vertex to get.
     * @return The vertex with the given ID.
     * @throws IllegalArgumentException if no vertex with the given ID is in the graph.
     */
    public VertexV getVertex(int id) throws IllegalArgumentException {
        if (!vertices.containsKey(id))
            throw new IllegalArgumentException("Vertex not in graph");
        return vertices.get(id);
    }

    public Collection<VertexV> getAllVertices() {
        return new ArrayList<>(this.vertices.values());
    }

    /**
     * Adds an edge between vertex u and v with the given weight.
     *
     * @param u First vertex.
     * @param v Second vertex.
     * @param weight Weight of the edge between a and b.
     */
    public void addEdge(VertexV u, VertexV v) {
        u.addNeighbour(v);
        v.addNeighbour(u);
    }

    /**
     * Adds an edge between the vertices with IDs u and v with the given weight.
     *
     * @param u ID of the first vertex.
     * @param v ID of the second vertex.
     * @param weight Weight of the edge between a and b.
     * @throws IllegalArgumentException if no vertex with the given ID is in the graph.
     */
    public void addEdge(int u, int v) throws IllegalArgumentException {
        addEdge(getVertex(u), getVertex(v));
    }
}