package org.primejava.cms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "DIC_SPECIALTY")
public class DictionarySpecialty {
	
	private String id;
    private String            name;
    private String            code;
    public DictionarySpecialty() {
    }

    public DictionarySpecialty(final String id) {
        this.id = id;
    }

    public DictionarySpecialty(final String id, final String name, final String code) {
        this.id = id;
        this.name = name;
        this.code = code;
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
	
    @Column(name = "CODE")
    public String getCode() {
        return this.code;
    }

    @Column(name = "NAME")
    public String getName() {
        return this.name;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
