import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.AlreadyExistsException;
import ru.netology.NotFoundException;
import ru.netology.Product;
import ru.netology.ShopRepository;

public class ShopRepositoryTest {

    @Test
    public void ShouldRemoveById() {
        Product product1 = new Product(55, "молоко", 100);
        Product product2 = new Product(66, "кефир", 300);
        Product product3 = new Product(44, "шоколад", 430);
        ShopRepository repo = new ShopRepository();

        repo.add(product1);
        repo.add(product2);
        repo.add(product3);

        repo.remove(66);

        Product[] expected = {product1, product3};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void ShouldRemoveByIdDoesNotExist() {
        Product product1 = new Product(55, "молоко", 100);
        Product product2 = new Product(66, "кефир", 300);
        Product product3 = new Product(44, "шоколад", 430);
        ShopRepository repo = new ShopRepository();

        repo.add(product1);
        repo.add(product2);
        repo.add(product3);

        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.remove(9);
        });
    }

    @Test
    public void ShouldAddByIdAlreadyExists() {
        Product product1 = new Product(55, "молоко", 100);
        Product product2 = new Product(66, "кефир", 300);
        Product product3 = new Product(55, "шоколад", 430);
        ShopRepository repo = new ShopRepository();

        repo.add(product1);
        repo.add(product2);


        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repo.add(product3);
        });
    }
}
