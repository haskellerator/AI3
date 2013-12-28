public abstract class BayesianNode {

    protected Vertex vertex;

    public BayesianNode() {
        this.vertex = null;
    }

    public BayesianNode(Vertex v) {
        this.vertex = v;
    }

    public Vertex getVertex() {
        return vertex;
    }


}
