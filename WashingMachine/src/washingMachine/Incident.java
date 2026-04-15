package washingmachine;

import java.sql.Date;

public class Incident {
    private int incidentId;
    private int machineId;
    private Date incidentDate;
    private String description;

    public Incident(int incidentId, int machineId, Date incidentDate, String description) {
        this.incidentId = incidentId;
        this.machineId = machineId;
        this.incidentDate = incidentDate;
        this.description = description;

    }

    public int getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(int incidentId) {
        this.incidentId = incidentId;
    }

    public int getMachineId() {
        return machineId;
    }

    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    public Date getIncidentDate() {
        return incidentDate;
    }

    public void setIncidentDate(Date incidentDate) {
        this.incidentDate = incidentDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Incident{" +
                "incidentId=" + incidentId +
                ", machineId=" + machineId +
                ", incidentDate=" + incidentDate +
                ", description='" + description + '\'' +
                '}';
    }
}
