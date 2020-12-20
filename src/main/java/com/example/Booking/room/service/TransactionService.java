package com.example.Booking.room.service;

import com.example.Booking.room.model.Transaction;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class TransactionService {

    private RestTemplate restTemplate;

    public TransactionService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Transaction> getTransaction(String date, int time){
        String url = "http://localhost:8091/api/transaction/" + date + "&" + time;
        ResponseEntity<Transaction[]> response =
                restTemplate.getForEntity(url, Transaction[].class);

        Transaction[] transactions = response.getBody();
        return Arrays.asList(transactions);
    }

    public void editTransaction(Transaction transaction) {
        String url = "http://localhost:8091/api/transaction";
        restTemplate.put(url, transaction);
    }


}
