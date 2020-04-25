package numbers;

import Exceptions.RuleException;

public interface Rule {
    void checkRule(int number, int min, int max) throws RuleException;
}
