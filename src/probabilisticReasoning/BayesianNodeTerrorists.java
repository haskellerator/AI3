/**
 * Created by talz on 27/12/13.
 */
public class BayesianNodeTerrorists {

    private Edge edge;
    private BayesianNodeArmy firstArmyParent;
    private BayesianNodeReported firstReportedParent;
    private BayesianNodeArmy secondArmyParent;
    private BayesianNodeReported secondReportedParent;
    private double[] probabilities;


    public BayesianNodeTerrorists(Edge e, BayesianNodeArmy armyParent1, BayesianNodeReported reportedParent1, BayesianNodeArmy armyParent2, BayesianNodeReported reportedParent2) {
        this.edge = e;
        this.firstArmyParent = armyParent1;
        this.firstReportedParent = reportedParent1;
        this.secondArmyParent = armyParent2;
        this.secondReportedParent = reportedParent2;
        this.probabilities = new double[16];
        setDistribution();
    }

    public double getProbability(boolean army1, boolean army2, boolean chem1, boolean chem2) {
        boolean[] arr = {army1, army2, chem1, chem2};
        return probabilities[getTableIndex(arr)];
    }

    private void setProbability(int index, double prob) {
        probabilities[index] = prob;
    }

    public static int getTableIndex(boolean[] input) {
        int ans = 0;
        for (int i = 0; i < 4; i++)
            ans += (input[i] ? 1 : 0) * ((int) Math.pow(2, i));
        return ans;
    }

    private void setDistribution() {

        setProbability(0, Probability.terrorist_leakage);
        setProbability(1, oneVariableTrueProbability(false));
        setProbability(2, oneVariableTrueProbability(false));
        setProbability(4, oneVariableTrueProbability(true));
        setProbability(8, oneVariableTrueProbability(true));

        for (int i = 3; i < 16; i++) {
            if (i != 4 && i != 8) {
                double calculation = 1;
                boolean[] arr = getBoolVals(i);
                for (int j = 0; j < 4; j++) {
                    if (arr[j])
                        calculation = calculation * probabilities[(int) Math.pow(2, j)];
                }
                setProbability(i, calculation);
            }
        }
    }

    @Override
    public String toString() {
        int f = this.edge.getFirst().getIndex(), s = this.edge.getSecond().getIndex();
        double w = this.edge.getWeight();
        String ans = "~~~ Edge (vertices " + f + " " + s + "," +
                " weight " + w + "): terrorists " + f + " " + s + " | army " + f + ", army " + s + ", chem reported " + f + ", chem reported " + s + "\n";
        for (int i = 0; i < 16; i++) {
            boolean[] bVals = getBoolVals(i);
            ans += "~~~ P(terrorrists" + f + " " + s + " | ";
            if (!bVals[0])
                ans += "not ";
            ans += "army " + f + ", ";
            if (!bVals[1])
                ans += "not ";
            ans += "army " + s + ", ";
            if (!bVals[2])
                ans += "not ";
            ans += "chem reported " + f + ", ";
            if (!bVals[3])
                ans += "not ";
            ans += "chem reported " + 2 + ") = " + probabilities[i] + "\n";
        }
        return ans + '\n';
    }

    public static boolean[] getBoolVals(int index) {
        boolean[] ans = new boolean[4];
        if (index % 2 == 1) ans[0] = true;
        if ((2 <= index && index <= 3) || (6 <= index && index <= 7) || (10 <= index && index <= 11) || (14 <= index && index <= 15)) {
            ans[1] = true;
        }

        if ((4 <= index && index <= 7) || (12 <= index && index <= 15)) {
            ans[2] = true;
        }

        if (8 <= index && index <= 15) {
            ans[3] = true;
        }
        return ans;
    }

    private double oneVariableTrueProbability(boolean isChem) {
        double pCause;
        if (isChem)
            pCause = Probability.Pchem_cause;
        else
            pCause = Probability.Parmy_cause;
        return pCause * Math.max((5 - this.edge.getWeight()) / 5, 0.3);
    }

    public Edge getEdge() {
        return edge;
    }
}


