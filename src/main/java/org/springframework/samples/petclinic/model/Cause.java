/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.springframework.samples.petclinic.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotEmpty;
import org.springframework.core.style.ToStringCreator;

/**
 *
 * @author japarejo
 */
@Entity
@Table(name = "causes")
public class Cause extends BaseEntity {
    
    
	@Column(name = "name")
	@NotEmpty
	private String name;

	@Column(name = "description")
	@NotEmpty
	private String description;

	@Column(name = "budget_target")
	@NotNull
	@Min(0)
	private Double budgetTarget;

	@Column(name = "organization")
	@NotEmpty
	private String organization;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cause", fetch = FetchType.EAGER)
	private Set<Donation> donations;

	@Column(name = "remaining_money")
	private Double remainingMoney;

	public Double getRemainingMoney() {
		Double acum = 0.;
		BigDecimal a;
		BigDecimal b;
		for (Donation donation : donations) {
			if(donation.getAmount()!=null) {
			acum += donation.getAmount();}

		}

		a = BigDecimal.valueOf(budgetTarget);
		b = BigDecimal.valueOf(acum);

		remainingMoney= (a.subtract(b)).doubleValue();

		return remainingMoney;

	}

	public void setRemainingMoney(Double remainingMoney) {
		this.remainingMoney = remainingMoney;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getBudgetTarget() {
		return budgetTarget;
	}

	public void setBudgetTarget(Double budgetTarget) {
		this.budgetTarget = budgetTarget;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public Set<Donation> getDonations() {
		return donations;
	}

	public void setDonations(Set<Donation> donations) {
		this.donations = donations;
	}

	public void addDonation(Donation donation) {
		getDonations().add(donation);
		donation.setCause(this);
		donation.setDonationDate(LocalDate.now());
	}

	@Override
	public String toString() {
		return new ToStringCreator(this).append("id", this.getId()).append("new", this.isNew())
				.append("name", this.getName()).append("description", this.getDescription())
				.append("budgetTarget", this.getBudgetTarget()).append("organization", this.getOrganization())
				.toString();
	}

}
