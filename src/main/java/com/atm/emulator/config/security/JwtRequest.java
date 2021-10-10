package com.atm.emulator.config.security;

public class JwtRequest {

	//@CustomValidate(message = "String is not valid")
	private String cardNo;
	private String pin;

	public JwtRequest() {
	}

	public JwtRequest(String cardNo, String pin) {
		this.cardNo = cardNo;
		this.pin = pin;

	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}
}