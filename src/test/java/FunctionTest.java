import org.junit.jupiter.api.Test;

import java.util.function.BiFunction;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Примеры использования {@link java.util.function.Function} и {@link java.util.function.BiFunction}
 */
public class FunctionTest {
    /**
     * {@link java.util.function.Function} - аналог методу который принимает один аргумент (типа как первый тип дженерика)
     * и возвращает значение (типа как второй тип дженерика)
     */
    Function<Integer, Integer> declarativeIncrement = number -> ++number;
    /**
     * Пример реализации {@link java.util.function.Function}
     */
    IntegerToStringMapper integerToStringMapperObject = new IntegerToStringMapper();
    /**
     * Аналог вышестоящиму примеру без реализации {@link java.util.function.Function}
     * <p>
     * String::valueOf - method reference,
     * <p>
     * по-другому можно - number -> String.valueOf(number);
     * <p>
     * Предпочтительнее использовать method reference
     */
    Function<Integer, String> integerToStringMapper = String::valueOf;

    /**
     * Пример императивного стиля
     */
    @Test
    void imperativeIncrementTest() {
        int number = 1;
        assertEquals(2, imperativeIncrement(number));
    }

    /**
     * Примеры функционального стиля используя {@link java.util.function.Function}
     */
    @Test
    void declarativeIncrementTest() {
        int number = 1;
        assertEquals(2, declarativeIncrement.apply(number));
    }

    @Test
    void declarativeMapperTest() {
        int number = 1;
        assertEquals("1", integerToStringMapper.apply(number));
    }

    @Test
    void declarativeMapper2Test() {
        int number = 1;
        assertEquals("1", integerToStringMapperObject.apply(number));
    }

    /**
     * Мы можем выстраивать цепочки вызовов различных {@link java.util.function.Function}
     */
    @Test
    void andThenTest() {
        int number = 1;
        String apply = declarativeIncrement
                .andThen(declarativeIncrement)
                .andThen(integerToStringMapper)
                .apply(number);
        assertEquals("3", apply);
    }

    /**
     * Пример конвертации долларов в рубли + конвертация в строку
     */
    @Test
    void convertDollarToRubblesByBiFunction() {
        BiFunction<Double, Integer, String> convert = (dollarValue, dollarsCount) -> String.valueOf(dollarValue * dollarsCount);
        System.out.println(convert.apply(74.2, 9));
        assertEquals("667.8000000000001", convert.apply(74.2, 9));
    }


    int imperativeIncrement(int number) {
        return ++number;
    }
}
