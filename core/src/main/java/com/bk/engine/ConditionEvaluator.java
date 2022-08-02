package com.bk.engine;

import com.bk.identity.Principal;
import com.bk.policy.conditions.Condition;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 02/08/22
 */
public interface ConditionEvaluator {

    boolean evaluate(Principal principal, Condition condition);


    /**
     "Conditions": [
         {
             "operation":"equals" {
             "principal-attribute:name": "GUEST"
             }
         }
     ]
     */
}
