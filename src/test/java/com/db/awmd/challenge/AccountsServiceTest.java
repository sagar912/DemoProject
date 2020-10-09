package com.db.awmd.challenge;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import com.db.awmd.challenge.domain.Account;
import com.db.awmd.challenge.exception.DuplicateAccountIdException;
import com.db.awmd.challenge.exception.NegativeAmountException;
import com.db.awmd.challenge.service.AccountsService;
import java.math.BigDecimal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountsServiceTest {

  @Autowired
  private AccountsService accountsService;

  @Test
  public void addAccount() throws Exception {
    Account account = new Account("Id-123", null, null, null, null);
    account.setBalance(new BigDecimal(1000));
    this.accountsService.createAccount(account);

    assertThat(this.accountsService.getAccount("Id-123")).isEqualTo(account);
  }

  @Test
  public void addAccount_failsOnDuplicateId() throws Exception {
    String uniqueId = "Id-" + System.currentTimeMillis();
    Account account = new Account(uniqueId, null, null, null, null);
    this.accountsService.createAccount(account);

    try {
      this.accountsService.createAccount(account);
      fail("Should have failed when adding duplicate account");
    } catch (DuplicateAccountIdException ex) {
      assertThat(ex.getMessage()).isEqualTo("Account id " + uniqueId + " already exists!");
    }

  }
  
  
  @Test
  public void transferMoney() throws Exception {
    Account account = new Account(null,30000l, "ID-123", "ID-323");
    account.setFromAccountId("ID-123");
    account.setToAccountId("ID-323");
    account.setAmount(30000l);


    this.accountsService.transferMoney("ID-123", "ID-323", 30000l);

    assertThat(this.accountsService.transferMoney("Id-123","ID-323,30000l", 30000l)).isEqualTo(account);
  }
  
  
  @Test
  public void transferMoney_failsOnNegativeAmount() throws Exception {
    Account account = new Account(null,30000l, "ID-123", "ID-323");
    this.accountsService.transferMoney("ID-123", "ID-323", 30000l);

    try {
      this.accountsService.transferMoney("ID-323", "ID-123", 30000l);
      fail("Should have failed when adding negative amount");
    } catch (NegativeAmountException ex) {
      assertThat(ex.getMessage()).isEqualTo("Amount of transfer " + account.getAmount() + " Negative");
    }

  }
  

}
