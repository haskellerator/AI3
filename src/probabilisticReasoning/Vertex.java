/**
 * Created with IntelliJ IDEA.
 * User: talz
 * Date: 23/11/13
 * Time: 02:14
 * To change this template use File | Settings | File Templates.
 */

import java.util.ArrayList;
import java.util.Iterator;


public class Vertex {
    private ArrayList<Edge> edges;
    private boolean chemicals;
    private boolean reportedChem;
    private boolean army;
    private int reportedArmy;
    private int index;
    private boolean goal;

    public Vertex(int i) {
        index = i;
        edges = new ArrayList<Edge>(0);
        this.chemicals = false;
        this.reportedChem = false;
        this.army = false;
        this.reportedArmy = Flags.REPORTED_NOT;
    }

    public int getIndex() {
        return index;
    }

    public void addEdge(Edge e) {
        edges.add(e);
    }

    public String toString() {
        String ans = "@---@ v " + index + " ( chems: " + chemicals + " , mUnits: " + army + " )";
        for (int i = 0; i < edges.size(); i++)
            ans += "\n" + edges.get(i).toString(this);
        return ans;
    }

    public void setChemicals(boolean a) {
        chemicals = a;
    }

    public boolean getChemicals() {
        return chemicals;
    }

    public Edge getEdgeWith(Vertex other) {
        Iterator<Edge> it = edges.iterator();
        while (it.hasNext()) {
            Edge e = it.next();
            if (e.otherSide(this) == other) {
                return e;
            }
        }
        return null;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public boolean getArmy() {
        return army;
    }

    public void setArmy(boolean a) {
        army = a;
    }



    public boolean hasChems() {
        return chemicals;
    }

    public boolean isGoal() {
        return goal;
    }

    public void setGoal(boolean goal) {
        this.goal = goal;
    }

    public int getReportedArmy() {
        return reportedArmy;
    }

    public void setReportedArmy(int reportedArmy) {
        this.reportedArmy = reportedArmy;
    }

    public boolean getReportedChem() {
        return reportedChem;
    }

    public void setReportedChem(boolean reportedChem) {
        this.reportedChem = reportedChem;
    }
}

