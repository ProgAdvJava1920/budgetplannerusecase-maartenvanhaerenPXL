package be.pxl.student.dao;

import be.pxl.student.entity.Account;
import be.pxl.student.entity.Payment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PaymentDAOTest {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
    Payment payment = new Payment();
    PaymentDAO paymentDAO = new PaymentDAO("jdbc:mysql://localhost:3306/budgetplanner?useSSL=false", "root", "admin");
    @Test
    public void testPaymentInsert(){

        payment.setCurrency("EUR");
        payment.setId(1);
        payment.setLabelId(1);
        payment.setCounterAccountId(1);
        payment.setId(1);
        payment.setAmount(200);
        payment.setDate(LocalDateTime.now());
        payment.setDetail("test value");

        Payment paymentInserted = paymentDAO.createPayment(payment);
        assertNotEquals(0, paymentInserted.getId());
        System.out.println("ID: " + paymentInserted.getId());;
        System.out.println(paymentInserted);
    }
    @Test
    public void testPaymentUpdate(){
        payment.setCurrency("EUR");
        payment.setId(1);
        payment.setLabelId(1);
        payment.setCounterAccountId(1);
        payment.setId(1);
        payment.setAmount(10000);
        payment.setDate(LocalDateTime.now());
        payment.setDetail("test value, Amount update");
        paymentDAO.updatePayment(payment);
        Payment updatedPayment = paymentDAO.readPayment(1);
        Assertions.assertEquals(payment.getAmount(), updatedPayment.getAmount());
        System.out.println(updatedPayment);
    }
    @Test
    public void testPaymentDelete(){


    }
    @Test
    public void testPaymentRead(){
        Payment paymentRead = paymentDAO.readPayment(1);
        assertNotEquals(null, paymentRead);
        System.out.println(paymentRead);
    }

}
