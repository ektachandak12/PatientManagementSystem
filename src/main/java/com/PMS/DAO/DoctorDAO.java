package com.PMS.DAO;

import com.PMS.model.entity.Doctor;
import com.PMS.model.util.FactoryProvider;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class DoctorDAO {

    public void saveDoctor(Doctor doctor){

        Session s = FactoryProvider.getFactory().openSession();

        Transaction tx = s.beginTransaction();

        s.save(doctor);

        tx.commit();

        s.close();
    }

    public Doctor loginDoctor(String username, String password){

        Session s = FactoryProvider.getFactory().openSession();

        Query<Doctor> query = s.createQuery(
                "from Doctor where username=:username and password=:password",
                Doctor.class
        );

        query.setParameter("username", username);
        query.setParameter("password", password);

        Doctor doctor = query.uniqueResult();

        s.close();

        return doctor;
    }
}