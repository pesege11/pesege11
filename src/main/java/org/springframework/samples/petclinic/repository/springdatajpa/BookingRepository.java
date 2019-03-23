/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.springframework.samples.petclinic.repository.springdatajpa;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Booking;
import org.springframework.samples.petclinic.model.Owner;

/**
 *
 * @author japarejo
 */
public interface BookingRepository extends JpaRepository<Booking,Integer>{
    
    @Query("SELECT booking FROM Booking booking WHERE booking.pet.id =:petId")
    public Collection<Booking> findByPetId(@Param("petId") int id);
}
