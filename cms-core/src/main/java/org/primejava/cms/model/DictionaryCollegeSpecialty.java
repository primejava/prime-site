package org.primejava.cms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "dic_college_specialty")
public class DictionaryCollegeSpecialty {
	
	public final static String ROOT             = "0";
	private String id;
    private String             collegeId;
    private String             specialtyId;
    private String             education;
    private Short              year;
    private Integer            sort;
    private String             name;
    
    @Id
	@Column(name = "ID", updatable = false)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDHexGenerator")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public DictionaryCollegeSpecialty() {
    }

    public DictionaryCollegeSpecialty(final String id) {
        this.id = id;
    }

    public DictionaryCollegeSpecialty(final String id, final String collegeId, final String specialtyId,
            final String education, final Short year) {
        this.id = id;
        this.collegeId = collegeId;
        this.specialtyId = specialtyId;
        this.education = education;
        this.year = year;
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (final CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Column(name = "COLLEGE_ID")
    public String getCollegeId() {
        return this.collegeId;
    }

    @Column(name = "EDUCATION")
    public String getEducation() {
        return this.education;
    }

    @Column(name = "NAME")
    public String getName() {
        return this.name;
    }

    @Column(name = "SORT")
    public Integer getSort() {
        return this.sort;
    }

    @Column(name = "SPECIALTY_ID")
    public String getSpecialtyId() {
        return this.specialtyId;
    }

    @Column(name = "YEAR")
    public Short getYear() {
        return this.year;
    }

    public void setCollegeId(final String collegeId) {
        this.collegeId = collegeId;
    }

    public void setEducation(final String education) {
        this.education = education;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setSort(final Integer sort) {
        this.sort = sort;
    }

    public void setSpecialtyId(final String specialtyId) {
        this.specialtyId = specialtyId;
    }

    public void setYear(final Short year) {
        this.year = year;
    }

}
