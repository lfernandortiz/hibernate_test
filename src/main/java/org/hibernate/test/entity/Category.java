package org.hibernate.test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author sergio.rojas
 *
 */

@Entity
@Table(name="CATEGORY")
public class Category implements IEntity{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;
	
	@Column(name="name")
	String name;
	
	public Category() {
		
	}

	public Category(String name) {
		this.name = name;
	}

	/**
	 * 
	 */
	public Long getId() {
		return this.id;
	}
	
	/**
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}


	/**
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}


	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		String  template = "Category {id: %d, name: %s}";
		return  String.format(template,this.id, this.name);  
	}
	
	
	

}
