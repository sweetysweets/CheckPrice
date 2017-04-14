package org.edu.nju.model;

public class Commodity {
	int id;
	String catalog;
	String description;
	String buyURL;
	String storeId;

	public boolean equals(Object obj) {
	
		if (!(obj instanceof Commodity))
			return false;

		Commodity c = (Commodity) obj;
		if(c.getId()==this.id){
			return true;
		}else{
			return false;
		}
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCatalog() {
		return catalog;
	}

	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBuyURL() {
		return buyURL;
	}

	public void setBuyURL(String buyURL) {
		this.buyURL = buyURL;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

}
