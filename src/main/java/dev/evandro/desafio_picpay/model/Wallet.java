package dev.evandro.desafio_picpay.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_wallet")
public class Wallet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_wallet")
	private Integer idWallet;

	@Column(name = "balance", nullable = false)
	private Double balance;

	@OneToOne
	@JoinColumn(name = "id_user")
	private User user;
	
	/*@OneToOne
	@JoinColumn(name = "id_wallet")
	private Wallet wallet;*/

	public Integer getIdWallet() {
		return idWallet;
	}

	public void setIdWallet(Integer idWallet) {
		this.idWallet = idWallet;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	

}
