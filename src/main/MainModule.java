package main;
import java.util.Scanner;
import controller.DetailsController;
import controller.DetailsInterface;
import exception.PatientNumberNotFoundException;
public class MainModule {
       public static void main(String[]args) {
    	   System.out.println("-----Welcome to Hospital Management System-----");
    	   DetailsInterface details=new DetailsController();
    	   Scanner s = new Scanner(System.in);
           char c;
           try {
        	   do {
        		   System.out.println("Enter your choice");
                   System.out.println("1. Get appointment details by Appointment ID");
                   System.out.println("2. Get appointment details by Patient ID");
                   System.out.println("3. Get appointment details by Doctor ID");
                   System.out.println("4. Schedule an Appointment");
                   System.out.println("5. Update Appointment Details ");
                   System.out.println("6. Cancel Appointment");
                   int choice = s.nextInt();
                   switch(choice) {
                   case 1: {
                       details.getAppointmentById();
                       break;
                    }
	               case 2: {
	                   try {
	                       details.getAppointmentsForPatient();
	                   } catch (PatientNumberNotFoundException e) {
	                       e.printStackTrace();
	                   }
	                   break;
	               }
	               case 3: {
	                   details.getAppointmentsForDoctor();
	                   break;
	               }
	               case 4: {
	                   details.scheduleAppointment();
	                   break;
	               }
	               case 5: {
	                   details.updateAppointment();
	                   break;
	               }
	               case 6: {
	                   details.cancelAppointment();
	                   break;
	               }
	               default: {
	                    System.out.println("Choose a proper choice");
	                    break;
	                }
                   
                   }
                   System.out.println("Do you want to continue? Y | y");
                   c = s.next().charAt(0);
        	   } while (c=='Y' || c=='y');
        	   System.out.println("------Thankyou for using the application. Have a nice day.------");
           }catch(Exception e) {
        	   e.printStackTrace();
           }finally {
        	   s.close();
           }       
       }
}
