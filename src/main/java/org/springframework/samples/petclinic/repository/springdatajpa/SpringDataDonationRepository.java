package org.springframework.samples.petclinic.repository.springdatajpa;

import javax.transaction.Transactional;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Donation;
import org.springframework.samples.petclinic.repository.DonationRepository;
import org.springframework.samples.petclinic.repository.OwnerRepository;

/**
 * Spring Data JPA specialization of the {@link OwnerRepository} interface
 *
 * @author Michael Isvy
 * @since 15.1.2013
 */
public interface SpringDataDonationRepository extends DonationRepository, Repository<Donation, Integer> {

	@Query("SELECT d FROM Donation d where d.id=:donationId")
	Donation findByDonationId(@Param("donationId") int donationId) throws DataAccessException;
	

	@Transactional
    @Modifying
    @Query("DELETE FROM Donation d where d.id=:donationId")
    void delete(@Param(value = "donationId") int donationId) throws DataAccessException;

}
