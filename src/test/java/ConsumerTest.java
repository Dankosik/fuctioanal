import org.junit.jupiter.api.Test;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * Примеры использования {@link java.util.function.Consumer} и {@link java.util.function.BiConsumer}
 */
public class ConsumerTest {
    /**
     * {@link java.util.function.Consumer} - аналог void методу который принимает один аргумент
     */
    Consumer<Person> declarativeGreeting = person -> System.out.println("Thanks for registering " + person.getName());
    /**
     * {@link java.util.function.BiConsumer} - аналог void методу который принимает два аргумента
     */
    BiConsumer<Person, Boolean> declarativeGreetingWithGender = (person, showGender) ->
            System.out.println("Thanks for registering " + person.getName() + (showGender ? " gender " + person.getGender() : ""));


    /**
     * Пример императивного стиля
     */
    @Test
    void imperativeGreeting() {
        Person person = new Person("John", Gender.MALE);
        greeting(person);
    }

    /**
     * Пример функционального стиля используя {@link java.util.function.Consumer}
     */
    @Test
    void declarativeGreeting() {
        Person person = new Person("John", Gender.MALE);
        declarativeGreeting.accept(person);
    }

    /**
     * Пример функционального стиля используя {@link java.util.function.BiConsumer}
     */
    @Test
    void declarativeGreetingHideGender() {
        Person person = new Person("John", Gender.MALE);
        declarativeGreetingWithGender.accept(person, true);
        declarativeGreetingWithGender.accept(person, false);
    }

    /**
     * Мы можем выстраивать цепочки вызовов различных {@link java.util.function.Consumer} и {@link java.util.function.BiConsumer}
     */
    @Test
    void declarativeChain() {
        Person person = new Person("John", Gender.MALE);
        declarativeGreetingWithGender
                .andThen(declarativeGreetingWithGender)
                .accept(person, true);
        declarativeGreeting
                .andThen(declarativeGreeting)
                .accept(person);
    }

    void greeting(Person person) {
        System.out.println("Thanks for registering " + person.getName());
    }
}
