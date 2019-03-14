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
package org.springframework.samples.petclinic.web;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Booking;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BookingController {

    private final ClinicService clinicService;


    @Autowired
    public BookingController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }
    
    @ModelAttribute("booking")
    public Booking loadPetWithBooking(@PathVariable("petId") int petId) {
        Pet pet = this.clinicService.findPetById(petId);
        Booking booking = new Booking();
        pet.addBooking(booking);
        return booking;
    }

    //TODO Cambiar path
    @RequestMapping(value = "/owners/*/pets/{petId}/bookings/new", method = RequestMethod.GET)
    public String initNewVisitForm(@PathVariable("petId") int petId, Map<String, Object> model) {
        return "pets/createBookingForm";
    }

    //TODO Cambiar path
    @RequestMapping(value = "/owners/{ownerId}/pets/{petId}/bookings/new", method = RequestMethod.POST)
    public String processNewVisitForm(@Valid  Booking booking, BindingResult result) {
        if (result.hasErrors()) {
            return "pets/createBookingForm";
        } else {
            this.clinicService.saveBooking(booking);
            return "redirect:/owners/{ownerId}";
        }
    }

}
