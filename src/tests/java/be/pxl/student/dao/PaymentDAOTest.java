package be.pxl.student.dao;

import be.pxl.student.entity.Payment;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PaymentDAOTest {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
    @Test
    public void testPaymentInsert(){
        Payment payment = new Payment();
        payment.setDate(LocalDateTime.parse("Wed Feb 26 06:41:27 CET 2020", formatter));
        payment.setAmount(1000);
        payment.setCurrency("EUR");
        payment.setDetail("Detail");

        PaymentDAO paymentDAO=new PaymentDAO("jdbc:mysql://localhost:3306/budgetplanner?useSSL=false", "root", "admin");

        Payment paymentInserted = paymentDAO.createPayment(payment);
        //assertEquals(account,accountInserted);
        assertNotEquals(0,paymentInserted.getId());
        System.out.println(paymentInserted.getId());
    }
}
