package numbers;

import Exceptions.RuleException;

public class Generator {
    int min;
    int max;

    Rule rule;

    public Generator(int min, int max, Rule rule)
    {
        if (max < min) {
            throw new IllegalArgumentException();
        }

        this.min = min;
        this.max = max;
        this.rule = rule;
    }

    public int[] generate(int nNumbers)
    {
        int[] result = new int[nNumbers];
        int number, index = 0;

        while (index < nNumbers) {
            number = (int) (max * Math.random());
            try {
                rule.checkRule(number, min, max);
            } catch (RuleException e) {
                number += e.getDelta();
            }
            result[index++] = number;
        }

        return result;
    }
    public Rule getRule()
    {
        return rule;
    }

    public void setRule(Rule rule)
    {
        this.rule = rule;
    }
}
