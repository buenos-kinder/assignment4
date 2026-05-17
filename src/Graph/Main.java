public class Main {
    public static void main(String[] args) {
        WeightedGraph<String> graph = new WeightedGraph<>();

        // Create Vertices
        Vertex<String> almaty = new Vertex<>("Almaty");
        Vertex<String> astana = new Vertex<>("Astana");
        Vertex<String> shymkent = new Vertex<>("Shymkent");
        Vertex<String> aktobe = new Vertex<>("Aktobe");

        // Build Graph Connections
        graph.addEdge(almaty, astana, 1200.0);
        graph.addEdge(almaty, shymkent, 700.0);
        graph.addEdge(shymkent, aktobe, 1500.0);
        graph.addEdge(astana, aktobe, 1000.0);

        System.out.println("--- TESTING BFS PATHS FROM ALMATY ---");
        BreadthFirstSearch<String> bfs = new BreadthFirstSearch<>(graph, almaty);
        System.out.println("Path to Aktobe: " + bfs.pathTo(aktobe));

        System.out.println("\n--- TESTING DIJKSTRA PATHS FROM ALMATY ---");
        DijkstraSearch<String> dijkstra = new DijkstraSearch<>(graph, almaty);
        System.out.println("Shortest Route Path to Aktobe: " + dijkstra.pathTo(aktobe));
        System.out.println("Total Cost/Distance to Aktobe: " + dijkstra.getDistanceTo(aktobe));
    }
}