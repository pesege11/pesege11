package org.springframework.samples.petclinic.web;

import java.util.Collection;


import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cause;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CauseController {
	
	 private static final String VIEWS_CAUSE_CREATE_OR_UPDATE_FORM = "causes/createOrUpdateCauseForm";
	    private final ClinicService clinicService;

	    @Autowired
	    public CauseController(ClinicService clinicService) {
	        this.clinicService = clinicService;
	    }

	    @RequestMapping(value = "/causes/new", method = RequestMethod.GET)
	    public String initCreationForm(Map<String, Object> model) {
	        Cause cause = new Cause();
	        model.put("cause", cause);
	        return VIEWS_CAUSE_CREATE_OR_UPDATE_FORM;
	    }

	    @RequestMapping(value = "/causes", method = RequestMethod.GET)
	    public String showAll( Map<String, Object> model) {
	    	Collection<Cause> resultados = this.clinicService.findAllCauses();       
	        model.put("resultados", resultados);
	        return "causes/causesList";
	    }
	    
	    @RequestMapping(value = "/causes/new", method = RequestMethod.POST)
	    public String processCreationForm(@Valid Cause cause, BindingResult result) {
	        if (result.hasErrors()) {
	            return VIEWS_CAUSE_CREATE_OR_UPDATE_FORM;
	        } else {
	            this.clinicService.saveCause(cause);
	            return "redirect:/causes/" + cause.getId();
	        }
	    }
	    
	    @RequestMapping("/causes/{causeId}")
	    public ModelAndView showCause(@PathVariable("causeId") int causeId) {
	        ModelAndView mav = new ModelAndView("causes/causesDetails");
	        mav.addObject(this.clinicService.findCauseById(causeId));
	        return mav;
	    }
	    
	    
	    @RequestMapping(value = "/causes/{causeId}/delete", method = RequestMethod.GET)
	    public String processDeleteForm(@PathVariable("causeId") int causeId) {
	        this.clinicService.deleteCause(causeId);
	           return "redirect:/causes";
	        }

}
