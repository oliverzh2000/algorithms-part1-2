package part2week1lesson;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class BreadthFirstPaths {
    private final Graph graph;
    private final int start;
    private boolean[] marked;
    private int[] edgeTo;

    public BreadthFirstPaths(Graph graph, int start) {
        this.graph = graph;
        this.start = start;
        this.marked = new boolean[graph.V()];
        this.edgeTo = new int[graph.V()];

        BFS(start);
    }

    private void BFS(int start) {
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(start);
        marked[start] = true;
        while (!queue.isEmpty()) {
            int currentVertex = queue.dequeue();
            for (int neighbor : graph.adj(currentVertex)) {
                if (!marked[neighbor]) {
                    edgeTo[neighbor] = currentVertex;
                    marked[currentVertex] = true;
                    queue.enqueue(neighbor);
                }
            }
        }
    }

    boolean hasPathTo(int vertex) {
        return marked[vertex];
    }

    Iterable<Integer> pathTo(int vertex) {
        if (!hasPathTo(vertex)) {
            return null;
        }
        Stack<Integer> path = new Stack<>();
        while (edgeTo[vertex] != start) {
            path.push(vertex);
            vertex = edgeTo[vertex];
        }
        return path;
    }
}
