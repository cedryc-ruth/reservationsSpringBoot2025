package be.iccbxl.pid.reservations_springboot.model;

public enum UserRole {	
	  ADMIN("admin"),
	  MEMBER("member"),
	  AFFILIATE("affiliate"),
	  PRESS("press"),
	  PRODUCER("producer");

	  private String role;

	  UserRole(String role) {
	    this.role = role;
	  }

	  public String getValue() {
	    return role;
	  }
}