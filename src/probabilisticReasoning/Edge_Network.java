import java.util.ArrayList;


public class Edge_Network {

    private ArrayList<Vertex_Network> parents;
    private int first;
    private int second;
    private double weight;

    public Edge_Network(int f, double w) {
        this.parents = new ArrayList<Vertex_Network>();
        this.first = f;
        this.setWeight(w);
    }


    public void add_parnet(Vertex_Network v) {
        this.parents.add(v);
    }

    public boolean is_setted(int v_index) {
        return (v_index == this.first);
    }

    public void set_second(int sec_index) {
        this.second = sec_index;
    }


    public int get_first() {
        return this.first;
    }

    public int get_sec() {
        return this.second;
    }


    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }


}
