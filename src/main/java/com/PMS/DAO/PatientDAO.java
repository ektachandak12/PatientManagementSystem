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

    public void getPatientByID(){


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

    public void deletePatient(){

    }
}
