package me.hanhyur.pay.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.hanhyur.pay.persistence.repository.TransactionRepository;
import me.hanhyur.pay.persistence.repository.UserRepository;
import me.hanhyur.pay.user.domain.Transaction;
import me.hanhyur.pay.user.domain.User;
import me.hanhyur.pay.user.domain.register.CardRegister;
import me.hanhyur.pay.user.domain.register.RegisterForm;
import me.hanhyur.pay.user.service.register.BankAccountConnector;
import me.hanhyur.pay.user.service.register.CardCompanyConnector;
import me.hanhyur.pay.user.service.register.Connector;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;

    // User Info
    public User findUserInfo(String userId) {
        return userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
    }

    // All Transaction List
    public List<Transaction> findAllTransactions(String userId) {
        return transactionRepository.findAllByMyId(userId)
                .stream()
                .sorted(Comparator.comparing(Transaction::getTrxDate, Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }

    // All Payment List
    public List<Transaction> findAllPayments(String userId) {
        return transactionRepository.findAllByMyId(userId)
                .stream()
                .filter(transaction -> transaction.getTrxType().equals(Transaction.TrxType.PAYMENT))
                .sorted(Comparator.comparing(Transaction::getTrxDate, Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }

    // All Transfer List
    public List<Transaction> findAllTransfers(String userId) {
        return transactionRepository.findAllByMyId(userId)
                .stream()
                .filter(transaction -> transaction.getTrxType().equals(Transaction.TrxType.TRANSFER))
                .sorted(Comparator.comparing(Transaction::getTrxDate, Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }

    // All Send List
    public List<Transaction> findAllTransfersByOnlySend(String userId) {
        return transactionRepository.findAllByMyId(userId)
                .stream()
                .filter(transaction -> transaction.getTrxType().equals(Transaction.TrxType.TRANSFER))
                .filter(transaction -> transaction.getMoney() < 0)
                .sorted(Comparator.comparing(Transaction::getTrxDate, Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }

    // All Receive List
    public List<Transaction> findAllTransfersByOnlyReceive(String userId) {
        return transactionRepository.findAllByMyId(userId)
                .stream()
                .filter(transaction -> transaction.getTrxType().equals(Transaction.TrxType.TRANSFER))
                .filter(transaction -> transaction.getMoney() > 0)
                .sorted(Comparator.comparing(Transaction::getTrxDate, Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }

    // Transfer Not Completed List
    public List<Transaction> findAllTransferNotCompleted(String userId) {
        return transactionRepository.findAllByMyId(userId)
                .stream()
                .filter(transaction -> transaction.getTrxType().equals(Transaction.TrxType.TRANSFER))
                .filter(transaction -> !transaction.isComplete())
                .sorted(Comparator.comparing(Transaction::getTrxDate, Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }

    // Transaction Method Register
    public boolean registerTransactionMethod(String userId, RegisterForm registerForm) {
        User result = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);

        String companyName = registerForm.getCompanyName();
        Connector connector;
        User.PaymentMethods type;

        if (registerForm instanceof CardRegister) {
            connector = new CardCompanyConnector();

            type = User.PaymentMethods.CARD;
        } else {
            connector = new BankAccountConnector();

            type = User.PaymentMethods.BANK_ACCOUNT;
        }

        connector.setCompany(registerForm);

        if (connector.isValid(registerForm)) {
            connector.isRegistered(registerForm);

            result.getPaymentMethods().add(type);

            userRepository.save(updateResultToDatabase(result, type, companyName));

            return true;
        } else {
            log.info("not valid!");

            return false;
        }

    }

    public static User updateResultToDatabase(User user, User.PaymentMethods type, String companyName) {
        if (type.equals(User.PaymentMethods.CARD)) {
            user.getPaymentMethods().add(User.PaymentMethods.CARD);
            user.getCardCompany().add(companyName);
        } else {
            user.getPaymentMethods().add(User.PaymentMethods.BANK_ACCOUNT);
            user.getBankCompany().add(companyName);
        }

        return user;
    }

}
