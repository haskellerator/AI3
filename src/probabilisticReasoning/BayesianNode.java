public abstract class BayesianNode {

    protected Vertex vertex;

    public BayesianNode(Vertex v) {
        this.vertex = v;
    }

    public Vertex getVertex() {
        return vertex;
    }

    public double enumeration() {

        return 0;
    }
}
