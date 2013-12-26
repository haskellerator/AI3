import java.util.ArrayList;

public class Vertex_Network {

    private int chemicals;
    private int militaryUnits;
    private int V_index;
    private int b_index;        // 1 for army, 2 for chem, 3 for reported chem
    ArrayList<Vertex_Network> parents;
    ArrayList<Vertex_Network> children;

    public Vertex_Network() {
        this.chemicals = 0;
        this.militaryUnits = 0;
        this.V_index = -1;
        this.b_index = -1;
        this.parents = null;
        this.children = null;
    }


    public Vertex_Network(int chem, int army, int v_i, int b_i) {
        this.chemicals = chem;
        this.militaryUnits = army;
        this.V_index = v_i;
        this.b_index = b_i;
        this.parents = new ArrayList<Vertex_Network>();
        this.children = new ArrayList<Vertex_Network>();
    }


    public void add_parnet(Vertex_Network v) {
        this.parents.add(v);
    }

    public void add_child(Vertex_Network v) {
        this.children.add(v);
    }


    public int getV_index() {
        return V_index;
    }

}
