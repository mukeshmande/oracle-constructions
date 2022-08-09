package org.oracle.constructions.beans;

public class CustomerDetailsBean {

    private long customerId;
    private long contractId;
    private String geozone;
    private String teamcode;
    private String projectcode;
    private String buildduration;

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getContractId() {
        return contractId;
    }

    public void setContractId(long contractId) {
        this.contractId = contractId;
    }

    public String getGeozone() {
        return geozone;
    }

    public void setGeozone(String geozone) {
        this.geozone = geozone;
    }

    public String getTeamcode() {
        return teamcode;
    }

    public void setTeamcode(String teamcode) {
        this.teamcode = teamcode;
    }

    public String getProjectcode() {
        return projectcode;
    }

    public void setProjectcode(String projectcode) {
        this.projectcode = projectcode;
    }

    public String getBuildduration() {
        return buildduration;
    }

    public void setBuildduration(String buildduration) {
        this.buildduration = buildduration;
    }
}
