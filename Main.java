import org.hibernate.Session;
import com.github.javafaker.Faker;

public class Main {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        // Generate fake data
        Faker faker = new Faker();
        
        User user = new User();
        user.setName(faker.name().fullName());
        session.save(user);

        Product product1 = new Product();
        product1.setName(faker.commerce().productName());
        session.save(product1);

        Product product2 = new Product();
        product2.setName(faker.commerce().productName());
        session.save(product2);

        Order order = new Order();
        order.setOrderNumber(faker.number().digits(8));
        order.setUser(user);

        Set<Product> products = new HashSet<>();
        products.add(product1);
        products.add(product2);
        order.setProducts(products);

        session.save(order);
        session.getTransaction().commit();
        session.close();
        
        HibernateUtil.shutdown();
    }
}
