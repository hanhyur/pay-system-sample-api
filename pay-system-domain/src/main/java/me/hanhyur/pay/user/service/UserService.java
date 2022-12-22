package me.hanhyur.pay.user.service;

import lombok.RequiredArgsConstructor;
import me.hanhyur.pay.persistence.repository.TransactionRepository;
import me.hanhyur.pay.persistence.repository.UserRepository;
import me.hanhyur.pay.user.domain.Transaction;
import me.hanhyur.pay.user.domain.User;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

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

    // Transfer Fail List
    public List<Transaction> findAllTransferNotComplete(String userId) {
        return transactionRepository.findAllByMyId(userId)
                .stream()
                .filter(transaction -> transaction.getTrxType().equals(Transaction.TrxType.TRANSFER))
                .filter(transaction -> !transaction.isComplete())
                .sorted(Comparator.comparing(Transaction::getTrxDate, Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }

}
