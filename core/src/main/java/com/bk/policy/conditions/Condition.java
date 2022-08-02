package com.bk.policy.conditions;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 28/07/22
 */
public interface Condition<T extends Operand> {
    public abstract boolean execute();
    public String getConditionName();
    public void setOperand(T operand);
}
