package com.sample.datamodel;

public class OTPBean {

	private String Status;
	private String Details;
	
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getDetails() {
		return Details;
	}
	public void setDetails(String details) {
		Details = details;
	}
	
	public String toString()
	{
		return "Status="+Status+"	Details="+Details;
	}
}
