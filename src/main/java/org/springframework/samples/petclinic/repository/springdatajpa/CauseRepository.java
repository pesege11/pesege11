/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.springframework.samples.petclinic.repository.springdatajpa;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Cause;
import org.springframework.samples.petclinic.model.Donation;

/**
 *
 * @author japarejo
 */
public interface CauseRepository extends JpaRepository<Cause,Integer>{
	
	@Query("SELECT SUM(d.amount) FROM Donation d WHERE d.cause.id =:causeId")
    public Integer getCauseCurrentBudget(@Param("causeId") int id);
	

	
}
