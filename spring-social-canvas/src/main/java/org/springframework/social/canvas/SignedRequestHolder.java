package org.springframework.social.canvas;

public class SignedRequestHolder {

	private String userId;

	private String oauthToken;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOauthToken() {
		return oauthToken;
	}

	public void setOauthToken(String oauthToken) {
		this.oauthToken = oauthToken;
	}
	
}
