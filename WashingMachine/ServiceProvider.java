package washingmachine;

public class ServiceProvider {
    private  int providerId;
    private String providerName;
    private String contact;

    public ServiceProvider(int providerId, String providerName, String contact) {
        this.providerId = providerId;
        this.providerName =providerName;
        this.contact = contact;
    }

    public int getProviderId() {
        return providerId;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "ServiceProvider{" +
                "providerId=" + providerId +
                ", providerName='" + providerName + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}


