import java.util.ArrayList;

/**
 * Created by talz on 27/12/13.
 */
public class BayesianNodeVertexArmy extends BayesianNodeVertex {

    private ArrayList<BayesianNodeTerrorists> children;


    public BayesianNodeVertexArmy(Vertex v) {
        super(v);
        this.children = new ArrayList<BayesianNodeTerrorists>();
    }

    public ArrayList<BayesianNodeTerrorists> getChildren() {
        return children;
    }

    public void addChild(BayesianNodeTerrorists child) {
        this.children.add(child);
    }

    @Override
    public String toString() {
        return "~~~ VERTEX " + vertex.getIndex() + ": army\n~~~ P(army) = " + getProbability() +
                "\n~~~ P(not army) = " + Probability.not(getProbability()) + '\n';
    }

    public double getProbability() {
        return Probability.Parmy;
    }
}
