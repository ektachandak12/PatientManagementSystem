package com.PMS.DAO;

import com.PMS.model.entity.Patient;
import com.PMS.model.util.FactoryProvider;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class PatientDAO {
    public void addPatient(Patient patient) {

        Session s = FactoryProvider.getFactory().openSession();

        Transaction tx = null;

        try {

            tx = s.beginTransaction();

            s.persist(patient);

            tx.commit();

        } catch (Exception e) {

            if (tx != null) {
                tx.rollback();
            }

            e.printStackTrace();

        } finally {

            s.close();
        }
    }

    public void updatePatient(){

    }

    public void viewAllPatients(){

    }

    public Patient searchPatient(int searchID){
        Session s = FactoryProvider.getFactory().openSession();

        Query<Patient> query = s.createQuery(
                "FROM Patient where id = :searchID",
                Patient.class
        );

        query.setParameter("searchID", searchID);

        Patient patient  = query.uniqueResult();

        s.close();

        return patient;

    }

    public int deletePatient(int deleteID) {

        Session s = FactoryProvider.getFactory().openSession();

        Transaction tx = s.beginTransaction();

        Query query = s.createQuery(
                "DELETE FROM Patient WHERE id = :deleteID"
        );

        query.setParameter("deleteID", deleteID);

        int rowsAffected = query.executeUpdate();

        tx.commit();

        s.close();

        return rowsAffected;
    }
}
