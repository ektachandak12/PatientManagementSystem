package com.PMS.DAO;

import com.PMS.model.entity.Patient;
import com.PMS.model.util.FactoryProvider;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class PatientDAO {

    // ================= BMI LOGIC =================

    public double calculateBmi(double weight, double height) {

        if (height <= 0) {
            throw new IllegalArgumentException("Height must be greater than 0");
        }

        return Math.round((weight / (height * height)) * 100.0) / 100.0;
    }

    public String getBmiCategory(double bmi) {

        if (bmi < 18.5) return "Underweight";
        else if (bmi < 24.9) return "Normal";
        else if (bmi < 29.9) return "Overweight";
        else return "Obese";
    }

    // ================= ADD PATIENT =================

    public void addPatient(Patient patient) {

        Session session = FactoryProvider.getFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            // ================= BMI CALCULATION =================

            double bmi = calculateBmi(
                    patient.getWeight(),
                    patient.getHeight()
            );


            String category = getBmiCategory(bmi);

            // ================= SET VALUES IN ENTITY =================

            patient.setBmi(bmi);
            patient.setBmiCategory(category);

            // ================= SAVE TO DB =================

            session.persist(patient);

            tx.commit();

        } catch (Exception e) {

            if (tx != null) tx.rollback();

            e.printStackTrace();

        } finally {
            session.close();
        }
    }

    // ================= UPDATE PATIENT (HIBERNATE VERSION) =================

    public boolean updatePatient(Patient oldPatient, Patient newPatient) {

        Session session = FactoryProvider.getFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Patient dbPatient = session.get(Patient.class, oldPatient.getId());

            if (dbPatient == null) {
                return false;
            }

            boolean changed = false;

            // NAME
            if (!dbPatient.getName().equals(newPatient.getName())) {
                dbPatient.setName(newPatient.getName());
                changed = true;
            }

            // AGE
            if (dbPatient.getAge() != newPatient.getAge()) {
                dbPatient.setAge(newPatient.getAge());
                changed = true;
            }

            // GENDER
            if (!dbPatient.getGender().equals(newPatient.getGender())) {
                dbPatient.setGender(newPatient.getGender());
                changed = true;
            }

            // CITY
            if (!dbPatient.getCity().equals(newPatient.getCity())) {
                dbPatient.setCity(newPatient.getCity());
                changed = true;
            }

            boolean heightChanged =
                    Math.abs(dbPatient.getHeight() - newPatient.getHeight()) > 0.0001;

            boolean weightChanged =
                    Math.abs(dbPatient.getWeight() - newPatient.getWeight()) > 0.0001;

            if (heightChanged) {
                dbPatient.setHeight(newPatient.getHeight());
                changed = true;
            }

            if (weightChanged) {
                dbPatient.setWeight(newPatient.getWeight());
                changed = true;
            }

            // ================= BMI UPDATE =================
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

            // Hibernate auto-updates on commit (dirty checking)
            tx.commit();

            return changed;

        } catch (Exception e) {

            if (tx != null) tx.rollback();

            e.printStackTrace();

        } finally {
            session.close();
        }

        return false;
    }

    // ================= GET ALL PATIENTS =================

    public List<Patient> getAllPatients() {

        Session session = FactoryProvider.getFactory().openSession();

        try {
            Query<Patient> query =
                    session.createQuery("FROM Patient", Patient.class);

            return query.list();

        } finally {
            session.close();
        }
    }

    // ================= SEARCH PATIENT =================

    public Patient searchPatient(int searchID) {

        Session session = FactoryProvider.getFactory().openSession();

        try {
            return session.get(Patient.class, searchID);

        } finally {
            session.close();
        }
    }

    // ================= DELETE PATIENT =================

    public int deletePatient(int deleteID) {

        Session session = FactoryProvider.getFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Patient patient = session.get(Patient.class, deleteID);

            if (patient != null) {
                session.remove(patient);
                tx.commit();
                return 1;
            }

            return 0;

        } catch (Exception e) {

            if (tx != null) tx.rollback();

            e.printStackTrace();
            return 0;

        } finally {
            session.close();
        }
    }
}