package washingmachine;

public class MachineMake {
    private  int makeId;
    private  String makeDescription;



    public MachineMake(int makeId, String makeDescription) {
        this.makeId = makeId;
        this.makeDescription = makeDescription;
    }

    public int getMakeId() {
        return makeId;
    }

    public void setMakeId(int makeId) {
        this.makeId = makeId;
    }

    public String getMakeDescription() {
        return makeDescription;
    }

    public void setMakeDescription(String makeDescription) {
        this.makeDescription = makeDescription;
    }

    @Override
    public String toString() {
        return "MachineMake{" +
                "makeId=" + makeId +
                ", makeDescription='" + makeDescription + '\'' +
                '}';
    }
}
