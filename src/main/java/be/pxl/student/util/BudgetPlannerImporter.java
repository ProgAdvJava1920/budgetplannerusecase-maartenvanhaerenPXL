package be.pxl.student.util;

import be.pxl.student.entity.Account;
import be.pxl.student.entity.Payment;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Util class to import csv file
 */
public class BudgetPlannerImporter {

    //private String _filename;
    private Account account;

    public BudgetPlannerImporter(){};
    //public BudgetPlannerImporter(String filename){
    //    _filename = filename;
    //};

    public Account ReadFile( String _filename){
        Path path = Paths.get(_filename);
        List<Payment> payments = new ArrayList<>();
        try {
            BufferedReader reader = Files.newBufferedReader(path);
            String line = reader.readLine();
            line = reader.readLine();
            while (reader.readLine() != null){

                String[] splitedString = line.split(",");
                if (account == null){
                    account = CreateAccount(splitedString);
                }
                Payment payment = CreatePayment(splitedString);
                payments.add(payment);
                line = reader.readLine();
            };
            account.setPayments(payments);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

        }
        return account;
    }

    //Account name,Account IBAN,Counteraccount IBAN,Transaction date,Amount,Currency,Detail
    private Account CreateAccount(String[] line){
        Account account = new Account();
        account.setName(line[0]);
        account.setIBAN(line[1]);
        return account;
    }

    private Payment CreatePayment(String[] splitedString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
        Payment payment = new Payment(LocalDateTime.parse(splitedString[3], formatter), Float.parseFloat(splitedString[4]), splitedString[5], splitedString[6]);
        return payment;
    }
}
