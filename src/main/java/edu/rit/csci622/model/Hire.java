package edu.rit.csci622.model;

public class Hire {
	
	private int idHire, idUser, requestedRole, managerSignOff, hrSignOff;

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public int getRequestedRole() {
		return requestedRole;
	}

	public void setRequestedRole(int requestedRole) {
		this.requestedRole = requestedRole;
	}

	public int getManagerSignOff() {
		return managerSignOff;
	}

	public void setManagerSignOff(int managerSignOff) {
		this.managerSignOff = managerSignOff;
	}

	public int getHrSignOff() {
		return hrSignOff;
	}

	public void setHrSignOff(int hrSignOff) {
		this.hrSignOff = hrSignOff;
	}

	public int getIdHire() {
		return idHire;
	}

	public void setIdHire(int idHire) {
		this.idHire = idHire;
	}

}
