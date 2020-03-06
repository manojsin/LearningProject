package com.grow.service;

import com.grow.beans.request.AddTransactionRequest;
import com.grow.beans.response.BaseResponse;
import com.grow.beans.response.GetGroupWiseAmount;
import com.grow.beans.response.GetOverAllAmountResponse;
import com.grow.beans.response.GetTransactionStatement;
import com.grow.dao.Group;
import com.grow.repository.AddUserInGroupRepository;
import com.grow.repository.GroupRepository;
import com.grow.repository.TransactionStatementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SplitWiseServiceImpl {
   private AddUserInGroupRepository addUserInGroupRepository;

    private GroupRepository groupRepository;
    private TransactionStatementRepository transactionStatementRepository;
    public SplitWiseServiceImpl(){}
    @Autowired
    public SplitWiseServiceImpl(AddUserInGroupRepository addUserInGroupRepository,
                                TransactionStatementRepository transactionStatementRepository,
                                GroupRepository groupRepository){
      this.addUserInGroupRepository=addUserInGroupRepository;
      this.transactionStatementRepository=transactionStatementRepository;
      this.groupRepository=groupRepository;
    }
    public BaseResponse addTransactionInGroup(AddTransactionRequest addTransactionRequest)
    {
        Group groupDetails=groupRepository.findByGroupId(addTransactionRequest.getGroupId());
        Group group=new Group();
        group.setGroupId(addTransactionRequest.getGroupId());
        group.setTotalAmount(groupDetails.getTotalAmount()+addTransactionRequest.getAmount());
        groupRepository.save(group);
        return null;
    }

    public GetGroupWiseAmount getGroupWiseAmount(String groupName,String uerEmail) {
        GetGroupWiseAmount getGroupWiseAmount=new GetGroupWiseAmount();
        Group group=groupRepository.findByGroupName(groupName);
        int noOfUser=group.getNumOfUser();
        double totalAmount=group.getTotalAmount();
        double perUserAmount=totalAmount/noOfUser;
        getGroupWiseAmount.setAmount(perUserAmount);
        getGroupWiseAmount.setTotalAmount(perUserAmount);
        return getGroupWiseAmount;
    }

    public GetOverAllAmountResponse getOverAllAmount(String groupName) {
        return null;
    }

    public GetTransactionStatement getTransactionStatement(String uerEmail) {
        return null;
    }
}
