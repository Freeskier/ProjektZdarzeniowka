package Models;

import Entities.Employee;
import Entities.Product;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class EmployeeModel {
    private SessionFactory factory = HibernateUtil.getFactory();

    public void createEmployee(String user, String password, String mail, String firstName, String lastName, String phoneNumber){
        Transaction transaction = null;

        try(Session session = factory.openSession()){
            transaction = session.beginTransaction();

            Employee employee = new Employee();
            employee.setUser(user);
            employee.setPassword(password);
            employee.setMail(mail);
            employee.setFirstName(firstName);
            employee.setLastName(lastName);
            employee.setPhoneNumber(phoneNumber);

            session.save(employee);

            transaction.commit();
        } catch (Exception e){
            if(transaction != null)
                transaction.rollback();
        }
    }

    public Employee getEmployeeCredentials(String user, String password){
        Employee employee = null;
        Transaction transaction = null;

        try(Session session = factory.openSession()){
            transaction = session.beginTransaction();

            employee = (Employee) session.createQuery("from Employee WHERE user = :user AND password = :password")
                    .setParameter("user", user)
                    .setParameter("password", password)
                    .getSingleResult();

            transaction.commit();
        }catch (Exception e){
            if(transaction != null)
                transaction.rollback();
        }

        return employee;
    }

    public void updateEmployee(Employee employee, String user, String firstName, String phone, String mail, String lastName){
        Transaction transaction = null;
        int id = employee.getId();

        try(Session session = factory.openSession()){
            transaction = session.beginTransaction();

            employee = (Employee) session.createQuery("from Employee WHERE id = :id")
                    .setParameter("id", id)
                    .getSingleResult();
            employee.setUser(user);
            employee.setFirstName(firstName);
            employee.setPhoneNumber(phone);
            employee.setMail(mail);
            employee.setLastName(lastName);

            session.update(employee);

            transaction.commit();
        }catch (Exception e){
            if(transaction != null)
                transaction.rollback();
        }
    }

    public List<Employee> getEmployeList(){
        Session session = factory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Employee> criteria = builder.createQuery(Employee.class);
        criteria.from(Employee.class);
        List<Employee> data = session.createQuery(criteria).getResultList();
        return data;
    }

    public void removeEmploye(Employee employee) {
        Transaction transaction = null;
        Session session = factory.openSession();
        transaction = session.beginTransaction();
        session.remove(employee);
        transaction.commit();
    }
}
