import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

/**
 * Пример использования {@link java.util.function.Supplier}
 */
public class SupplierTest {

    /**
     * {@link java.util.function.Supplier} - аналог методу который не принимает аргументов и возвращает
     * значение типа как в дженерике
     */
    Supplier<Person> getMaria = () -> new Person("Maria", Gender.FEMALE);


    /**
     * Пример императивного стиля
     */
    @Test
    void imperativeFilterUsingPredicate() {
        System.out.println(new Person("Maria", Gender.FEMALE));
    }

    /**
     * Пример функционального стиля используя {@link java.util.function.Supplier}
     */
    @Test
    void declarativeFilterUsingPredicate() {
        System.out.println(getMaria.get());
    }
}
