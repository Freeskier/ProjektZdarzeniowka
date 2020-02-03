package Models;

import Entities.Employee;
import Entities.Product;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ProductModel {
    private SessionFactory factory = HibernateUtil.getFactory();

    public void createProduct(String name, String category, String brand, int price, int i) {
        int j;
        Transaction transaction = null;

        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();

            Product product = new Product();
            product.setName(name);
            product.setCategory(category);
            product.setBrand(brand);
            product.setPrice(price);
            product.setQuantity(i);

            session.save(product);


            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
        }
    }

    public List<Product> getProducts() {
        Session session = factory.openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
            criteria.from(Product.class);
            List<Product> data = session.createQuery(criteria).getResultList();
            return data;
    }

    public void removeProduct(Product product) {
        Transaction transaction = null;
        Session session = factory.openSession();

        transaction = session.beginTransaction();
        session.remove(product);
        transaction.commit();
    }

    public List<Product> findProduct(String name) {
        Transaction transaction = null;
        List<Product> product = null;
        Session session = factory.openSession();
        transaction = session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Product> query = builder.createQuery(Product.class);
        Root<Product> root = query.from(Product.class);
        query.select(root).where(builder.equal(root.get("name"), name));
        Query<Product> q = session.createQuery(query);
        List<Product> list = q.getResultList();
        transaction.commit();
        return list;
    }

    public void addProduct(Product product) {
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.save(product);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
        }
    }

    public void setQuantity(Product product, int quantity) {

        Transaction transaction = null;

        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();

            product.setQuantity(quantity);
            session.update(product);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
        }
    }


}
