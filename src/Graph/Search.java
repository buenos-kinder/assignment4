import java.util.*;

public abstract class Search<V> {
    protected Vertex<V> source;
    protected Map<Vertex<V>, Vertex<V>> edgeTo; // tracks parent nodes

    public Search(Vertex<V> source) {
        this.source = source;
        this.edgeTo = new HashMap<>();
    }

    public boolean hasPathTo(Vertex<V> dest) {
        return edgeTo.containsKey(dest) || dest.equals(source);
    }

    public Iterable<Vertex<V>> pathTo(Vertex<V> dest) {
        if (!hasPathTo(dest)) return null;
        
        LinkedList<Vertex<V>> path = new LinkedList<>();
        for (Vertex<V> v = dest; v != null; v = edgeTo.get(v)) {
            path.addFirst(v);
        }
        return path;
    }
}