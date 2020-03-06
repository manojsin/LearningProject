package com.grow.service;

import com.grow.beans.request.AddTransactionRequest;
import com.grow.beans.response.BaseResponse;
import com.grow.beans.response.GetGroupWiseAmount;
import com.grow.beans.response.GetOverAllAmountResponse;
import com.grow.beans.response.GetTransactionStatement;

public interface SplitWiseService {
    BaseResponse addTransactionInGroup(AddTransactionRequest addTransactionRequest);

    GetGroupWiseAmount getGroupWiseAmount(String groupName,String uerEmail);

    GetOverAllAmountResponse getOverAllAmount(String groupName);

    GetTransactionStatement getTransactionStatement(String uerEmail);


}
