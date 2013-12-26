import java.util.ArrayList;
import java.util.Iterator;

public class Network {

    private ArrayList<Vertex_Network> vertexs;
    private ArrayList<Edge_Network> edges;


    public Network(Graph G) {
        ArrayList<Vertex> g_ver = G.getVertices();
        Iterator<Vertex> it = g_ver.iterator();
        vertexs = new ArrayList<Vertex_Network>(); // make sure its empty
        edges = new ArrayList<Edge_Network>(); // make sure its empty
        while (it.hasNext()) {
            Vertex v = it.next();
            Vertex_Network a = new Vertex_Network(v.getChemicals(), v.getMUnits(), v.getIndex(), 1);
            Vertex_Network b = new Vertex_Network(v.getChemicals(), v.getMUnits(), v.getIndex(), 2);
            Vertex_Network c = new Vertex_Network(v.getChemicals(), v.getMUnits(), v.getIndex(), 3);
            vertexs.add(a);
            vertexs.add(b);
            vertexs.add(c);
            c.add_parnet(b);
            b.add_child(c);
            set_v_edgeNetwork(a, c, G.getVertices());
        }
    }


    private void set_v_edgeNetwork(Vertex_Network a, Vertex_Network c, ArrayList<Vertex> g_ver) {
        int index = a.getV_index();
        boolean found = false;
        int i;
        Edge_Network e = null;
        for (i = 0; i < edges.size(); i++) {
            e = edges.get(i);
            found = e.is_setted(index);
        }

        if (found) {
            // e is known :)
            e.set_second(index);
            set_w(e, g_ver);
            e.add_parnet(c);
        } else {
            Edge_Network e2 = new Edge_Network(index, 0);
            e.add_parnet(a);
            edges.add(e2);
        }

    }


    private void set_w(Edge_Network e, ArrayList<Vertex> ver) {
        // we have both sides but we dont have any relation to the real edge
        boolean found = false;
        Iterator<Vertex> it = ver.iterator();
        Vertex v = null, u = null;
        while (!found && it.hasNext()) {
            v = it.next();
            if (v.getIndex() == e.get_first())
                found = true;
        }
        while (found && it.hasNext()) {
            u = it.next();
            if (u.getIndex() == e.get_sec())
                found = false;
        }

        Edge e2 = v.getEdgeWith(u);
        e.setWeight(e2.getWeight());
    }


}



