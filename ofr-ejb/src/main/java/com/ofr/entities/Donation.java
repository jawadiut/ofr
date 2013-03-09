package com.ofr.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 * User: imon
 * Date: 2/17/13
 * Time: 4:49 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Donation {
    @Id
    @GeneratedValue
    private String donationId;

    @NotNull
    @Min(value = 1)
    private int amount;


}
