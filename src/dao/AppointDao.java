package dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import entity.Appointment;
import util.DBConnUtil;
import util.DBPropertyUtil;
public class AppointDao {
	static Connection con;
	Statement statement;
	PreparedStatement preparedstatement;
	ResultSet resultSet;
	private static void getCon()
	{
		if(con==null)
		con=DBConnUtil.getConnection(DBPropertyUtil.getConnString("db.properties"));
	}
	public Appointment getAppointmentById(int appointmentId) {
		Appointment appointment = null;
		try {
			getCon();
			String sql = "Select * From Appointment where appointmentId = ?";
			try (PreparedStatement preparedStatement = con.prepareStatement(sql)){
				preparedStatement.setInt(1, appointmentId);
				try (ResultSet rs = preparedStatement.executeQuery()){
					if (rs.next()) {
		                appointment = new Appointment();
		                    appointment.setAppointmentId(rs.getInt("appointmentId"));
		                    appointment.setPatientId(rs.getInt("patientId"));
		                    appointment.setDoctorId(rs.getInt("doctorId"));
		                    appointment.setAppointmentDate(rs.getDate("appointmentDate"));
		                    appointment.setDescription(rs.getString("description"));
		            }
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return appointment;
	}
	 public List<Appointment> getAppointmentsForPatient(int patientId){
		 List<Appointment> appointments = new ArrayList<>();
		 try {
			 getCon();
			 PreparedStatement ps = con.prepareStatement("SELECT * FROM Appointment WHERE patientId = ?");
	            ps.setInt(1, patientId);
	            ResultSet rs = ps.executeQuery();
	            while (rs.next()) {
	                Appointment appointment = new Appointment(
	                    rs.getInt("appointmentId"),
	                    rs.getInt("patientId"),
	                    rs.getInt("doctorId"),
	                    rs.getDate("appointmentDate"),
	                    rs.getString("description")
	                );
	                appointments.add(appointment);
		        }
	          }catch(Exception e) {
		            e.printStackTrace();
	          }
               return appointments;
     }
	 public List<Appointment> getAppointmentsForDoctor(int doctorId){
		  List<Appointment> appointments = new ArrayList<>();
		  try {
			  getCon();
			  PreparedStatement ps = con.prepareStatement("SELECT * FROM Appointment WHERE doctorId = ?");
	            ps.setInt(1, doctorId);
	            ResultSet rs = ps.executeQuery();
	            while (rs.next()) {
	                Appointment appointment = new Appointment(
	                    rs.getInt("appointmentId"),
	                    rs.getInt("patientId"),
	                    rs.getInt("doctorId"),
	                    rs.getDate("appointmentDate"),
	                    rs.getString("description")
	                );
	                appointments.add(appointment);
	            }
		  }catch(Exception e) {
			  e.printStackTrace();
		  }
		  return appointments;
	 }
	 public boolean scheduleAppointment(Appointment appointment) {
		 try {
			  getCon();
			  PreparedStatement ps = con.prepareStatement("INSERT INTO Appointment (appointmentId, patientId, doctorId, appointmentDate, description) VALUES (?, ?, ?, ?, ?)");
	            ps.setInt(1, appointment.getAppointmentId());
	            ps.setInt(2, appointment.getPatientId());
	            ps.setInt(3, appointment.getDoctorId());
	            ps.setDate(4, new java.sql.Date(appointment.getAppointmentDate().getTime()));
	            ps.setString(5, appointment.getDescription());
	            return ps.executeUpdate() > 0;

		 }catch(Exception e) {
			 e.printStackTrace();
			 return false;
		 }
	 }
	 public boolean updateAppointment(Appointment appointment) {
		 try {
			 getCon();
			 PreparedStatement ps = con.prepareStatement("UPDATE Appointment SET patientId = ?, doctorId = ?, appointmentDate = ?, description = ? WHERE appointmentId = ?");
			 ps.setInt(1, appointment.getAppointmentId());
	         ps.setInt(2, appointment.getPatientId());
	         ps.setInt(3, appointment.getDoctorId());
	         ps.setDate(4, new java.sql.Date(appointment.getAppointmentDate().getTime()));
	         ps.setString(5, appointment.getDescription());
	         return ps.executeUpdate() > 0;
		 }catch(Exception e) {
			 e.printStackTrace();
			 return false;
		 }
	 }
	 public boolean cancelAppointment(int appointmentId) {
		 try {
			 getCon();
			 PreparedStatement ps = con.prepareStatement("DELETE FROM Appointment WHERE appointmentId = ?");
	         ps.setInt(1, appointmentId);
	         return ps.executeUpdate() > 0;
		 }catch(Exception e) {
			 e.printStackTrace();
			 return false;
		 }
	 }
}