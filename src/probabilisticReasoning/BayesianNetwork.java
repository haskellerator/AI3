import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

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
            System.out.println(a.toString());


        }
        System.out.println('\n');
        for (BayesianNodeChem c : chemNodes) {
            System.out.println(c.toString());
        }

        System.out.println('\n');
        for (BayesianNodeReported r : reportedNodes) {
            System.out.println(r.toString());
        }

        for (BayesianNodeTerrorists t : terroristNodes) {
            System.out.println(t.toString());
        }
    }


    public void ProbabilisticReasoning() throws IOException {
        System.out.println("~~~ What would you like to do?\n");
        System.out.println("	1. probability that one of the edges contain terrorists.");
        System.out.println("	2. probability that all the edges contains terrorists.");
        System.out.println("	3. probability that a certain path (set of edges) is free from terrorists.");
        System.out.println("	4. bonus!!!");
        String line = Utility.br.readLine();

        if (line.compareTo("1") == 0)
            OneEdgeProb();
        else if (line.compareTo("2") == 0)
            AllEdgesProb();
        else if (line.compareTo("3") == 0)
            //PathProb();
            System.out.println("not suported yet");
        else if (line.compareTo("4") == 0)
            //BonusProb();
            System.out.println("not suported yet");
        else {
            System.out.println("unrecognized operation, retry...");
            ProbabilisticReasoning();
        }
    }

    private void OneEdgeProb() throws IOException {
        System.out.println("please enter both sides of the edge");
        System.out.println("for example if the edge is from vertex 2 to vertex 3 (or vice verse) then enter 2 3");
        String line = Utility.br.readLine();
        int f = Character.getNumericValue(line.charAt(0));   /// the -1 is for the the get in the array list of the vertexs
        int s = Character.getNumericValue(line.charAt(2));


        // *******************************************************
        // since i have f, s then i can call to the function of get prob *******************
        // *******************************************************


        BayesianNodeReported first_chem = reportedNodes.get(f - 1);
        System.out.println("---------------------" + first_chem.getVertex().getIndex());
        BayesianNodeReported second_chem = reportedNodes.get(s - 1);
        BayesianNodeArmy first_Army = armyNodes.get(f - 1);
        BayesianNodeArmy second_Army = armyNodes.get(s - 1);

        // get the boolean evidences for the table
        boolean c1 = first_chem.getVertex().getReportedChem();
        boolean c2 = second_chem.getVertex().getReportedChem();
        int flag1 = first_Army.getVertex().getReportedArmy();
        int flag2 = second_Army.getVertex().getReportedArmy();
        double ans = get_prob(f, s, flag1, flag2, c1, c2);

        // print the pr???
        System.out.println("the pr is..  " + ans);

    }


    private void AllEdgesProb() {
        //Double [] arr = new Double [this.terroristNodes.size()];
        int f, s;
        BayesianNodeReported first_chem, second_chem;
        BayesianNodeArmy first_Army, second_Army;
        boolean c1, c2;
        int flag1, flag2;
        double ans;

        for (int i = 0; i < this.terroristNodes.size(); i++) {
            BayesianNodeTerrorists node = terroristNodes.get(i);
            Edge e = node.getEdge();

            f = e.getFirst().getIndex();
            s = e.getSecond().getIndex();
            // same as before (for one edge)


            // *******************************************************
            // since i have f, s then i can call to the function of get prob *******************
            // *******************************************************


            first_chem = reportedNodes.get(f);
            second_chem = reportedNodes.get(s);
            first_Army = armyNodes.get(f);
            second_Army = armyNodes.get(f);

            // get the boolean evidences for the table
            c1 = first_chem.getVertex().getReportedChem();
            c2 = second_chem.getVertex().getReportedChem();
            flag1 = first_Army.getVertex().getReportedArmy();
            flag2 = second_Army.getVertex().getReportedArmy();
            ans = get_prob(f, s, flag1, flag2, c1, c2);

            System.out.println("The Edge of Vertexs: " + f + " and " + s + " has probability to heve terrorist is: " + ans);
        }

    }


    private boolean get_reprted_army_from_int_to_bool(int flag) {
        boolean ans = false;
        if (flag == 0)
            ans = true;
        else if (flag == 1)
            ans = false;
        else
            System.out.println("called to check boolean value of flage with un-known");
        return ans;
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////
    // return the probability only of one edge when we know only the chem
    // we also heve first and second indexs to find the right node for his table
    // first and second army are the flags
    /////////////////////////////////////////////////////////////////////////////////////////////////////
    private double get_prob(int first_index, int second_index,
                            int first_army, int sec_army,
                            boolean first_chem, boolean sec_chem) {

        double ans = -11111;

        System.out.println("the first and sec indexs are:    " + first_index + "    " + second_index);
        BayesianNodeTerrorists node = getTerrNode(first_index, second_index);
        System.out.println("some info about the node:");
        System.out.println("the	weight is:		" + node.getEdge().getWeight());
        System.out.println("the first and sec of the node are:	" + node.getEdge().getFirst().getIndex() + "        " + node.getEdge().getSecond().getIndex());

        double a1t = Probability.Parmy;
        double a1f = 1 - Probability.Parmy;
        double a2t = Probability.Parmy;
        double a2f = 1 - Probability.Parmy;

        if (first_army == -1) {
            System.out.println("111111111111111111111111111111111111111111111111111111111111111");
            if (sec_army == -1) {
                System.out.println("22222222222222222222222222222222222222222222222222222222222222");
                // if them both are unknown
                ans = (node.getProbability(sec_chem, first_chem, true, true) * a2t * a1t) +
                        (node.getProbability(sec_chem, first_chem, true, false) * a2t * a1f) +
                        (node.getProbability(sec_chem, first_chem, false, true) * a2f * a1t) +
                        (node.getProbability(sec_chem, first_chem, false, false) * a2f * a1f);
                System.out.println("THE ANS IS::::      " + ans);

            } else {
                System.out.println("33333333333333333333333333333333333333333333333333333333333333333");
                // if only a1 is unknown
                boolean a2 = get_reprted_army_from_int_to_bool(sec_army);
                ans = node.getProbability(sec_chem, first_chem, a2, true) * a1t +
                        node.getProbability(sec_chem, first_chem, a2, false) * a1f;
            }
        } else {
            System.out.println("444444444444444444444444444444444444444444444444444444444444444444444");
            // if a1 is known
            boolean a1 = get_reprted_army_from_int_to_bool(first_army);
            // if a2 is unknown
            if (sec_army == -1) {
                System.out.println("555555555555555555555555555555555555555555555555555555555555555555");
                ans = node.getProbability(sec_chem, first_chem, true, a1) * a2t +
                        node.getProbability(sec_chem, first_chem, false, a1) * a2f;
            } else {
                // they both known!! :)
                System.out.println("*********************************************************************");
                System.out.println("*********************************************************************");
                boolean a2 = get_reprted_army_from_int_to_bool(sec_army);
                ans = node.getProbability(sec_chem, first_chem, a2, a1);
            }
        }

        return ans;
    }

    private BayesianNodeTerrorists getTerrNode(int first_index, int second_index) {
        // TODO Auto-generated method stub
        boolean found = false;
        Iterator<BayesianNodeTerrorists> it = terroristNodes.iterator();
        Edge e;
        BayesianNodeTerrorists bnt = null;
        while (it.hasNext() && !found) {
            bnt = it.next();
            e = bnt.getEdge();
            if (e.getFirst().getIndex() == first_index && e.getSecond().getIndex() == second_index)
                found = true;
            if (e.getFirst().getIndex() == second_index && e.getSecond().getIndex() == first_index)
                found = true;
        }
        if (bnt == null) {
            System.out.println("*****could not find the right node for the table********");
            System.out.println("******** be ready for null pointer excption*********");
        }
        return bnt;

    }


}



