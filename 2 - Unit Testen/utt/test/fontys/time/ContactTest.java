/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fontys.time;

import fontys.time.Appointment;
import fontys.time.Contact;
import fontys.time.Time;
import fontys.time.TimeSpan;
import fontys.time.TimeSpan2;
import java.util.NoSuchElementException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Ian
 */
public class ContactTest {
    
    private String name;
    private Contact contact;
    
    @Before
    public void before() {
        this.name = "Ian";
        this.contact = new Contact(this.name);
    }
    
    @Test
    public void getNameTest() {
        Assert.assertEquals(this.contact.getName(), this.name);
    }
    
    @Test(expected = NoSuchElementException.class)
    public void addRemoveAppointmentsTest() {
        Appointment appointment = new Appointment("Test", new TimeSpan2(new Time(2017, 1, 1, 1, 1), new Time(2017, 1, 1, 1, 2)));
        Assert.assertTrue(this.contact.addAppointment(appointment));
        Assert.assertFalse(this.contact.addAppointment(appointment));
        Assert.assertEquals(this.contact.appointments().next(), appointment);
        this.contact.removeAppointment(appointment);
        this.contact.appointments().next();
    }
    
}
