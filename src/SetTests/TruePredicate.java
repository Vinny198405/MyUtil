package SetTests;

import java.util.function.Predicate;

public class TruePredicate implements Predicate<Integer> {

    @Override
    public boolean test(Integer t) {
        return true;
    }
}
