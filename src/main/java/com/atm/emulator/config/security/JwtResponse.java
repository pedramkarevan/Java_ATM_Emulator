package com.atm.emulator.config.security;

public class JwtResponse {
	private String access_token;

	public JwtResponse() {
	}

	public JwtResponse(String access_token) {
		this.access_token = access_token;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
}
