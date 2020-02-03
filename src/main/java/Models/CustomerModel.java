package Models;


import Entities.Customer;
import Entities.Product;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CustomerModel {
    private SessionFactory factory = HibernateUtil.getFactory();

    public void createCustomer(String firstName, String lastName, String phoneNumber, String mail, String address){
        Transaction transaction = null;

        try(Session session = factory.openSession()){
            transaction = session.beginTransaction();

            Customer customer = new Customer();
            customer.setFirstName(firstName);
            customer.setLastName(lastName);
            customer.setPhoneNumber(phoneNumber);
            customer.setMail(mail);
            customer.setAddress(address);

            session.save(customer);

            transaction.commit();
        } catch (Exception e){
            if(transaction != null)
                transaction.rollback();
        }
    }

    public List<Customer> getCustomers() {
        Session session = factory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Customer> criteria = builder.createQuery(Customer.class);
        criteria.from(Customer.class);
        List<Customer> data = session.createQuery(criteria).getResultList();
        return data;
    }

    public void removeCustomers(Customer customer) {
        Transaction transaction = null;
        Session session = factory.openSession();

        transaction = session.beginTransaction();
        session.remove(customer);
        transaction.commit();
    }

    public List<Customer> getCustomerName() {
        Session session = factory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Customer> crt = cb.createQuery(Customer.class);
        Root<Customer> root = crt.from(Customer.class);
        crt.select(root.get("firstName"));
        Query query = session.createQuery(crt);
        List<Customer> collection = query.getResultList();
        return collection;
    }

}
