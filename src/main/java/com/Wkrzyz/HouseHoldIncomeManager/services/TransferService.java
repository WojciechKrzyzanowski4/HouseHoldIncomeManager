package com.Wkrzyz.HouseHoldIncomeManager.services;

import com.Wkrzyz.HouseHoldIncomeManager.model.dto.TransferDto;

import java.util.List;

public interface TransferService {


    void saveTransfer(TransferDto transferDto);

    List<TransferDto> findAllByOwner(Integer userId);

    List<TransferDto> findAll();

}
