package exception;

public class PatientNumberNotFoundException extends Exception {
	public String toString() {
		return "Patient Id not found in the database.";
	}
}
