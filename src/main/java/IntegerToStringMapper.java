import java.util.function.Function;

public class IntegerToStringMapper implements Function<Integer, String> {
    @Override
    public String apply(Integer integer) {
        return String.valueOf(integer);
    }
}
