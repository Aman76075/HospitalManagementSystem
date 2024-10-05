package controller;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;
import dao.AppointDao;
import entity.*;
import exception.PatientNumberNotFoundException;
public class DetailsController implements DetailsInterface {
	public AppointDao appoint;
	public Scanner s=new Scanner(System.in);
	private Date parseDate(String dateStr)throws ParseException{
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date utilDate=dateFormat.parse(dateStr);
		return new java.sql.Date(utilDate.getTime());
	}
	public void getAppointmentById() {
		appoint=new AppointDao();
		System.out.println("Enter the Appointment Id:");
		int appointmentId=s.nextInt();
		Appointment getdetails=appoint.getAppointmentById(appointmentId);
		if(getdetails!=null) {
			System.out.println(getdetails);
		}
		else {
			System.out.println("Appointment Details Not Found.");
		}
	}
	public void getAppointmentsForPatient() throws PatientNumberNotFoundException{
		try {
			appoint=new AppointDao();
			System.out.println("Enter the patient Id:");
			int patientId=s.nextInt();
			List<Appointment> appointmentList=appoint.getAppointmentsForPatient(patientId);
			if(appointmentList.isEmpty()) {
				throw new PatientNumberNotFoundException();
			}
			else {
				System.out.println("Details of patient id "+patientId+" are:");
				for(Appointment details:appointmentList) {
					System.out.println(details);
				}
			}
		}catch(PatientNumberNotFoundException e) {
			System.out.println(e);
		}
	}
	public void getAppointmentsForDoctor() {
		try {
			appoint=new AppointDao();
			System.out.println("Enter the Doctor Id:");
			int doctorId=s.nextInt();
			List<Appointment> appointmentList=appoint.getAppointmentsForDoctor(doctorId);
			if(appointmentList.isEmpty()) {
				System.out.println("Details with doctor id not found.");
			}
			else {
				System.out.println("Details of Doctor id "+doctorId+" are:");
				for(Appointment details:appointmentList) {
					System.out.println(details);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void scheduleAppointment() {
		try {
		appoint=new AppointDao();
		System.out.println("Enter the appointment id:");
		int appointmentId=s.nextInt();
		System.out.println("Enter the patient id: ");
		int patientId=s.nextInt();
		System.out.println("Enter the doctor id:");
		int doctorId=s.nextInt();
		System.out.println("Enter the appointmentdate (in yyyy-mm-dd format): ");
		String appointmentDateStr=s.next();
		Date appointmentDate=parseDate(appointmentDateStr);
		s.nextLine();
		
	    System.out.println("Enter the description:");
	    String description = s.nextLine();
	    Appointment appointm=new Appointment();
	    appointm.setAppointmentId(appointmentId);
	    appointm.setPatientId(patientId);
	    appointm.setDoctorId(doctorId);
	    appointm.setAppointmentDate(appointmentDate);
	    appointm.setDescription(description);
	    
	    if(appoint.scheduleAppointment(appointm)) {
	    	System.out.println("Appoint Scheduled Successfully.");
	    }
	    else {
	    	System.out.println("Sorry! Appointment Scheduling Failed.");
	    }
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void updateAppointment() {
		try {
			appoint=new AppointDao();
			System.out.println("Enter the appointment id:");
			int appointmentId=s.nextInt();
			System.out.println("Enter the new patient id: ");
			int patientId=s.nextInt();
			System.out.println("Enter the new doctor id:");
			int doctorId=s.nextInt();
			System.out.println("Enter the new appointmentdate (in yyyy-mm-dd format): ");
			String appointmentDateStr=s.next();
			Date appointmentDate=parseDate(appointmentDateStr);
			s.nextLine();
			
		    System.out.println("Enter the new description:");
		    String description = s.nextLine();
		    Appointment appointm=new Appointment();
		    appointm.setAppointmentId(appointmentId);
		    appointm.setPatientId(patientId);
		    appointm.setDoctorId(doctorId);
		    appointm.setAppointmentDate(appointmentDate);
		    appointm.setDescription(description);
		    
		    if(appoint.updateAppointment(appointm)) {
		    	System.out.println("Appointment Updated Successfully.");
		    }
		    else {
		    	System.out.println("Sorry! Appointment Updation Failed.");
		    }
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public void cancelAppointment() {
		try {
			appoint=new AppointDao();
			System.out.println("Enter the appointment id to be cancelled.");
			int appointmentId=s.nextInt();
			
			if(appoint.cancelAppointment(appointmentId)) {
				System.out.println("Appointment Deletion Successful.");
			}
			else {
				System.out.println("Sorry Not Successful.");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
