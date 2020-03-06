package com.grow.dao;

import com.grow.beans.request.AddTransactionRequest;
import com.grow.beans.response.GetGroupWiseAmount;
import com.grow.beans.response.GetTransactionStatement;
public interface SplitDao {
    void addTransactionInGroup(AddTransactionRequest addTransactionRequest);
     GetGroupWiseAmount getGroupWiseAmount(String groupName, String uerEmail);
     GetTransactionStatement getTransactionStatement(String groupName) ;

}
