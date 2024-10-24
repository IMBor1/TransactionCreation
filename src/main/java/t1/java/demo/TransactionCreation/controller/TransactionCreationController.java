package t1.java.demo.TransactionCreation.controller;

import jakarta.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.pulsar.PulsarProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionCreationController {

    @Autowired
    private KafkaTemplate<String, Transaction> kafkaTemplate;

    @PostMapping("/")
    public ResponseEntity<Void> createTransaction(@RequestBody Transaction transaction) {
        kafkaTemplate.send("t1_demo_client_transactions", transaction);
        return ResponseEntity.ok().build();
    }
}
