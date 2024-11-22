package com.nov21;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Initialize Hibernate session
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(VotingCard.class)
                .addAnnotatedClass(Address.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();
        Transaction transaction = null;

        try {
            // Begin transaction
            transaction = session.beginTransaction();

            // Create Address and VotingCards
            Address kpune = new Address(411052, "Karve Pune");
            VotingCard tms = new VotingCard("TMS1234", "Kothrud - Pune");
            VotingCard ars = new VotingCard("ARS1234", "Kothrud - Pune");

            // Case 1: Check for duplicate voter ID and add the address
            if (!isVoterIdAssigned(tms.getVoterId(), session)) {
                // Save address first to ensure foreign key integrity
                session.save(kpune);

                Person p1 = new Person(6242, "Tejas Saraf", tms, Arrays.asList(kpune));
                Person p2 = new Person(1232, "Atul Raut", tms, Arrays.asList(kpune)); // Duplicate voterId

                try {
                    // Check for duplicate voterId
                    if (isVoterIdAssigned(tms.getVoterId(), session)) {
                        throw new Exception("Data Not Inserted. Cannot Assign Similar voterID.");
                    }

                    session.save(p1);
                    session.save(p2); // Should not be inserted
                    System.out.println("Data Inserted Successfully.");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            // Case 2: Valid input, no duplicate voter ID
            if (!isVoterIdAssigned(ars.getVoterId(), session)) {
                Person p3 = new Person(1001, "John Doe", ars, Arrays.asList(kpune));
                session.save(p3);
                System.out.println("Data Inserted Successfully.");
            }

            // Case 3: Assign the same address to multiple persons
            Person p4 = new Person(2002, "Kiran Digrase", ars, Arrays.asList(kpune));
            Person p5 = new Person(3003, "Ravi Shinde", ars, Arrays.asList(kpune));

            kpune.setPersons(Arrays.asList(p4, p5)); // Address linked with multiple persons
            session.save(p4);
            session.save(p5);
            System.out.println("Data Inserted Successfully. Multiple Persons Assigned to the Same Address.");

            // Commit transaction
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }
    }

    private static boolean isVoterIdAssigned(String voterId, Session session) {
        // Check if the VoterId is already assigned to another person
        List<VotingCard> existingVotingCards = session.createQuery("from VotingCard where voterId = :voterId")
                .setParameter("voterId", voterId)
                .getResultList();
        return !existingVotingCards.isEmpty();
    }
}
