package com.PMS.DAO;

import com.PMS.model.entity.Patient;
import com.PMS.model.util.FactoryProvider;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

/*
 * PatientDAO:
 * Handles all database operations related to patients.
 * Also manages BMI calculation and BMI category assignment.
 */
public class PatientDAO {

    // ================= BMI LOGIC =================

    // Calculates BMI using weight and height
    public double calculateBmi(double weight, double height) {

        // Prevent division by zero or invalid height
        if (height <= 0) {
            throw new IllegalArgumentException("Height must be greater than 0");
        }

        // Calculate BMI and round to 2 decimal places
        return Math.round((weight / (height * height)) * 100.0) / 100.0;
    }

    // Returns BMI category based on BMI value
    public String getBmiCategory(double bmi) {

        if (bmi < 18.5) return "Underweight";
        else if (bmi < 24.9) return "Normal";
        else if (bmi < 29.9) return "Overweight";
        else return "Obese";
    }

    // ================= ADD PATIENT =================

    // Saves a new patient record to the database
    public void addPatient(Patient patient) {

        // Open Hibernate session
        Session session = FactoryProvider.getFactory().openSession();

        Transaction tx = null;

        try {

            // Start database transaction
            tx = session.beginTransaction();

            // Calculate BMI before saving patient
            double bmi = calculateBmi(
                    patient.getWeight(),
                    patient.getHeight()
            );

            // Determine BMI category
            String category = getBmiCategory(bmi);

            // Store BMI details in patient object
            patient.setBmi(bmi);
            patient.setBmiCategory(category);

            // Save patient record
            session.persist(patient);

            // Permanently save changes
            tx.commit();

        } catch (Exception e) {

            // Undo changes if an error occurs
            if (tx != null) tx.rollback();

            e.printStackTrace();

        } finally {

            // Release database resources
            session.close();
        }
    }

    // ================= UPDATE PATIENT =================

    // Updates patient details if any changes are found
    public boolean updatePatient(Patient oldPatient, Patient newPatient) {

        Session session = FactoryProvider.getFactory().openSession();
        Transaction tx = null;

        try {

            // Start transaction
            tx = session.beginTransaction();

            // Fetch latest patient record from database
            Patient dbPatient = session.get(Patient.class, oldPatient.getId());

            // Return false if patient does not exist
            if (dbPatient == null) {
                return false;
            }

            // Tracks whether any field was modified
            boolean changed = false;

            // Update name if changed
            if (!dbPatient.getName().equals(newPatient.getName())) {
                dbPatient.setName(newPatient.getName());
                changed = true;
            }

            // Update age if changed
            if (dbPatient.getAge() != newPatient.getAge()) {
                dbPatient.setAge(newPatient.getAge());
                changed = true;
            }

            // Update gender if changed
            if (!dbPatient.getGender().equals(newPatient.getGender())) {
                dbPatient.setGender(newPatient.getGender());
                changed = true;
            }

            // Update city if changed
            if (!dbPatient.getCity().equals(newPatient.getCity())) {
                dbPatient.setCity(newPatient.getCity());
                changed = true;
            }

            // Check if height value changed
            boolean heightChanged =
                    Math.abs(dbPatient.getHeight() - newPatient.getHeight()) > 0.0001;

            // Check if weight value changed
            boolean weightChanged =
                    Math.abs(dbPatient.getWeight() - newPatient.getWeight()) > 0.0001;

            // Update height if changed
            if (heightChanged) {
                dbPatient.setHeight(newPatient.getHeight());
                changed = true;
            }

            // Update weight if changed
            if (weightChanged) {
                dbPatient.setWeight(newPatient.getWeight());
                changed = true;
            }

            // Recalculate BMI if height or weight changed
            if (heightChanged || weightChanged) {

                double bmi = calculateBmi(
                        dbPatient.getWeight(),
                        dbPatient.getHeight()
                );

                String category = getBmiCategory(bmi);

                dbPatient.setBmi(bmi);
                dbPatient.setBmiCategory(category);

                changed = true;
            }

            // Hibernate automatically updates modified fields
            tx.commit();

            return changed;

        } catch (Exception e) {

            // Undo changes if update fails
            if (tx != null) tx.rollback();

            e.printStackTrace();

        } finally {

            // Close session
            session.close();
        }

        return false;
    }

    // ================= GET ALL PATIENTS =================

    // Retrieves all patient records from database
    public List<Patient> getAllPatients() {

        Session session = FactoryProvider.getFactory().openSession();

        try {

            // HQL query to fetch all patients
            Query<Patient> query =
                    session.createQuery("FROM Patient", Patient.class);

            return query.list();

        } finally {

            session.close();
        }
    }

    // ================= SEARCH PATIENT =================

    // Finds a patient using patient ID
    public Patient searchPatient(int searchID) {

        Session session = FactoryProvider.getFactory().openSession();

        try {

            // Fetch patient by primary key
            return session.get(Patient.class, searchID);

        } finally {

            session.close();
        }
    }

    // ================= DELETE PATIENT =================

    // Deletes a patient record using patient ID
    public int deletePatient(int deleteID) {

        Session session = FactoryProvider.getFactory().openSession();
        Transaction tx = null;

        try {

            // Start transaction
            tx = session.beginTransaction();

            // Search patient before deleting
            Patient patient = session.get(Patient.class, deleteID);

            if (patient != null) {

                // Remove patient record
                session.remove(patient);

                // Save deletion permanently
                tx.commit();

                return 1;
            }

            // Patient not found
            return 0;

        } catch (Exception e) {

            // Undo deletion if an error occurs
            if (tx != null) tx.rollback();

            e.printStackTrace();

            return 0;

        } finally {

            // Close session
            session.close();
        }
    }
}