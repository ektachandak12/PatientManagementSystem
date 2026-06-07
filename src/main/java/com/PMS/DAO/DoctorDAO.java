package com.PMS.DAO;

import com.PMS.model.entity.Doctor;
import com.PMS.model.util.FactoryProvider;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/*
 * DoctorDAO:
 * Handles all database operations related to Doctor.
 * Uses Hibernate to save doctor records and verify login credentials.
 */
public class DoctorDAO {

    /*
     * Saves a new doctor record into the database.
     */
    public void saveDoctor(Doctor doctor){

        // Open a new Hibernate session
        Session s = FactoryProvider.getFactory().openSession();

        // Begin database transaction
        Transaction tx = s.beginTransaction();

        // Save doctor object into database
        s.persist(doctor);

        // Commit transaction to make changes permanent
        tx.commit();

        // Close session and release resources
        s.close();
    }

    /*
     * Checks whether a doctor exists with the given
     * username and password.
     *
     * Returns:
     * - Doctor object if credentials are valid
     * - null if no matching record is found
     */
    public Doctor loginDoctor(String username, String password){

        // Open a new Hibernate session
        Session s = FactoryProvider.getFactory().openSession();

        // HQL query to find doctor with matching credentials
        Query<Doctor> query = s.createQuery(
                "from Doctor where username=:username and password=:password",
                Doctor.class
        );

        // Set username parameter in query
        query.setParameter("username", username);

        // Set password parameter in query
        query.setParameter("password", password);

        // Fetch single matching doctor record
        Doctor doctor = query.uniqueResult();

        // Close session after database operation
        s.close();

        // Return doctor object if found, otherwise return null
        return doctor;
    }
}