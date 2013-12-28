import java.util.ArrayList;

/**
 * Created by talz on 27/12/13.
 */
public class BayesianNodeArmy extends BayesianNode {

    private ArrayList<BayesianNodeTerrorists> children;


    public BayesianNodeArmy(Vertex v) {
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
        return "" + getVertex().getIndex() + 'a';
    }
}
