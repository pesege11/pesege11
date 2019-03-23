/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Booking;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author japarejo
 */
@Controller
public class BookingController {

    private static final String VIEWS_BOOKING_CREATE_OR_UPDATE_FORM = "bookings/createOrUpdateBookingForm";
    private final ClinicService clinicService;

    @Autowired
    public BookingController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @RequestMapping(value = "/bookings/{petId}/new", method = RequestMethod.GET)
    public String initCreationForm(@PathVariable("petId") int petId,ModelMap model) {
        Booking booking = new Booking();
        Pet pet=clinicService.findPetById(petId);
        booking.setPet(pet);
        model.put("booking", booking);        
        return VIEWS_BOOKING_CREATE_OR_UPDATE_FORM;
    }

    @RequestMapping(value = "/bookings", method = RequestMethod.GET)
    public String showAll( Map<String, Object> model) {

        Collection<Booking> results = this.clinicService.findAllBookings();       
        model.put("bookings", results);
        return "bookings/bookingsList";
    }
    
    @RequestMapping(value = "/bookings/{petId}/new", method = RequestMethod.POST)
    public String creationForm(@Valid Booking booking,@PathVariable("petId") int petId, BindingResult result) {
        Pet pet=clinicService.findPetById(petId);
        booking.setPet(pet);
        if (result.hasErrors()) {
            return VIEWS_BOOKING_CREATE_OR_UPDATE_FORM;
        } else {
            this.clinicService.saveBooking(booking);
            return "redirect:/bookings";
        }
    }
}
