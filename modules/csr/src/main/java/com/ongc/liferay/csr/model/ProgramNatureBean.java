package com.ongc.liferay.csr.model;
/**
 *  
 * @author Ranjeet
 */
public class ProgramNatureBean {

	private int id;
	private String name;
	
	public ProgramNatureBean() {
	}
	
	public ProgramNatureBean(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
