package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Cause;
import org.springframework.samples.petclinic.model.Donation;

public interface CauseRepository {
	
	 void save(Cause cause) throws DataAccessException;
	  
	  Cause findByCauseId(int causeId) throws DataAccessException;
	  
	  Collection<Cause> findAll() throws DataAccessException;

	  Collection<Donation> findDonationsByCauseId(int causeId) throws DataAccessException;

	  void delete(int causeId) throws DataAccessException;
	    

}
