package com.Wkrzyz.HouseHoldIncomeManager.services;

import com.Wkrzyz.HouseHoldIncomeManager.model.Transfer;
import com.Wkrzyz.HouseHoldIncomeManager.model.User;
import com.Wkrzyz.HouseHoldIncomeManager.model.dto.TransferDto;

import java.util.List;

public interface TransferService {


    void saveTransfer(TransferDto transferDto);

    Transfer findTransferByID(Integer id);

    List<TransferDto> findAllByOwner(Integer user_id);

    List<TransferDto> findAll();

}
