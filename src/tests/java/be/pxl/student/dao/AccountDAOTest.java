package be.pxl.student.dao;

import be.pxl.student.entity.Account;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccountDAOTest {
    private AccountDAO accountDAO=new AccountDAO("jdbc:mysql://localhost:3306/budgetplanner?useSSL=false", "root", "admin");
    private Account account = new Account();

    @Test
    public void testAccountInsert(){
        account.setIBAN("testIBAN");
        account.setName("testName");

        Account accountInserted = accountDAO.createAccount(account);
        //assertEquals(account,accountInserted);
        assertNotEquals(0,accountInserted.getId());
        System.out.println(accountInserted.getId());
    }

    @Test
    public void testAccountUpdate(){
        account.setIBAN("testIBAN");
        account.setName("testNameAangepast");
        account.setId(2);

        boolean uitgevoerd = accountDAO.updateAccount(account);
        assertTrue(uitgevoerd);
        //assert
        System.out.println(uitgevoerd);

    }
    @Test
    public void testAccountDelete(){


    }
    @Test
    public void testAccountRead(){
        Account accountRead = accountDAO.readAccount(1);
        assertNotEquals(null, accountRead);
        System.out.println("name: " + accountRead.getName());
        System.out.println("IBAN: " + accountRead.getIBAN());
    }

}
