package washingmachine;

public class Machine {
    private int machineId;
    private int residenceId;
    private int modelId;
    private String serialNo;

    public Machine(int machineId, int residenceId, int modelId, String serialNo) {
        this.machineId = machineId;
        this.residenceId = residenceId;
        this.modelId = modelId;
        this.serialNo = serialNo;
    }

    public int getMachineId() {
        return machineId;
    }

    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    public int getResidenceId() {
        return residenceId;
    }

    public void setResidenceId(int residenceId) {
        this.residenceId = residenceId;
    }

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    @Override
    public String toString() {
        return "Machine{" +
                "machineId=" + machineId +
                ", residenceId=" + residenceId +
                ", modelId=" + modelId +
                ", serialNo='" + serialNo + '\'' +
                '}';
    }
}
