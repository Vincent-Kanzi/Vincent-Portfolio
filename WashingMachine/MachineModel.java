package washingmachine;

public class MachineModel {
    private int modelId;
    private String modelDescription;
    private int machineId;

    public MachineModel(int modeId, String modelDescription, int machineId) {
        this.modelId = modeId;
        this.modelDescription = modelDescription;
        this.machineId = machineId;
    }

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public String getModelDescription() {
        return modelDescription;
    }

    public void setModelDescription(String modelDescription) {
        this.modelDescription = modelDescription;
    }

    public int getMachineId() {
        return machineId;
    }

    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    @Override
    public String toString() {
        return "MachineModel{" +
                "modelId=" + modelId +
                ", modelDescription='" + modelDescription + '\'' +
                ", machineId=" + machineId +
                '}';
    }
}
