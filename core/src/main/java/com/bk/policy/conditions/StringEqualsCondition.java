package com.bk.policy.conditions;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 02/08/22
 */
public class StringEqualsCondition implements Condition<StringEqualsOperand> {
    private static final String CONDITION_NAME="STRING_EQUALS";
    private StringEqualsOperand operand;

    public StringEqualsCondition(StringEqualsOperand operand) {
        this.operand = operand;
    }

    @Override
    public boolean execute() {
        return operand.getLHS().equals(operand.getRHS());
    }

    @Override
    public String getConditionName() {
        return CONDITION_NAME;
    }

    @Override
    public void setOperand(StringEqualsOperand operand) {
        this.operand = operand;
    }
}
