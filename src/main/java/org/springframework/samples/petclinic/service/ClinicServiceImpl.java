/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Booking;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.Specialty;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.samples.petclinic.repository.OwnerRepository;
import org.springframework.samples.petclinic.repository.PetRepository;
import org.springframework.samples.petclinic.repository.VetRepository;
import org.springframework.samples.petclinic.repository.VisitRepository;
import org.springframework.samples.petclinic.repository.springdatajpa.BookingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Mostly used as a facade for all Petclinic controllers
 * Also a placeholder for @Transactional and @Cacheable annotations
 *
 * @author Michael Isvy
 */
@Service
public class ClinicServiceImpl implements ClinicService {

    private PetRepository petRepository;
    private VetRepository vetRepository;
    private OwnerRepository ownerRepository;
    private VisitRepository visitRepository;
    private BookingRepository bookingRepository;

    @Autowired
    public ClinicServiceImpl(PetRepository petRepository, VetRepository vetRepository, 
    		OwnerRepository ownerRepository, VisitRepository visitRepository, BookingRepository bookingRepository) {
        this.petRepository = petRepository;
        this.vetRepository = vetRepository;
        this.ownerRepository = ownerRepository;
        this.visitRepository = visitRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<PetType> findPetTypes() throws DataAccessException {
        return petRepository.findPetTypes();
    }

    @Override
    @Transactional(readOnly = true)
    public Owner findOwnerById(int id) throws DataAccessException {
        return ownerRepository.findById(id);
    }

    
    @Override
    @Transactional(readOnly = true)
    public Collection<Owner> findOwnerByLastName(String lastName) throws DataAccessException {
        return ownerRepository.findByLastName(lastName);
    }

    @Override
    @Transactional
    public void saveOwner(Owner owner) throws DataAccessException {
        ownerRepository.save(owner);
    }
    
    @Override
    @Transactional
    public void saveVet(Vet vet) throws DataAccessException {
       vetRepository.save(vet);
    }

    @Override
	public void deleteOwner(Owner owner) throws DataAccessException {
    	ownerRepository.delete(owner.getId());
	}

    @Override
	public Visit findVisitById(int id) throws DataAccessException {
		return visitRepository.findById(id);
	}
    
    @Override
  	public void deleteVisit(Visit visit) throws DataAccessException {
      	visitRepository.delete(visit.getId());
  	}
    
    @Override
    @Transactional
    public void saveVisit(Visit visit) throws DataAccessException {
        visitRepository.save(visit);
    }


    @Override
    @Transactional(readOnly = true)
    public Pet findPetById(int id) throws DataAccessException {
        return petRepository.findById(id);
    }

    @Override
    @Transactional
    public void savePet(Pet pet) throws DataAccessException {
        petRepository.save(pet);
    }

    @Override
    @Transactional
    public void deletePet(Pet pet) throws DataAccessException{
        petRepository.delete(pet.getId());
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "vets")
    public Collection<Vet> findVets() throws DataAccessException {
        return vetRepository.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public Vet findVetById(int id) throws DataAccessException {
    	return vetRepository.findById(id);
    }

    @Override
	public void deleteVet(Vet vet) throws DataAccessException {
    	vetRepository.delete(vet.getId());
	}
    
	@Override
	public Collection<Visit> findVisitsByPetId(int petId) {
		return visitRepository.findByPetId(petId);
		
	}

	@Override
	public Collection<Specialty> findSpecialties() throws DataAccessException {
		return vetRepository.findSpecialties();
	}

    @Transactional(readOnly = true)
    public Collection<Booking> findBookingsByPetId(int petId)
    {
        return bookingRepository.findByPetId(petId);
    }
    
    @Transactional(readOnly = true)
    public Collection<Booking> findAllBookings(){
        return bookingRepository.findAll();
    }
    
    @Transactional()
    public void saveBooking(Booking booking){
        bookingRepository.save(booking);
    }

	@Override
	public void deleteBooking(Booking booking) throws DataAccessException {
		bookingRepository.delete(booking);
		
	}
}
