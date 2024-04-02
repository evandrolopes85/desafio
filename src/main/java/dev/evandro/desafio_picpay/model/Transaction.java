package dev.evandro.desafio_picpay.model;

public class Transaction {
	private Double value;
	private Integer payer; // pagador
	private Integer payee; // beneficiario

	public Transaction(Double value, Integer payer, Integer payee) {
		super();
		this.value = value;
		this.payer = payer;
		this.payee = payee;
	}

	public Transaction() {
		super();
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Integer getPayer() {
		return payer;
	}

	public void setPayer(Integer payer) {
		this.payer = payer;
	}

	public Integer getPayee() {
		return payee;
	}

	public void setPayee(Integer payee) {
		this.payee = payee;
	}

}
