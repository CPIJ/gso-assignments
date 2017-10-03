/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fontys.time;

import java.util.NoSuchElementException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Ian
 */
public class AppointmentTest {
    
    private ITimeSpan timeSpan;
    private Appointment appointment;
    private String subject;
    
    @Before
    public void before() {
        this.subject = "Test 2";
        this.timeSpan = new TimeSpan2(new Time(2017, 1, 1, 1, 1), new Time(2017, 1, 1, 1, 2));
        this.appointment = new Appointment(this.subject, this.timeSpan);
    }
    
    @Test
    public void getSubjectTest() {
        Assert.assertEquals(this.appointment.getSubject(), this.subject);
    }
    
    @Test
    public void getTimeSpanTest() {
        Assert.assertEquals(this.appointment.getTimeSpan(), this.timeSpan);
    }
    
    @Test(expected = NoSuchElementException.class)
    public void addRemoveInviteeTest() {
        Contact c = new Contact("John");
        Assert.assertTrue(this.appointment.addContact(c));
        Assert.assertFalse(this.appointment.addContact(c));
        Assert.assertEquals(c, this.appointment.invitees().next());
        this.appointment.removeContact(c);
        this.appointment.invitees().next();
    }
    
}
