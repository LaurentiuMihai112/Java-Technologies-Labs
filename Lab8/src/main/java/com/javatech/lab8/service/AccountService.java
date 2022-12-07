package com.javatech.lab8.service;

import com.javatech.lab8.dtos.AccountDTO;
import com.javatech.lab8.dtos.AccountLoginDTO;
import com.javatech.lab8.dtos.AccountRegisterDTO;
import com.javatech.lab8.entity.User;
import com.javatech.lab8.exceptions.AccountAlreadyExistsException;
import com.javatech.lab8.exceptions.AccountInvalidCredentialsException;
import com.javatech.lab8.exceptions.AccountNotFoundException;
import com.javatech.lab8.pemissions.Role;
import com.javatech.lab8.repository.AccountRepository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class AccountService {

    @Inject
    protected AccountRepository accountRepository;

    public AccountDTO get(Long accountId) throws AccountNotFoundException {
        boolean exists = accountRepository.checkIfExistsById(accountId);

        if (exists) {
            User user = accountRepository.findById(User.class, accountId);
            AccountDTO accountDTO = new AccountDTO();

            accountDTO.setId(user.getId());
            accountDTO.setUsername(user.getName());

            return accountDTO;
        } else {
            throw new AccountNotFoundException();
        }
    }

    public List<AccountDTO> gets() {

        List<AccountDTO> accountsDTO = new ArrayList<>();
        for (User user : accountRepository.getAll()) {
            AccountDTO accountDTO = new AccountDTO();

            accountDTO.setId(user.getId());
            accountDTO.setUsername(user.getName());

            accountsDTO.add(accountDTO);
        }

        return accountsDTO;
    }

    public void create(AccountRegisterDTO user,Role role) throws AccountAlreadyExistsException {

        boolean exists = accountRepository.checkIfExists(user.getUsername());

        if (!exists) {
            accountRepository.save(new User(user.getUsername(), user.getPassword(), role.toString()));
        } else {
            throw new AccountAlreadyExistsException();
        }


    }


    public Role getAccountRole(Long id) {

        User user = accountRepository.findById(User.class, id);

        if (user != null) {
            return Role.fromString(user.getRole());
        }
        return null;

    }

    public Long validate(AccountLoginDTO user) throws AccountInvalidCredentialsException, AccountNotFoundException {
        boolean exists = accountRepository.checkIfExists(user.getUsername());

        if (exists) {
            User account = accountRepository.getUserByName(user.getUsername());

            if (account.getPassword().equals(user.getPassword())) {
                return account.getId();
            } else {
                throw new AccountInvalidCredentialsException();
            }
        } else {
            throw new AccountNotFoundException();
        }
    }

    public void remove(Long id) throws AccountNotFoundException {
        boolean exists = accountRepository.checkIfExistsById(id);

        if (exists) {
            accountRepository.deleteById(User.class, id);
        } else {
            throw new AccountNotFoundException();
        }
    }

    public void updateRole(Long id, Role newRole) throws AccountNotFoundException {

    }

}
