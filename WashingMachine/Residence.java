package washingmachine;

public class Residence {
    private int residenceId;
    private String residenceName;
    private String location;

    public Residence(int residenceId, String residenceName, String location) {
        this.residenceId = residenceId;
        this.residenceName = residenceName;
        this.location = location;
    }

    public int getResidenceId() {
        return residenceId;
    }

    public void setResidenceId(int residenceId) {
        this.residenceId = residenceId;
    }

    public String getResidenceName() {
        return residenceName;
    }

    public void setResidenceName(String residenceName) {
        this.residenceName = residenceName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Residence{" +
                "residenceId=" + residenceId +
                ", residenceName='" + residenceName + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
