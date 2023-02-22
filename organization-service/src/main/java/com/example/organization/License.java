package com.example.organization;

public class License {
    private String id;
    private String organizationId;
    private String productName;
    private String licenseType;

    public License() {
    }

    public License(String id, String organizationId, String productName, String licenseType) {
        this.id = id;
        this.organizationId = organizationId;
        this.productName = productName;
        this.licenseType = licenseType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(String licenseType) {
        this.licenseType = licenseType;
    }
}
