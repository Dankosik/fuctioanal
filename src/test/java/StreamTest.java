import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Пример {@link java.util.stream.Stream}
 */
public class StreamTest {

    /**
     * Начальный список персонов
     */
    List<Person> people = List.of(new Person("John", Gender.MALE),
            new Person("Maria", Gender.FEMALE),
            new Person("Anna", Gender.FEMALE),
            new Person("Petr", Gender.MALE));


    /**
     * Вывод имен персонов используя {@link java.util.stream.Stream#map(Function) map()}
     */
    @Test
    void map() {
        people.stream()
                .map(Person::getName)
                .forEach(System.out::println);
    }

    /**
     * Вывод имен персонов которые начинаются с заглавной буквы "A" {@link java.util.stream.Stream#filter(Predicate) filter()}
     * <p>
     * .collect(Collectors.toList()); - позволяет создать список перснонов где будет результат операций выполняемых над стримом
     */
    @Test
    void filter() {
        List<Person> result = this.people.stream()
                .filter(person -> person.getName().startsWith("A"))
                .collect(Collectors.toList());

        System.out.println(result);
    }

    /**
     * Вывод имен персонов, пропуская первые два персона, используя {@link java.util.stream.Stream#skip(long) skip()}
     */
    @Test
    void skip() {
        List<String> names = people.stream()
                .skip(2)
                .map(Person::getName)
                .collect(Collectors.toList());

        System.out.println(names);
    }

    /**
     * Вывод уникальных персонов, используя  {@link Stream#distinct() distinct()}
     */
    @Test
    void distinct() {
        List<Person> people = List.of(new Person("John", Gender.MALE),
                new Person("Maria", Gender.FEMALE),
                new Person("Maria", Gender.FEMALE),
                new Person("Anna", Gender.FEMALE),
                new Person("Anna", Gender.FEMALE),
                new Person("Petr", Gender.MALE));

        System.out.println(people);

        people = people.stream()
                .distinct()
                .collect(Collectors.toList());

        System.out.println(people);
    }


    /**
     * {@link Stream#peek(Consumer)  peek()} - Промежуточная операция, не вляиет на результат
     */
    @Test
    void peek() {
        List<String> names = people.stream()
                .map(Person::getName)
                .peek(name -> System.out.println(name.toUpperCase()))
                .collect(Collectors.toList());

        System.out.println(names);
    }

    /**
     * Выводим список имен первых двух перонов в апер кейсе, используя {@link Stream#limit(long) limit()}
     */
    @Test
    void limit() {
        List<String> names = people.stream()
                .limit(2)
                .map(person -> person.getName().toUpperCase())
                .collect(Collectors.toList());

        System.out.println(names);
    }

    /**
     * Выводим список имен персонов в алфавитном порядке, используя {@link Stream#sorted() sorted()}
     */
    @Test
    void sorted() {
        List<String> names = people.stream()
                .map(Person::getName)
                .sorted()
                .collect(Collectors.toList());

        System.out.println(names);
    }

    /**
     * Выводим список имен всех java разработчиков
     */
    @Test
    void flatMap() {
        Map<String, List<Person>> people = Map.of(
                "Java", List.of(new Person("John", Gender.MALE), new Person("Petr", Gender.MALE)),
                "C#", List.of(new Person("Ivan", Gender.MALE), new Person("Daria", Gender.FEMALE)),
                "JS", List.of(new Person("Maria", Gender.FEMALE), new Person("Anna", Gender.FEMALE))
        );


        List<String> javaDevs = people.entrySet().stream()
                .filter(entry -> entry.getKey().equals("Java"))
                .map(Map.Entry::getValue)
                .flatMap(Collection::stream)
                .map(Person::getName)
                .collect(Collectors.toList());
        System.out.println(javaDevs);
    }

    /**
     * Выводим количество разработичков, используя {@link Stream#mapToLong(ToLongFunction) mapToLong()}
     * и {@link LongStream#sum() sum()}
     */
    @Test
    void flatMap2() {
        Map<String, List<Person>> people = Map.of(
                "Java", List.of(new Person("John", Gender.MALE), new Person("Petr", Gender.MALE)),
                "C#", List.of(new Person("Ivan", Gender.MALE), new Person("Daria", Gender.FEMALE)),
                "JS", List.of(new Person("Maria", Gender.FEMALE), new Person("Anna", Gender.FEMALE))
        );

        long devsCount = people.values().stream()
                .mapToLong(Collection::size)
                .sum();
        System.out.println(devsCount);
    }

    /**
     * Выводим список имен всех разработичков используя  {@link Stream#flatMap(Function) flatMap()}
     * чтобы преобразовать несколько списков разработичков в один
     */
    @Test
    void flatMap3() {
        Map<String, List<Person>> people = Map.of(
                "Java", List.of(new Person("John", Gender.MALE), new Person("Petr", Gender.MALE)),
                "C#", List.of(new Person("Ivan", Gender.MALE), new Person("Daria", Gender.FEMALE)),
                "JS", List.of(new Person("Maria", Gender.FEMALE), new Person("Anna", Gender.FEMALE))
        );

        List<String> names = people.values().stream()
                .flatMap(Collection::stream)
                .map(Person::getName)
                .sorted()
                .collect(Collectors.toList());

        System.out.println(names);
    }

    /**
     * Чтобы найти первого персона мужского пола который попадется использем {@link Stream#findFirst() findFirst()},
     * <p>
     * если такого не найдется будет выброшено RuntimeException
     */
    @Test
    void findFirst() {
        Person first = people.stream()
                .filter(person -> person.getGender().equals(Gender.MALE))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Person with gender " + Gender.MALE + " is not found"));
        System.out.println(first);
    }

    /**
     * Чтобы найти любого персона мужского использем {@link Stream#findAny() findAny()},
     * <p>
     * если такого не найдется будет выброшено RuntimeException
     */
    @Test
    void findAny() {
        Person first = people.stream()
                .filter(person -> person.getGender().equals(Gender.MALE))
                .findAny()
                .orElseThrow(() -> new RuntimeException("Person with gender " + Gender.MALE + " is not found"));
        System.out.println(first);
    }

    /**
     * Выводим количество персонов мужского пола, ипользуя {@link Stream#count() count()}
     */
    @Test
    void count() {
        long malesCount = people.stream()
                .filter(person -> person.getGender().equals(Gender.MALE))
                .count();
        System.out.println(malesCount);
    }

    /**
     * Добавляем к имени каждого персона "foreach", для этого используем {@link Stream#forEach(Consumer) forEach()}
     */
    @Test
    void forEach() {
        people.forEach(person -> person.setName(person.getName() + " foreach"));
        System.out.println(people);
    }


    /**
     * Пример, где мы создаем персона с именем склеиным из иимен всех персонов, используя {@link Stream#reduce(BinaryOperator) reduce()}
     */
    @Test
    void reduce() {
        /*
        так как здесь reduce() начинается с пустого персона new Person(), то именя будет начинаться с null
         */
        Person person = people.stream()
                .reduce(new Person(), (i1, i2) -> new Person(i1.getName() + i2.getName(), Gender.MALE));
        System.out.println(person);

        Optional<Person> person2 = people.stream()
                .reduce((i1, i2) -> new Person(i1.getName() + i2.getName(), Gender.MALE));
        System.out.println(person2);

        /*
        пример без reduce()
         */
        String name = people.stream()
                .map(Person::getName)
                .collect(Collectors.joining());
        System.out.println(new Person(name, Gender.MALE));
    }

    /**
     * Пример подсчета суммы длин имени каждго персона, используем {@link Stream#reduce(BinaryOperator) reduce()}
     */
    @Test
    void reduce2() {
        Integer reduce = people.stream()
                .map(person -> person.getName().length())
                .reduce(0, (i1, i2) -> i1 + i2);
        System.out.println(reduce);


        reduce = people.stream()
                .reduce(0, (i1, i2) -> i1 + i2.getName().length(), Integer::sum);
        System.out.println(reduce);

        /*
        пример без reduce()
         */
        reduce = people.stream()
                .map(person -> person.getName().length())
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println(reduce);
    }
}
