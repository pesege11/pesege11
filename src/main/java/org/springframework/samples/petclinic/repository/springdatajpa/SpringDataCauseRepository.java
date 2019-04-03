package org.springframework.samples.petclinic.repository.springdatajpa;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.model.Cause;
import java.util.Collection;
import javax.transaction.Transactional;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Donation;
import org.springframework.samples.petclinic.repository.CauseRepository;
import org.springframework.samples.petclinic.repository.OwnerRepository;

public interface SpringDataCauseRepository extends CauseRepository, Repository<Cause, Integer> {
	
	@Query("SELECT c FROM Cause c where c.id=:causeId")
	Cause findByCauseId(@Param(value = "causeId") int causeId) throws DataAccessException;
	
	@Query("SELECT c FROM Cause c")
	Collection<Cause> findAllCauses();
	
	@Query("SELECT c.donations FROM Cause c where c.id=:causeId")
	Collection<Donation> findDonationsByCauseId(@Param(value = "causeId") int causeId) throws DataAccessException;
	
	@Transactional
    @Modifying
    @Query("DELETE FROM Cause c where c.id=:causeId")
    void delete(@Param(value = "causeId") int causeId) throws DataAccessException;
	
	
	

	

}
