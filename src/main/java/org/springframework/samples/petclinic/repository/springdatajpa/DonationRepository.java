/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.springframework.samples.petclinic.repository.springdatajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.samples.petclinic.model.Booking;
import org.springframework.samples.petclinic.model.Donation;

/**
 *
 * @author japarejo
 */
public interface DonationRepository extends JpaRepository<Donation,Integer>{
    
    
}
