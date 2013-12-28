import java.util.ArrayList;

/**
 * Created by talz on 27/12/13.
 */
public class BayesianNodeReported extends BayesianNode {
    private BayesianNodeChem parent;
    private ArrayList<BayesianNodeTerrorists> children;

    public BayesianNodeReported(Vertex v) {
        super(v);
        this.children = new ArrayList<BayesianNodeTerrorists>();
    }

    public void setParent(BayesianNodeChem parent) {
        this.parent = parent;
    }

    public BayesianNodeChem getParent() {
        return parent;
    }

    public ArrayList<BayesianNodeTerrorists> getChildren() {
        return children;
    }

    public void addChild(BayesianNodeTerrorists child) {
        this.children.add(child);
    }

    @Override
    public String toString() {
        return "" + getVertex().getIndex() + 'r';
    }
}
