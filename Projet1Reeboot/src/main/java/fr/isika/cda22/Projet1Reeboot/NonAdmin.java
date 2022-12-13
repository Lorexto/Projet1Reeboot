package fr.isika.cda22.Projet1Reeboot;

public class NonAdmin {

	protected String userId;
	protected String mdp;


	public NonAdmin(String userId, String mdp) {
		super();
		this.userId = userId;
		this.mdp = mdp;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getMdp() {
		return mdp;
	}


	public void setMdp(String mdp) {
		this.mdp = mdp;
	}




}
