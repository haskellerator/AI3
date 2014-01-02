import java.io.IOException;
import java.util.ArrayList;

public class BayesianNetwork {

    private ArrayList<BayesianNodeVertexArmy> armyNodes;
    private ArrayList<BayesianNodeVertexChem> chemNodes;
    private ArrayList<BayesianNodeVertexReported> reportedNodes;
    private ArrayList<BayesianNodeTerrorists> terroristNodes;


    public BayesianNetwork(Graph g) {

        this.armyNodes = new ArrayList<BayesianNodeVertexArmy>();
        this.chemNodes = new ArrayList<BayesianNodeVertexChem>();
        this.reportedNodes = new ArrayList<BayesianNodeVertexReported>();
        this.terroristNodes = new ArrayList<BayesianNodeTerrorists>();

        for (Vertex v : g.getVertices()) {
            BayesianNodeVertexChem chemNode = new BayesianNodeVertexChem(v);
            BayesianNodeVertexReported reportedNode = new BayesianNodeVertexReported(v);

            chemNode.setChild(reportedNode);
            reportedNode.setParent(chemNode);

            this.chemNodes.add(chemNode);
            this.reportedNodes.add(reportedNode);
            this.armyNodes.add(new BayesianNodeVertexArmy(v));
        }

        for (Edge e : g.getEdges()) {
            Vertex v1 = e.getFirst(), v2 = e.getSecond();

            BayesianNodeVertexArmy armyParent1 = this.armyNodes.get(v1.getIndex() - 1),
                    armyParent2 = this.armyNodes.get(v2.getIndex() - 1);

            BayesianNodeVertexReported reportedParent1 = this.reportedNodes.get(v1.getIndex() - 1),
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
        for (BayesianNodeVertexArmy a : armyNodes) {
            System.out.println(a.toString());


        }
        System.out.println('\n');
        for (BayesianNodeVertexChem c : chemNodes) {
            System.out.println(c.toString());
        }

        System.out.println('\n');
        for (BayesianNodeVertexReported r : reportedNodes) {
            System.out.println(r.toString());
        }

        for (BayesianNodeTerrorists t : terroristNodes) {
            System.out.println(t.toString());
        }
    }

    public void ProbabilisticReasoning() throws IOException {
        System.out.println("~~~ What would you like to do?\n");
        System.out.println("1. probability that one of the edges contain terrorists.");
        System.out.println("2. probability that all the edges contains terrorists.");
        System.out.println("3. probability that a certain path (set of edges) is free from terrorists.");
        System.out.println("4. bonus!!!");
        String line = Utility.br.readLine();

        if (line.compareTo("1") == 0)
            OneEdgeProb();
        else if (line.compareTo("2") == 0) {
            //AllEdgesProb();
        } else if (line.compareTo("3") == 0) {
            //PathProb();
        } else if (line.compareTo("4") == 0) {
            //BonusProb();
        } else {
            System.out.println("unrecognized operation, retry...");
            ProbabilisticReasoning();
        }
    }

    private void OneEdgeProb() throws IOException {
        System.out.println("please enter the bote sides of the edge");
        System.out.println("for exsaple if the edge is like 2-------3 then enter 2 3");
        String line = Utility.br.readLine();
        int f = Character.getNumericValue(line.charAt(0)) - 1;   /// the -1 is for the the get in the array list of the vertexs
        int s = Character.getNumericValue(line.charAt(2)) - 1;
        BayesianNodeVertexReported first_chem = reportedNodes.get(f);
        BayesianNodeVertexReported second_chem = reportedNodes.get(s);
        BayesianNodeVertexArmy first_Army = armyNodes.get(f);
        BayesianNodeVertexArmy second_Army = armyNodes.get(f);
        // now we need to get the edge node to do the calc
        BayesianNodeTerrorists node = getTerrNode(first_chem, second_chem, first_Army, second_Army);
        // now we need to calc
        // get the boolean evidences for the table
        int c1 = first_chem.getVertex().getReportedChem() ? 1 : 0;
        int c2 = second_chem.getVertex().getReportedChem() ? 1 : 0;
        int a1 = first_Army.getVertex().getReportedArmy();
        int a2 = second_Army.getVertex().getReportedArmy();

        // convert the int values to boolean by the flags
        //Boolean[] ar = get_reported_from_int_to_bool(a1,a2,c1,c2);

        // get the pr value from the table
        //double pr = node.getProbability(ar[0], ar[1], ar[2], ar[3]);

        // print the pr???
        //System.out.println("the pr is..  " + pr);

    }


    private BayesianNodeTerrorists getTerrNode(BayesianNodeVertexReported first_chem,
                                               BayesianNodeVertexReported second_chem, BayesianNodeVertexArmy first_Army,
                                               BayesianNodeVertexArmy second_Army) {
        // TODO Auto-generated method stub
        //need to get the right node from this.terroristNodes
        //need to do compare???
        return null;
    }

        /*private Boolean[] get_reported_from_int_to_bool(int a1, int a2, int c1, int c2){
            Boolean[] ans = new Boolean[3];
            if (a1 == 0)
            ans[0] = true;
        else
        if (a1 == 1)
            ans[0] = false;
        else ...
        //////////////////////////////////
        if (a2 == 0)
            ans[1] = true;
        else
        if (a2 == 1)
            ans[1] = false;
        else ...
        ////////////////////////////////
        if (c1 == 0)
            ans[2] = true;
        else
        if (c1 == 1)
            ans[2] = false;
        else ...
        //////////////////////////
        if (c2 == 0)
            ans[3] = true;
        else
        if (c2 == 1)
            ans[3] = false;
        else ...


        return ans;
    }*/


}



