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

import java.util.Collection;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cause;
import org.springframework.samples.petclinic.model.Donation;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
@RequestMapping("/causes/{causeId}")
public class DonationController {

    private static final String VIEWS_DONATIONS_CREATE_OR_UPDATE_FORM = "causes/createOrUpdateDonationForm";
    private final ClinicService clinicService;

    @Autowired
    public DonationController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }
    
    @ModelAttribute("cause")
    public Cause findCause(@PathVariable("causeId") int causeId) {
        return this.clinicService.findCauseById(causeId);
    }
    
    @RequestMapping(value = "/donations", method = RequestMethod.GET)
    public String showAll(Cause cause,Map<String, Object> model) {
    	Collection<Donation> resultados = this.clinicService.findDonationsByCauseId(cause.getId());       
        model.put("resultados", resultados);
        return "causes/donationsList";
    }

    @RequestMapping(value = "/donations/new", method = RequestMethod.GET)
    public String initCreationForm(Cause cause, ModelMap model) {
        Donation donation = new Donation();
        cause.addDonation(donation);
        Assert.isTrue(!(cause.getRemainingMoney()==0),"No puedes realizar una donación, la causa se ha cerrado");
        model.put("donation", donation);
        model.put("cause", cause);
        System.out.println(donation.getCause().getRemainingMoney());
        return VIEWS_DONATIONS_CREATE_OR_UPDATE_FORM;
    }

    @RequestMapping(value = "/donations/new", params="addNew", method = RequestMethod.POST)
    public String processCreationForm(Cause cause, @Valid Donation donation, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            model.put("donation", donation);
            model.put("cause", cause);
            return VIEWS_DONATIONS_CREATE_OR_UPDATE_FORM;
        } else {
        	Boolean res = donation.getAmount()>cause.getRemainingMoney();
        	Assert.isTrue(!res,"La cantidad introducida es mayor a la restante");
            cause.addDonation(donation);
            this.clinicService.saveDonation(donation);
            return "redirect:/causes/{causeId}";
        }
    }

}