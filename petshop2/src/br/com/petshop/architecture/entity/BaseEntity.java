package br.com.petshop.architecture.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity extends BaseEntityComparator {
	
	private static final long serialVersionUID = -3336043433768035460L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator="SEQUENCE")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public void setId(Object id) {
    	if(id != null){
    		this.id = Long.parseLong(id.toString());
    	} else {
    		this.id = null;
    	}
    }
    
	public boolean isNew() {
		return (getId() == null);
	}
}