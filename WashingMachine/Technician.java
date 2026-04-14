package washingmachine;

public class Technician {
    private  int technicianid;
    private  String technicianName;
    private String contact;

    public Technician(int technicianid, String technicianName, String contact) {
        this.technicianid = technicianid;
        this.technicianName = technicianName;
        this.contact = contact;
    }

    public int getTechnicianid() {
        return technicianid;
    }

    public void setTechnicianid(int technicianid) {
        this.technicianid = technicianid;
    }

    public String getTechnicianName() {
        return technicianName;
    }

    public void setTechnicianName(String technicianName) {
        this.technicianName = technicianName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Technician{" +
                "technicianid=" + technicianid +
                ", technicianName='" + technicianName + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}
