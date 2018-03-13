package org.primejava.cms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "dic_college")
public class DictionaryCollege {
	private String id;
	private String            name;
    private Short             year;
    private String            undergraduateCode;
    private String            masterCode;
    private String            doctorCode;
    public DictionaryCollege() {
    }

    public DictionaryCollege(final String id) {
        this.id = id;
    }

    public DictionaryCollege(final String id, final String name, final String undergraduateCode,
            final String masterCode, final String doctorCode) {
        this.id = id;
        this.name = name;
        this.undergraduateCode = undergraduateCode;
        this.masterCode = masterCode;
        this.doctorCode = doctorCode;
    }
    
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

    @Column(name = "DOCTOR_CODE")
    public String getDoctorCode() {
        return this.doctorCode;
    }

    @Column(name = "MASTER_CODE")
    public String getMasterCode() {
        return this.masterCode;
    }

    @Column(name = "NAME")
    public String getName() {
        return this.name;
    }

    @Column(name = "UNDERGRADUATE_CODE")
    public String getUndergraduateCode() {
        return this.undergraduateCode;
    }

    @Column(name = "YEAR")
    public Short getYear() {
        return this.year;
    }

    public void setDoctorCode(final String doctorCode) {
        this.doctorCode = doctorCode;
    }

    public void setMasterCode(final String masterCode) {
        this.masterCode = masterCode;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setUndergraduateCode(final String undergraduateCode) {
        this.undergraduateCode = undergraduateCode;
    }

    public void setYear(final Short year) {
        this.year = year;
    }
}
