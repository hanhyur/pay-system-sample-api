package me.hanhyur.pay.api.user;

import lombok.RequiredArgsConstructor;
import me.hanhyur.pay.user.domain.Transaction;
import me.hanhyur.pay.user.domain.User;
import me.hanhyur.pay.user.domain.register.BankRegister;
import me.hanhyur.pay.user.domain.register.CardRegister;
import me.hanhyur.pay.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> findUserInfo(@PathVariable("id") String userId) {
        return new ResponseEntity<>(userService.findUserInfo(userId), HttpStatus.OK);
    }

    @GetMapping("/transaction/all/{id}")
    public ResponseEntity<List<Transaction>> findUserTrxList(@PathVariable("id") String userId) {
        return new ResponseEntity<>(userService.findAllTransactions(userId), HttpStatus.OK);
    }

    @GetMapping("/transaction/payment/{id}")
    public ResponseEntity<List<Transaction>> findUserPaymentList(@PathVariable("id") String userId) {
        return new ResponseEntity<>(userService.findAllPayments(userId), HttpStatus.OK);
    }

    @GetMapping("/transaction/transfer/{id}")
    public ResponseEntity<List<Transaction>> findUserTransferList(@PathVariable("id") String userId) {
        return new ResponseEntity<>(userService.findAllTransfers(userId), HttpStatus.OK);
    }

    @GetMapping("/transaction/transfer/send/{id}")
    public ResponseEntity<List<Transaction>> findUserSendList(@PathVariable("id") String userId) {
        return new ResponseEntity<>(userService.findAllTransfersByOnlySend(userId), HttpStatus.OK);
    }

    @GetMapping("/transaction/transfer/receive/{id}")
    public ResponseEntity<List<Transaction>> findUserReceiveList(@PathVariable("id") String userId) {
        return new ResponseEntity<>(userService.findAllTransfersByOnlyReceive(userId), HttpStatus.OK);
    }

    @GetMapping("/transaction/transfer/not-complete/{id}")
    public ResponseEntity<List<Transaction>> findUserTransferNotCompletedList(@PathVariable("id") String userId) {
        return new ResponseEntity<>(userService.findAllTransferNotCompleted(userId), HttpStatus.OK);
    }

    @PostMapping("/register/card/{id}")
    public ResponseEntity<String> registerCard(@PathVariable("id") String userId, @RequestBody CardRegister cardRegister) {
        boolean result = userService.registerTransactionMethod(userId, cardRegister);

        return result ? new ResponseEntity<>("Register Success", HttpStatus.OK)
                : new ResponseEntity<>("Register Failed", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/register/bank/{id}")
    public ResponseEntity<String> registerBank(@PathVariable("id") String userId, @RequestBody BankRegister bankRegister) {
        boolean result = userService.registerTransactionMethod(userId, bankRegister);

        return result ? new ResponseEntity<>("Register Success", HttpStatus.OK)
                : new ResponseEntity<>("Register Failed", HttpStatus.BAD_REQUEST);
    }

}
