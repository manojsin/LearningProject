package com.grow.dao;

import com.grow.beans.request.AddTransactionRequest;
import com.grow.beans.response.GetGroupWiseAmount;
import com.grow.beans.response.GetTransactionStatement;
import org.springframework.stereotype.Service;

@Service
public class SplitDaoImpl implements SplitDao {
    public void addTransactionInGroup(AddTransactionRequest addTransactionRequest) {
    }
    public GetGroupWiseAmount getGroupWiseAmount(String groupName, String uerEmail) {
        return null;
    }

    public GetTransactionStatement getTransactionStatement(String groupName) {
        return null;
    }
}
