/**
 * Created by talz on 27/12/13.
 */
public class BayesianNodeTerrorists {

    private Edge edge;
    private BayesianNodeArmy firstArmyParent;
    private BayesianNodeReported firstReportedParent;
    private BayesianNodeArmy secondArmyParent;
    private BayesianNodeReported secondReportedParent;

    public BayesianNodeTerrorists(Edge e, BayesianNodeArmy armyParent1, BayesianNodeReported reportedParent1, BayesianNodeArmy armyParent2, BayesianNodeReported reportedParent2) {
        this.edge = e;
        this.firstArmyParent = armyParent1;
        this.firstReportedParent = reportedParent1;
        this.secondArmyParent = armyParent2;
        this.secondReportedParent = reportedParent2;
    }

    @Override
    public String toString() {
        return "te " + edge.toString();
    }
}
