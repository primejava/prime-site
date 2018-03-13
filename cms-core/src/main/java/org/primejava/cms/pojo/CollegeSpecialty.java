package org.primejava.cms.pojo;

import java.io.Serializable;

public class CollegeSpecialty implements Serializable {
	private static final long serialVersionUID = 5856225724566974011L;
	private String            id;
    private String            name;
    private String            parentId;
    private Short             year;
    private String            education;
    private Boolean           isCollege;
    private String            code;
    private String            masterCode;
    private String            doctorCode;
    private Integer           sort;

    @Override
    public boolean equals(final Object obj) {
        // TODO Auto-generated method stub
        if (null == obj) {
            return false;
        }
        if (!(obj instanceof CollegeSpecialty)) {
            return false;
        }
        final CollegeSpecialty collegeSpecialty = (CollegeSpecialty) obj;
        if (!getId().equals(collegeSpecialty.getId())) {
            return false;
        }
        if (!getName().equals(collegeSpecialty.getName())) {
            return false;
        }
        if (!getParentId().equals(collegeSpecialty.getParentId())) {
            return false;
        }
        if (!getEducation().equals(collegeSpecialty.getEducation())) {
            return false;
        }
        return true;
    }

    public String getCode() {
        return this.code;
    }

    public String getDoctorCode() {
        return this.doctorCode;
    }

    public String getEducation() {
        return this.education;
    }

    public String getId() {
        return this.id;
    }

    public Boolean getIsCollege() {
        return this.isCollege;
    }

    public String getMasterCode() {
        return this.masterCode;
    }

    public String getName() {
        return this.name;
    }

    public String getParentId() {
        return this.parentId;
    }

    public Integer getSort() {
        return this.sort;
    }

    public Short getYear() {
        return this.year;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public void setDoctorCode(final String doctorCode) {
        this.doctorCode = doctorCode;
    }

    public void setEducation(final String education) {
        this.education = education;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public void setIsCollege(final Boolean isCollege) {
        this.isCollege = isCollege;
    }

    public void setMasterCode(final String masterCode) {
        this.masterCode = masterCode;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setParentId(final String parentId) {
        this.parentId = parentId;
    }

    public void setSort(final Integer sort) {
        this.sort = sort;
    }

    public void setYear(final Short year) {
        this.year = year;
    }
}
