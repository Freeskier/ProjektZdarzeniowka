package Models;

import Entities.Cart;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CartModel {
    private SessionFactory factory = HibernateUtil.getFactory();

    public void createCart(int orderId, String productName, String productCategory, String productBrand, int productPrice, int quantity){
        Transaction transaction = null;

        try(Session session = factory.openSession()){
            transaction = session.beginTransaction();

            Cart cart = new Cart();
            cart.setOrder_id(orderId);
            cart.setName(productName);
            cart.setCategory(productCategory);
            cart.setBrand(productBrand);
            cart.setPrice(productPrice);
            cart.setQuantity(quantity);
            session.save(cart);

            transaction.commit();
        } catch (Exception e){
            if(transaction != null)
                transaction.rollback();
        }
    }

    public List<Cart> findCartByName(String name) {
        Transaction transaction = null;
        List<Cart> cart = null;
        Session session = factory.openSession();
        transaction = session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Cart> query = builder.createQuery(Cart.class);
        Root<Cart> root = query.from(Cart.class);
        query.select(root).where(builder.equal(root.get("name"), name));
        Query<Cart> q = session.createQuery(query);
        List<Cart> list = q.getResultList();
        transaction.commit();
        return list;
    }

    public void removeCart(Cart cart) {
        Transaction transaction = null;
        Session session = factory.openSession();

        transaction = session.beginTransaction();
        session.remove(cart);
        transaction.commit();
    }

    public List<Cart> findCartByOrderId(String orderID) {

        Transaction transaction = null;
        List<Cart> cart = null;
        Session session = factory.openSession();
        transaction = session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Cart> query = builder.createQuery(Cart.class);
        Root<Cart> root = query.from(Cart.class);
        query.select(root).where(builder.equal(root.get("order_id"), orderID));
        Query<Cart> q = session.createQuery(query);
        List<Cart> list = q.getResultList();
        transaction.commit();
        return list;
    }

    public List<Cart> getCartList(){
        Session session = factory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Cart> criteria = builder.createQuery(Cart.class);
        criteria.from(Cart.class);
        List<Cart> data = session.createQuery(criteria).getResultList();
        return data;
    }

    public void setQuantity(Cart cart, int quantity) {

        Transaction transaction = null;

        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();

            cart.setQuantity(quantity);
            session.update(cart);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
        }
    }
}
