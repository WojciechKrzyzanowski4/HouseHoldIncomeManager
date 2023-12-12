package com.Wkrzyz.HouseHoldIncomeManager.services.impl;

import com.Wkrzyz.HouseHoldIncomeManager.model.Transfer;
import com.Wkrzyz.HouseHoldIncomeManager.model.User;
import com.Wkrzyz.HouseHoldIncomeManager.model.dto.TransferDto;
import com.Wkrzyz.HouseHoldIncomeManager.model.dto.UserDto;
import com.Wkrzyz.HouseHoldIncomeManager.repository.TransferRepository;
import com.Wkrzyz.HouseHoldIncomeManager.repository.UserRepository;
import com.Wkrzyz.HouseHoldIncomeManager.services.TransferService;
import jakarta.transaction.Transaction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransferServiceImpl implements TransferService {

    private TransferRepository transferRepository;

    public TransferServiceImpl(TransferRepository transferRepository){

        this.transferRepository = transferRepository;
    }

    @Override
    public void saveTransfer(TransferDto transferDto) {
        Transfer transfer = new Transfer();

        transfer.setCategory(transferDto.getCategory());
        transfer.setUser(transferDto.getUser());
        transfer.setId(transferDto.getId());
        transfer.setValue(transferDto.getValue());
        transfer.setIsRecurring(transferDto.getIsRecurring());

        transferRepository.save(transfer);
    }

    @Override
    public List<TransferDto> findAllByOwner(Integer user_id) {
        List<Transfer> databaseTransfers = transferRepository.findByUser_id(user_id);
        return databaseTransfers.stream()
                .map((transfer) -> mapToTransferDto(transfer))
                .collect(Collectors.toList());
    }
    @Override
    public List<TransferDto> findAll() {
        List<Transfer> databaseTransfers = (List<Transfer>)transferRepository.findAll();
        return databaseTransfers.stream()
                .map((transfer) -> mapToTransferDto(transfer))
                .collect(Collectors.toList());
    }


    private TransferDto mapToTransferDto(Transfer transfer){

        TransferDto transferDto = new TransferDto();

        transferDto.setId(transfer.getId());
        transferDto.setCategory(transfer.getCategory());
        transferDto.setIsRecurring(transfer.getIsRecurring());
        transferDto.setValue(transfer.getValue());
        transferDto.setUser(transfer.getUser());

        return transferDto;
    }
}
