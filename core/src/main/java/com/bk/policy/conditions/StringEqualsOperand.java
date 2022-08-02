package com.bk.policy.conditions;

/**
 * Operand useful for comparing two Strings values.<p>
 * Frequent use case would be during Attribute Checks on principal, such as comparing resource names, identity name, etc.<br>
 * <br>
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 02/08/22
 */
public class StringEqualsOperand implements Operand<String, String> {
    private String lhs;
    private String rhs;

    public StringEqualsOperand() {

    }

    public StringEqualsOperand(String lhs, String rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public String getLHS() {
        return lhs;
    }

    @Override
    public String getRHS() {
        return rhs;
    }

    @Override
    public void setLHS(String lhs) {

    }

    @Override
    public void setRHS(String rhs) {

    }
}
