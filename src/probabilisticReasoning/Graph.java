/**
 * Created with IntelliJ IDEA.
 * User: talz
 * Date: 23/11/13
 * Time: 02:09
 * To change this template use File | Settings | File Templates.
 */

import java.io.IOException;
import java.util.ArrayList;


public class Graph {
    private ArrayList<Vertex> vertexs;
    private ArrayList<Edge> edges;

    public Graph(int vNumber) {
        vertexs = new ArrayList<Vertex>();
        edges = new ArrayList<Edge>();
        for (int i = 0; i < vNumber; i++) {
            vertexs.add(i, new Vertex(i + 1)); // have to test that the vectors are add correctly
        }
    }

    // static graph display function
    public String toString() {
        String ans = "\n<<<<<<< World Map >>>>>>>\n\n";
        for (int i = 0; i < vertexs.size(); i++) {
            ans += "" + vertexs.get(i) + "\n";
        }
        ans += "\n--------------------------\n";
        return ans;
    }

    public void updateEdge(int fstNdIdx, int sndNdIdx, int w) {
        Vertex v1 = vertexs.get(fstNdIdx - 1), v2 = vertexs.get(sndNdIdx - 1);
        Edge e = new Edge(v1, v2, w);
        edges.add(e);
        v1.addEdge(e);
        v2.addEdge(e);
    }

    public Vertex getVertex(int i) {
        return vertexs.get(i);
    }

    public Edge getEdge(Vertex first, Vertex second) {
        return first.getEdgeWith(second);
    }

    public int getTotalChem() {
        int chems = 0;
        for (Vertex v : this.vertexs)
            if (v.getChemicals())
                chems += 1;
        return chems;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public ArrayList<Vertex> getVertices() {
        return vertexs;
    }

    public void resetEvidenceList() {
        for (Vertex v : this.vertexs) {
            v.setReportedArmy(Flags.REPORTED_NOT);
            v.setReportedChem(false);
        }

        for (Edge e : this.edges) {
            e.setReportedTerrorists(Flags.REPORTED_NOT);
        }
    }

    public void addEvidence() throws IOException {
        System.out.println("~~~ For chemicals use the format - Cn (where 'n' is the number of the vertex)\n" +
                "~~~ For Army use the format Anm (where 'n' is the number of the vertex and 'm' is the status)\n" +
                "~~~ For Terrorists use the format Tnmk (where 'n' and 'm' are the edge connecting vertices indexes, " +
                "and 'k is the status)\n" +
                "~~~ Status options: 0 - report in destination\n" +
                "~~~                 1 - report not in destination\n" +
                "~~~                 2 - report unknown");
        String line = Utility.br.readLine();

        if (isChemEvidence(line)) {
            int index = Integer.parseInt(line.substring(1));  // parse index from input
            Vertex v = this.vertexs.get(index - 1);           // get the correct vertex
            v.setReportedChem(true);                          // set field
            System.out.println("~~~ Vertex " + vertexs.get(index - 1).getIndex() + " has chems, according to report");
        } else if (isArmyEvidence(line)) {

            int index = Integer.parseInt(line.substring(1, 2)), // parse index and status
                    status = Integer.parseInt(line.substring(2, 3));// from input
            Vertex v = this.vertexs.get(index - 1);            // get correct vertex
            v.setReportedArmy(status);                         // set field
            System.out.println("~~~ Vertex " + v.getIndex() + " army status is " + Flags.StatusString(v.getReportedArmy()));

        } else if (isTerroristsEvidence(line)) {
            int firstIndex = Integer.parseInt(line.substring(1, 2)),                      // parse indexes and status
                    secondIndex = Integer.parseInt(line.substring(2, 3)),
                    status = Integer.parseInt(line.substring(3, 4));
            Edge e = this.getEdge(vertexs.get(firstIndex - 1), vertexs.get(secondIndex - 1)); // get correct edge
            e.setReportedTerrorists(status);                                             // set field
            System.out.println("~~~ Edge " + e.show() + " terrorists status is " + Flags.StatusString(e.getReportedTerrorists()));

        } else {
            System.out.println("~~~ Unsupported request, returning to menu...");
        }
    }

    private boolean isChemEvidence(String s) {
        return s.charAt(0) == 'C' && Integer.parseInt(s.substring(1)) < this.vertexs.size();
    }

    private boolean isArmyEvidence(String s) {
        return s.charAt(0) == 'A' && Integer.parseInt(s.substring(1, 2)) < this.vertexs.size();
    }

    private boolean isTerroristsEvidence(String s) {
        return s.charAt(0) == 'T' && Integer.parseInt(s.substring(1, 2)) < this.vertexs.size()
                && Integer.parseInt(s.substring(2, 3)) < this.vertexs.size();
    }

    public void showEvidenceList() {
        System.out.println("~~~ Chems:");
        for (Vertex v : this.vertexs) {
            if (v.getReportedChem()) {
                System.out.println("~~~    vertex " + v.getIndex() + " is reported to have chems");
            }
        }
        System.out.println("~~~ Army:");
        for (Vertex v : this.vertexs) {
            if (v.getReportedArmy() != Flags.REPORTED_NOT) {
                System.out.println("~~~    vertex " + v.getIndex() + " status is: " + Flags.StatusString(v.getReportedArmy()));
            }
        }
        System.out.println("~~~ Terrorists:");
        for (Edge e : this.edges) {
            if (e.getReportedTerrorists() != Flags.REPORTED_NOT) {
                System.out.println("~~~    Edge " + e.show() + " status is: " + Flags.StatusString(e.getReportedTerrorists()));
            }
        }
    }
}