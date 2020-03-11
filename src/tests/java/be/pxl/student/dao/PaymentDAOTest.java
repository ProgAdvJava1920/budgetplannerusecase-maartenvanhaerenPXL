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
    Payment payment;
    PaymentDAO paymentDAO = new PaymentDAO("jdbc:mysql://localhost:3306/budgetplanner?useSSL=false", "root", "admin");
    private Payment testpayment(String detail, float amount){
        Payment payment = new Payment();
        payment.setCurrency("EUR");
        payment.setId(1);
        payment.setLabelId(1);
        payment.setCounterAccountId(1);
        payment.setId(1);
        payment.setAmount(amount);
        payment.setDate(LocalDateTime.now());
        payment.setDetail(detail);
        return payment;
    }
    @Test
    public void testPaymentInsert(){
        payment = testpayment("test payment", 200);
        Payment paymentInserted = paymentDAO.createPayment(payment);
        assertNotEquals(0, paymentInserted.getId());
        System.out.println("ID: " + paymentInserted.getId());;
        System.out.println(paymentInserted);
    }
    @Test
    public void testPaymentUpdate(){
        payment = testpayment("test payment update amount", 100000);
        payment.setId(1);
        paymentDAO.updatePayment(payment);
        Payment updatedPayment = paymentDAO.readPayment(1);
        Assertions.assertEquals(payment.getAmount(), updatedPayment.getAmount());
        System.out.println(updatedPayment);
    }
    @Test
    public void testPaymentDelete(){
        payment = testpayment("test payment Delete", 200);
        Payment createdPayment = paymentDAO.createPayment(payment);
        System.out.println("id:" + createdPayment.getId());
        paymentDAO.deletePayment(payment.getId());
        Payment deletedPayment = paymentDAO.readPayment(createdPayment.getId());
        Assertions.assertEquals(null, deletedPayment);
        System.out.println(deletedPayment);


    }
    @Test
    public void testPaymentRead(){
        Payment paymentRead = paymentDAO.readPayment(1);
        assertNotEquals(null, paymentRead);
        System.out.println(paymentRead);
    }

}
