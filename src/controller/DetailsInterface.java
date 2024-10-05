package controller;
import exception.PatientNumberNotFoundException;
public interface DetailsInterface {
	void getAppointmentById();
	void getAppointmentsForPatient() throws PatientNumberNotFoundException;
    void getAppointmentsForDoctor();
    void scheduleAppointment();
    void updateAppointment();
    void cancelAppointment();
}
