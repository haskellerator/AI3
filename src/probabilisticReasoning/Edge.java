/**
 * Created with IntelliJ IDEA.
 * User: talz
 * Date: 23/11/13
 * Time: 02:07
 * To change this template use File | Settings | File Templates.
 */

public class Edge {
    private Vertex first;
    private Vertex second;
    private double weight;
    private boolean terrorists;
    private int reportedTerrorists;

    public Edge(Vertex f, Vertex s, int w) {
        this.first = f;
        this.second = s;
        this.weight = w;
        this.setTerrorists(false);
        this.setReportedTerrorists(Flags.REPORTED_NOT);
    }

    public Vertex otherSide(Vertex from) {
        if (from == first) return second;
        return first;
    }

    public String toString(Vertex from) {
        String ans = "#---#\t   ";
        if (from == first)
            ans += first.getIndex() + " ---> " + second.getIndex();
        else
            ans += second.getIndex() + " ---> " + first.getIndex();

        ans += " ( weight: " + weight;
        return ans + " ) ";
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        String ans = "#---#\t   ";
        ans += first.getIndex() + " ---> " + second.getIndex();
        ans += " ( weight: " + weight;
        return ans + " ) ";
    }

    public boolean hasVertex(Vertex v) {
        return v == first || v == second;
    }

    public Vertex getFirst() {
        return first;
    }

    public Vertex getSecond() {
        return second;
    }

    // for the state function
    public void setup(Vertex fst, Vertex snd) {
        this.first = fst;
        this.second = snd;
    }

    public boolean isTerrorists() {
        return terrorists;
    }

    public void setTerrorists(boolean terrorists) {
        this.terrorists = terrorists;
    }

    public int getReportedTerrorists() {
        return reportedTerrorists;
    }

    public void setReportedTerrorists(int reportedTerrorists) {
        this.reportedTerrorists = reportedTerrorists;
    }

    public String show() {
        return "" + first.getIndex() + " -> " + second.getIndex();
    }
}
