/**
 * Created with IntelliJ IDEA.
 * User: talz
 * Date: 23/11/13
 * Time: 02:08
 * To change this template use File | Settings | File Templates.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class EntryPoint {

    public static void main(String[] args) throws IOException {
        System.out.println("~~~ Welcome to the reasoning under uncertainty simulator ");
        if (args.length < 1) {
            System.out.println("~~~ no graph file given ");
            return;
        }
        utility.fileName = args[0];
        System.out.println("~~~ Parsing file: " + args[0] + ", and Creating a Graph ");

        Graph world = utility.processGraphFromFile();  // creating graph
        System.out.println(world);
        utility.br = new BufferedReader(new InputStreamReader(System.in));
        utility.openInput();
        utility.closeInput();
    }
}

