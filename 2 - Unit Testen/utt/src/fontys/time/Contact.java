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
public class Contact {

    private String name;
    private ArrayList<Appointment> agenda;

    /**
     *
     * @param name - The name of this contact
     */
    public Contact(String name) {
        this.name = name;
        this.agenda = new ArrayList<>();
    }

    /**
     *
     * @return The name of this contact
     */
    public String getName() {
        return this.name;
    }

    /**
     *
     * @param a - The appointment that needs to be added
     * @return True if the appointment was successfully added, false if
     * duplicate or not
     */
    protected boolean addAppointment(Appointment a) {
        if (this.agenda.contains(a)) {
            return false;
        }
        return this.agenda.add(a);
    }

    /**
     *
     * @param a - The appointment that possibly needs to be removed
     */
    protected void removeAppointment(Appointment a) {
        this.agenda.remove(a);
    }

    /**
     *
     * @return A list of all the appointments in the Contacts agenda
     */
    public Iterator<Appointment> appointments() {
        return this.agenda.iterator();
    }

}
