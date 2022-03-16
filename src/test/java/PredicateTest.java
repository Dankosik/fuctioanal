import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Примеры использования {@link java.util.function.Predicate}
 */
public class PredicateTest {

    /**
     * {@link java.util.function.Predicate} - аналог методу который принимает один парамаетр типа как в дженерике и возвращает boolean
     * <p>
     * <p>
     * <p>
     * Проверяем что персон женского пола
     */
    Predicate<Person> isFemale = person -> person.getGender().equals(Gender.FEMALE);
    /**
     * Проверяем что имя персона "Maria"
     */
    Predicate<Person> isMaria = person -> "Maria".equals(person.getName());
    /**
     * Проверяем что список персонов содержит персона женксого пола с именем "Maria"
     */
    Predicate<List<Person>> containsMaria = personList -> personList.stream().anyMatch(isMaria.and(isFemale));

    /**
     * Пример императивного стиля
     * <p>
     * Из листа персонов хотим вывести персонов женского пола
     */
    @Test
    void imperative() {
        List<Person> people = List.of(new Person("John", Gender.MALE),
                new Person("Maria", Gender.FEMALE),
                new Person("Anna", Gender.FEMALE),
                new Person("Petr", Gender.MALE));

        List<Person> females = new ArrayList<>();

        for (Person person : people) {
            if (person.getGender().equals(Gender.FEMALE)) {
                females.add(person);
            }
        }
        System.out.println(females);
    }


    /**
     * Пример функционального стиля
     * <p>
     * Хотим вывести персонов женского пола с именем Maria
     * <p>
     * Для этого используем цепочку {@link java.util.function.Predicate} которую передаем в метод {@link  java.util.stream.Stream#filter(Predicate<? super T>) filter()}
     */
    @Test
    void declarativeFilterUsingPredicate() {
        List<Person> people = List.of(new Person("John", Gender.MALE),
                new Person("Maria", Gender.FEMALE),
                new Person("Anna", Gender.FEMALE),
                new Person("Petr", Gender.MALE));

        people.stream()
                .filter(person -> isMaria.and(isFemale).test(person))
                .forEach(System.out::println);

        /*
        Проверяем что список персонов содержит персона женксого пола с именем "Maria"
         */
        assertTrue(containsMaria.test(people));
    }

    /**
     * Пример функционального стиля
     * <p>
     * Хотим вывести персонов женского пола
     * <p>
     * {@link java.util.function.Predicate} можно передавать сразу в метод {@link  java.util.stream.Stream#filter(Predicate<? super T>) filter()} как лямбда выражение
     */
    @Test
    void declarativeFilter() {
        List<Person> people = List.of(new Person("John", Gender.MALE),
                new Person("Maria", Gender.FEMALE),
                new Person("Anna", Gender.FEMALE),
                new Person("Petr", Gender.MALE));

        people.stream()
                .filter(person -> person.getGender().equals(Gender.FEMALE))
                .forEach(System.out::println);
    }
}
