/**
 * Created with IntelliJ IDEA.
 * User: talz
 * Date: 23/11/13
 * Time: 02:08
 * To change this template use File | Settings | File Templates.
 */

import java.io.IOException;


public class EntryPoint {


    public static void main(String[] args) throws IOException {
        System.out.println("~~~ Welcome to the reasoning under uncertainty simulator ");
        if (args.length < 1) {
            System.out.println("~~~ no graph file given ");
            return;
        }

        Utility.fileName = args[0];
        System.out.println("~~~ Parsing file: " + args[0] + ", and Creating a Graph ");

        Graph g = Utility.processGraphFromFile();  // creating graph
        BayesianNetwork bn = new BayesianNetwork(g);
        Utility.openInput();


        bn.show();


        startSimulation(g, bn);
        Utility.closeInput();
    }

    private static void startSimulation(Graph g, BayesianNetwork bn) throws IOException {
        while (true) {
            print(g.toString());
            print("~~~ What would you like to do?\n~~~    0. quit.\n~~~    1. Reset evidence list." +
                    "\n~~~    2. Add evidence.\n~~~    3. Show evidence list.\n~~~    4. Do probabilistic reasoning.");
            String line = Utility.br.readLine();
            if (line.compareTo("0") == 0) {
                print("bye bye");
                return;
            } else if (line.compareTo("1") == 0) {
                g.resetEvidenceList();
            } else if (line.compareTo("2") == 0) {
                g.addEvidence();
            } else if (line.compareTo("3") == 0) {
                g.showEvidenceList();
            } else if (line.compareTo("4") == 0) {
                //bn.ProbabilisticReasoning();
            } else {
                print("unrecognized operation, retry...");
            }
            System.out.println();
        }
    }

    private static void print(String s) {
        System.out.println(s);
    }


}

