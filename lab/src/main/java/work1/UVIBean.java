package work1;

import java.io.Serializable;

public class UVIBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String county;
	private String publishAgency;
	private String publishTime;
	private String siteName;
	private String uvi;
	
	
	public UVIBean(String id ,String county,String publishAgency,String publishTime,String siteName,String uvi) {
		super();
		 this.id=id;
		 this.county=county;
		 this.publishAgency=publishAgency;
		this.publishTime=publishTime;
		this.siteName=siteName;
		 this.uvi=uvi;
	}
	
	public UVIBean() {
	}

	public String toString() {
		return "["+id+","+county+","+publishAgency+","+publishTime+","+siteName+","+uvi+"]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getPublishAgency() {
		return publishAgency;
	}

	public void setPublishAgency(String publishAgency) {
		this.publishAgency = publishAgency;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

	public String getUvi() {
		return uvi;
	}

	public void setUvi(String uvi) {
		this.uvi = uvi;
	}


}
	
