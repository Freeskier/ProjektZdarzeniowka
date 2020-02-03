package Models;

import Entities.Employee;
import Entities.Order;
import Entities.Product;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class OrderModel {
    private SessionFactory factory = HibernateUtil.getFactory();

    public void createOrder(String customerName, String employeeName, String date){
        Transaction transaction = null;

        try(Session session = factory.openSession()){
            transaction = session.beginTransaction();

            Order order = new Order();
            order.setCustomerName(customerName);
            order.setEmployeeName(employeeName);
            order.setDate(date);

            session.save(order);

            transaction.commit();
        } catch (Exception e){
            if(transaction != null)
                transaction.rollback();
        }
    }

    public int getHighestOrderValue(){
        Transaction transaction = null;
        int hov = 0;

        try(Session session = factory.openSession()){
            transaction = session.beginTransaction();

            Criteria criteria = session
                    .createCriteria(Order.class)
                    .setProjection(Projections.max("id"));
            Integer maxID = (Integer)criteria.uniqueResult();
            hov = maxID + 1;

        } catch (Exception e){
            if(transaction != null)
                transaction.rollback();
        }
        return hov;
    }

    public List<Order> getOrders() {
        Session session = factory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Order> criteria = builder.createQuery(Order.class);
        criteria.from(Order.class);
        List<Order> data = session.createQuery(criteria).getResultList();
        return data;
    }

    public Order findOrder(String name) {
        Transaction transaction = null;
        List<Order> product = null;
        Session session = factory.openSession();
        transaction = session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Order> query = builder.createQuery(Order.class);
        Root<Order> root = query.from(Order.class);
        query.select(root).where(builder.equal(root.get("id"), name));
        Query<Order> q = session.createQuery(query);
        Order list = q.getSingleResult();
        transaction.commit();
        return list;
    }

    public void removeOrder(Order order) {
        Transaction transaction = null;
        Session session = factory.openSession();

        transaction = session.beginTransaction();
        session.remove(order);
        transaction.commit();
    }
}
