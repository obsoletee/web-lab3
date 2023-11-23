package model;

import jakarta.xml.bind.annotation.*;

import java.util.List;

@XmlRootElement(name = "organisation")
@XmlAccessorType(XmlAccessType.FIELD)
public class Organisation {
    private String orgNo;
    private String orgName;
    private String country;

    @XmlElementWrapper(name = "departments")
    @XmlElement(name = "department")
    private List<Department> departments;

    /**
     * This default constructor is required if there are other constructors.
     */
    public Organisation() {

    }

    public Organisation(String orgNo, String orgName, String country) {
        this.orgNo = orgNo;
        this.orgName = orgName;
        this.country = country;
    }

    public String getOrgNo() {
        return orgNo;
    }

    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Department> getDepartments() {
        return this.departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }
}
