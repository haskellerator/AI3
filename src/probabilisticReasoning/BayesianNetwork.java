import java.util.ArrayList;

public class BayesianNetwork {

    private ArrayList<BayesianNodeArmy> armyNodes;
    private ArrayList<BayesianNodeChem> chemNodes;
    private ArrayList<BayesianNodeReported> reportedNodes;
    private ArrayList<BayesianNodeTerrorists> terroristNodes;


    public BayesianNetwork(Graph g) {

        this.armyNodes = new ArrayList<BayesianNodeArmy>();
        this.chemNodes = new ArrayList<BayesianNodeChem>();
        this.reportedNodes = new ArrayList<BayesianNodeReported>();
        this.terroristNodes = new ArrayList<BayesianNodeTerrorists>();

        for (Vertex v : g.getVertices()) {
            BayesianNodeChem chemNode = new BayesianNodeChem(v);
            BayesianNodeReported reportedNode = new BayesianNodeReported(v);

            chemNode.setChild(reportedNode);
            reportedNode.setParent(chemNode);

            this.chemNodes.add(chemNode);
            this.reportedNodes.add(reportedNode);
            this.armyNodes.add(new BayesianNodeArmy(v));
        }

        for (Edge e : g.getEdges()) {
            Vertex v1 = e.getFirst(), v2 = e.getSecond();

            BayesianNodeArmy armyParent1 = this.armyNodes.get(v1.getIndex() - 1),
                    armyParent2 = this.armyNodes.get(v2.getIndex() - 1);

            BayesianNodeReported reportedParent1 = this.reportedNodes.get(v1.getIndex() - 1),
                    reportedParent2 = this.reportedNodes.get(v2.getIndex() - 1);

            BayesianNodeTerrorists terroristsNode = new BayesianNodeTerrorists(e, armyParent1, reportedParent1, armyParent2, reportedParent2);

            this.terroristNodes.add(terroristsNode);

            armyParent1.addChild(terroristsNode);
            armyParent2.addChild(terroristsNode);
            reportedParent1.addChild(terroristsNode);
            reportedParent2.addChild(terroristsNode);
        }
    }

    // function for debugging purpose - to check all the pointers
    public void show() {
        for (BayesianNodeArmy a : armyNodes) {
            System.out.print(a.toString() + " -> ");
            String str = "";
            for (BayesianNodeTerrorists t : a.getChildren()) {
                str += t.toString() + ", ";
            }
            System.out.println(str);
        }
        System.out.println('\n');
        for (BayesianNodeChem c : chemNodes) {
            System.out.println(c.toString() + " -> " + c.getChild().toString());
        }

        System.out.println('\n');
        for (BayesianNodeReported r : reportedNodes) {
            System.out.print(r.getParent().toString() + " -> ");
            System.out.print(r.toString() + " -> ");
            String str = "";
            for (BayesianNodeTerrorists t : r.getChildren()) {
                str += t.toString() + ", ";
            }
            System.out.println(str + '\n');
        }
    }
}



