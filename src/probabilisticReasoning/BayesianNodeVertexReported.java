import java.util.ArrayList;

/**
 * Created by talz on 27/12/13.
 */
public class BayesianNodeVertexReported extends BayesianNodeVertex {
    private BayesianNodeVertexChem parent;
    private ArrayList<BayesianNodeTerrorists> children;


    public BayesianNodeVertexReported(Vertex v) {
        super(v);
        this.children = new ArrayList<BayesianNodeTerrorists>();
    }

    public void setParent(BayesianNodeVertexChem parent) {
        this.parent = parent;
    }

    public BayesianNodeVertexChem getParent() {
        return parent;
    }

    public ArrayList<BayesianNodeTerrorists> getChildren() {
        return children;
    }

    public void addChild(BayesianNodeTerrorists child) {
        this.children.add(child);
    }

    public String toString() {
        return "~~~ VERTEX " + vertex.getIndex() + ": chem\n~~~ P(chem reported | chem) = " + getProbability(true) +
                "\n~~~ P(not chem reported | chem) = " + Probability.not(getProbability(true)) +
                "\n~~~ P(chem reported | not chem) = " + getProbability(false) +
                "\n~~~ P(not chem reported | not chem) = " + Probability.not(getProbability(false)) + '\n';
    }

    private static int getTableIndex(boolean chem) {
        return (chem ? 1 : 0);
    }

    public double getProbability(boolean chem) {
        if (chem) return Probability.Preveal;
        else return 0;
    }
}
