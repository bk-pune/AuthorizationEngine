package com.bk.condition;

import com.bk.policy.conditions.Condition;
import com.bk.policy.conditions.StringEqualsCondition;
import com.bk.policy.conditions.StringEqualsOperand;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 01/08/22
 */
public class ConditionTest {
    @Test
    public void stringEqualsConditionTest() {
        StringEqualsOperand operand = new StringEqualsOperand("ADMIN", "GUEST");
        Condition<StringEqualsOperand> stringEqualsCondition = new StringEqualsCondition(operand);
        Assert.assertTrue(stringEqualsCondition.getConditionName().equals("STRING_EQUALS"));
        Assert.assertFalse(stringEqualsCondition.execute());

        operand = new StringEqualsOperand("ADMIN", "ADMIN");
        stringEqualsCondition = new StringEqualsCondition(operand);
        Assert.assertTrue(stringEqualsCondition.execute());
    }
}
