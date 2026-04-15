package washingmachine;

import java.sql.Date;

public class RepairHistory {
    private  int repairId;
    private  int machineId;
    private  int technicianId;
    private  int  providerId;
    private Date repairDate;
    private String description;

    public RepairHistory(int repairId, int machineId, int technicianId, int providerId, Date repairDate, String description) {
        this.repairId = repairId;
        this.machineId = machineId;
        this.technicianId =technicianId;
        this.providerId = providerId;
        this.repairDate =repairDate;
        this.description = description;
    }

    public int getRepairId() {
        return repairId;
    }

    public void setRepairId(int repairId) {
        this.repairId = repairId;
    }

    public int getMachineId() {
        return machineId;
    }

    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    public int getTechnicianId() {
        return technicianId;
    }

    public void setTechnicianId(int technicianId) {
        this.technicianId = technicianId;
    }

    public int getProviderId() {
        return providerId;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }

    public Date getRepairDate() {
        return repairDate;
    }

    public void setRepairDate(Date repairDate) {
        this.repairDate = repairDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "RepairHistory{" +
                "repairId=" + repairId +
                ", machineId=" + machineId +
                ", technicianId=" + technicianId +
                ", providerId=" + providerId +
                ", repairDate=" + repairDate +
                ", description='" + description + '\'' +
                '}';
    }
}
