/**
 * Created by talz on 27/12/13.
 */
public class BayesianNodeChem extends BayesianNode {
    private BayesianNodeReported child;

    public BayesianNodeChem(Vertex v) {
        super(v);
    }

    public void setChild(BayesianNodeReported child) {
        this.child = child;
    }

    public BayesianNodeReported getChild() {
        return child;
    }

    @Override
    public String toString() {
        return "" + getVertex().getIndex() + 'c';
    }
}
