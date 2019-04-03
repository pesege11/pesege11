/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author japarejo
 */
@Entity
@Table(name = "bookings")
public class Booking extends BaseEntity {
    
    
    @Column(name = "start")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate start;
    
    @Column(name = "finish")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate finish;

    
    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    /**
     * @return the start
     */
    public LocalDate getStart() {
        return start;
    }

    /**
     * @param start the start to set
     */
    public void setStart(LocalDate start) {
        this.start = start;
    }

    /**
     * @return the finish
     */
    public LocalDate getFinish() {
        return finish;
    }

    /**
     * @param finish the finish to set
     */
    public void setFinish(LocalDate finish) {
        this.finish = finish;
    }

    /**
     * @return the pet
     */
    public Pet getPet() {
        return pet;
    }

    /**
     * @param pet the pet to set
     */
    public void setPet(Pet pet) {
        this.pet = pet;
    }
    
    

}
