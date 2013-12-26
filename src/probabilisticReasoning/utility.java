import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class utility {

    public static String fileName;
    public static BufferedReader br;

    public static Graph processGraphFromFile() throws IOException {
        Graph g = null;
        BufferedReader reader = new BufferedReader(new FileReader(fileName)); // opens the file for reading
        String line = null;

		/* reads the lines of the input file one by one, and parsing the arguments given */
        while ((line = reader.readLine()) != null) {
            if (line.length() != 0) {
                char action = line.charAt(1);
                int arg1 = getInteger(line, 3);
                int arg2 = getInteger(line, 5);

                /* sets a new graph with amount of arg1 vertices*/
                if (action == 'V') {
                    g = new Graph(arg1);

				/* reads the required information for setting a new edge */
                } else if (action == 'E') {
                    int w = getInteger(line, 8);
                    g.updateEdge(arg1, arg2, w);    // connects the nodes

				/* sets goal vertex */
                } else if (action == 'G') {
                    int vertexNum = getInteger(line, 7);
                    g.getVertex(vertexNum - 1).setGoal(true);
                } else {
                    System.out.println("got unexpected input : " + action + ". quitting"); // in case of unhandled read
                    reader.close();
                    return null;
                }
            }
        }
        reader.close();
        return g;
    }

    /* converts s[index] to integer, can do so up to 3 digits numbers*/
    public static int getInteger(String s, int index) {
        int i = 0, acc = 0;
        while (true) {
            int flag = Character.digit(s.charAt(index + i), 10);
            if (flag == -1) return acc;
            acc = acc * 10;
            acc += flag;
            i++;
        }
    }

    public static int intReader(int maximumNumber) throws IOException {
        System.out.println("~~~ Please enter a valid integer:");
        int n = Integer.parseInt(br.readLine());
        if (n <= 0 || n > maximumNumber) {
            System.out.println("~~~ Invalid Number");
            return intReader(maximumNumber);
        }
        return n;
    }

    public static double doubleReader(double maximumNumber) throws IOException {
        System.out.println("~~~ Please enter a valid number:");
        double d = Double.parseDouble(br.readLine());
        if (d <= 0 || d > maximumNumber) {
            System.out.println("~~~ Invalid Number");
            return doubleReader(maximumNumber);
        }
        return d;
    }

    public static void openInput() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }


    public static void closeInput() throws IOException {
        br.close();
    }
}
