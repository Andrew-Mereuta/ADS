package Graphs;

import java.util.*;

public class KruskalSolution {

    /**
     * Builds a Minimum Spanning Tree (MST) using
     * Kruskal's Algorithm (as learned in class).
     * Nodes are numbered from 0 to n - 1.
     *
     * @param n the amount of nodes in the graph
     * @param edges the edges that comprise the graph
     * @return the list of edges that should be included in the MST
     */
    public static List<Edge> buildMST(int n, List<Edge> edges) {
        if(n==0){
            return new ArrayList<>();
        }
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for(int i=0; i<edges.size();i++){
            pq.add(edges.get(i));
        }
        List<Edge> result = new ArrayList<>();
        UnionFind uf = new UnionFind(n);
        while(!pq.isEmpty()){
            Edge e = pq.poll();
            if(uf.find(e.getFrom()) != uf.find(e.getTo())){
                uf.union(e.getFrom(), e.getTo());
                result.add(e);
            }
        }
        return result;
        // TODO
    }
}

class Edge implements Comparable<Edge> {
    private int from;
    private int to;
    private int cost;

    public Edge(int from, int to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(this.cost, o.cost);
    }

    @Override
    public String toString() {
        return "Edge{" +
                "from=" + from +
                ", to=" + to +
                ", cost=" + cost +
                '}';
    }
}

class UnionFind {

    private int[] parent;

    private int[] rank;

    // Union Find structure implemented with two arrays for Union by Rank
    public UnionFind(int size) {
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) parent[i] = i;
    }

    /**
     * Merge two clusters, if they are not already part of the same cluster.
     *
     * @param i a node in the first cluster
     * @param j a node in the second cluster
     * @return true iff i and j had different clusters.
     */
    boolean union(int i, int j) {
        int parent1 = find(i);
        int parent2 = find(j);
        if (parent2 == parent1)
            return false;
        if (rank[parent1] > rank[parent2]) {
            parent[parent2] = parent1;
        } else if (rank[parent2] > rank[parent1]) {
            parent[parent1] = parent2;
        } else {
            parent[parent2] = parent1;
            rank[parent1]++;
        }
        return true;
    }

    /**
     * NB: this function should also do path compression
     * @param i index of a node
     * @return the root of the subtree containg i.
     */
    int find(int i) {
        int parent = this.parent[i];
        if (i == parent) {
            return i;
        }
        return this.parent[i] = find(parent);
    }

    // Return the rank of the trees
    public int[] getRank() {
        return rank;
    }

    // Return the parent of the trees
    public int[] getParent() {
        return parent;
    }
}