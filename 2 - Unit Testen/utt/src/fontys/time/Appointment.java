/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fontys.time;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Ian
 */
public class Appointment {
    
    private final String subject;
    private final ITimeSpan timeSpan;
    private final ArrayList<Contact> invitees;

    /**
     *
     * @param subject - Subject bound to this appointment
     * @param timeSpan - Start/End time for this appointment
     */
    public Appointment(String subject, ITimeSpan timeSpan) {
        this.subject = subject;
        this.timeSpan = timeSpan;
        this.invitees = new ArrayList<>();
    }

    /**
     *
     * @return Subject of this appointment
     */
    public String getSubject() {
        return this.subject;
    }

    /**
     *
     * @return The set time span (start- end time) for this appointment
     */
    public ITimeSpan getTimeSpan() {
        return this.timeSpan;
    }

    /**
     *
     * @return All contacts bound to this Appointment
     */
    public Iterator<Contact> invitees() {
        return this.invitees.iterator();
    }

    /**
     *
     * @param c - The contact that needs to be added to this appointment
     * @return true if successfully added, false if duplicate or not added
     */
    public boolean addContact(Contact c) {
        if (this.invitees.contains(c)) {
            return false;
        }
        return this.invitees.add(c);
    }

    /**
     *
     * @param c - The contact that needs to be possibly removed
     */
    public void removeContact(Contact c) {
        this.invitees.remove(c);
    }
    
}
