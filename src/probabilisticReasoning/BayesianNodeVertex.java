public abstract class BayesianNodeVertex {

    protected Vertex vertex;

    public BayesianNodeVertex(Vertex v) {
        this.vertex = v;
    }

    public Vertex getVertex() {
        return vertex;
    }

    public double enumeration() {

        return 0;
    }
}
