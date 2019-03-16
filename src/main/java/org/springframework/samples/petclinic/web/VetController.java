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
import org.springframework.samples.petclinic.model.Owner;
<<<<<<< HEAD
=======
import org.springframework.samples.petclinic.model.Pet;
>>>>>>> deleteSupport#11
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.model.Vets;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.stereotype.Controller;
<<<<<<< HEAD
import org.springframework.validation.BindingResult;
=======
import org.springframework.ui.ModelMap;
>>>>>>> deleteSupport#11
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Juergen Hoeller
 * @author Mark Fisher
 * @author Ken Krebs
 * @author Arjen Poutsma
 */
@Controller
public class VetController {

    private final ClinicService clinicService;


    @Autowired
    public VetController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @RequestMapping(value = { "/vets.html"})
    public String showVetList(Map<String, Object> model) {
        // Here we are returning an object of type 'Vets' rather than a collection of Vet objects
        // so it is simpler for Object-Xml mapping
        Vets vets = new Vets();
        vets.getVetList().addAll(this.clinicService.findVets());
        model.put("vets", vets);
        return "vets/vetList";
    }
    
    //Delete Vet
    @RequestMapping(value = "/vets/vetList/{vetId}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable("vetId") int vetId, ModelMap model) {
    		Vet v = this.clinicService.findVetById(vetId);
    		this.clinicService.deleteVet(v);
            return "redirect:/vets.html";
    }
    
    @RequestMapping(value = { "/vets.json", "/vets.xml"})
    public
    @ResponseBody
    Vets showResourcesVetList() {
        // Here we are returning an object of type 'Vets' rather than a collection of Vet objects
        // so it is simpler for JSon/Object mapping
        Vets vets = new Vets();
        vets.getVetList().addAll(this.clinicService.findVets());
        return vets;
    }
    @RequestMapping(value="/vets/create",method = RequestMethod.GET)
    public ModelAndView createView() {
    	Vet vet= new Vet();
    	ModelAndView mav = new ModelAndView("vets/create");
        mav.addObject("vet",vet);
        return mav;
    }
    
    @RequestMapping(value = "/vets/create", method = RequestMethod.POST)
    public String create(@Valid Vet vet, BindingResult result) {
        if (result.hasErrors()) {
            return "vets/create";
        } else {
            this.clinicService.saveVet(vet);
            return "/vets/vetList" ;
        }
    }

}
