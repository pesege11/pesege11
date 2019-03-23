/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author japarejo
 */
@Entity
@Table(name = "donations")
public class Donation extends BaseEntity {
    
    
    @Column(name = "amount")
    private Integer amount;
    
    @ManyToOne
    @JoinColumn(name = "cause_id")
    private Cause cause;

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Cause getCause() {
		return cause;
	}

	public void setCause(Cause cause) {
		this.cause = cause;
	}

   
}
