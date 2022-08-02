package com.bk.policy.conditions;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 02/08/22
 */
public interface Operand <L, R> {
    public L getLHS();
    public R getRHS();
    void setLHS(L lhs);
    void setRHS(R rhs);
}
