package be.pxl.student;

import be.pxl.student.entity.Account;
import be.pxl.student.entity.Payment;
import be.pxl.student.util.BudgetPlannerImporter;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BudgetPlannerImporterTest {
    BudgetPlannerImporter importer = new BudgetPlannerImporter();
    Account account = new Account();
    List<Payment> payments;
    @Test
    public void ReadFileShouldCreateAccountAndPaymaentsPerfect(){
        //account testen
        account = importer.ReadFile("src\\main\\resources\\account_payments.csv");
        assertEquals("Jos", account.getName());
        assertEquals("BE69771770897312", account.getIBAN());
        //payment testen
        payments = account.getPayments();
        assertEquals("Wed Feb 26 06:41:27 CET 2020", payments.get(0).getDate());
        assertEquals("3929.93", payments.get(0).getAmount());
        assertEquals("EUR", payments.get(0).getCurrency());
        assertEquals("Fuga quisquam vel sint eligendi eum molestiae", payments.get(0).getDetail());

    }
}
