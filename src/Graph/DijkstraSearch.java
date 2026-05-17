import java.util.*;

public class DijkstraSearch<V> extends Search<V> {
    private Map<Vertex<V>, Double> distTo; // tracks shortest distance to each vertex
    private PriorityQueue<NodeDistancePair<V>> pq;

    public DijkstraSearch(WeightedGraph<V> graph, Vertex<V> source) {
        super(source);
        this.distTo = new HashMap<>();
        this.pq = new PriorityQueue<>(Comparator.comparingDouble(pair -> pair.distance));
        
        // Initialize distances
        for (Vertex<V> v : graph.getVertices()) {
            distTo.put(v, Double.POSITIVE_INFINITY);
        }
        distTo.put(source, 0.0);

        pq.add(new NodeDistancePair<>(source, 0.0));

        while (!pq.isEmpty()) {
            Vertex<V> current = pq.poll().vertex;

            for (Map.Entry<Vertex<V>, Double> entry : current.getAdjacentVertices().entrySet()) {
                Vertex<V> neighbor = entry.getKey();
                double weight = entry.getValue();
                
                relax(current, neighbor, weight);
            }
        }
    }

    private void relax(Vertex<V> source, Vertex<V> dest, double weight) {
        double newDist = distTo.get(source) + weight;
        if (newDist < distTo.get(dest)) {
            distTo.put(dest, newDist);
            edgeTo.put(dest, source);
            
            // Simulating a decrease-key operation by adding the updated node
            pq.add(new NodeDistancePair<>(dest, newDist));
        }
    }

    public double getDistanceTo(Vertex<V> dest) {
        return distTo.getOrDefault(dest, Double.POSITIVE_INFINITY);
    }

    // Helper class to match vertices with weights inside the PriorityQueue
    private static class NodeDistancePair<V> {
        Vertex<V> vertex;
        double distance;

        NodeDistancePair(Vertex<V> vertex, double distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
    }
}