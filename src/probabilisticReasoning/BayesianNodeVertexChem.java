/**
 * Created by talz on 27/12/13.
 */
public class BayesianNodeVertexChem extends BayesianNodeVertex {
    private BayesianNodeVertexReported child;

    public BayesianNodeVertexChem(Vertex v) {
        super(v);
    }

    public void setChild(BayesianNodeVertexReported child) {
        this.child = child;
    }

    public BayesianNodeVertexReported getChild() {
        return child;
    }

    @Override
    public String toString() {
        return "~~~ VERTEX " + vertex.getIndex() + ": chem\n~~~ P(chem) = " + getProbability() +
                "\n~~~ P(not chem) = " + Probability.not(getProbability()) + '\n';
    }

    public double getProbability() {
        return Probability.Pchem;
    }
}
