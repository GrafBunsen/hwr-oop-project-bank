package hwr.oop.budgetbook.logic;

import hwr.oop.budgetbook.models.Transaction;
import hwr.oop.budgetbook.view.Account;

import java.util.ArrayList;
import java.util.List;

public class Expenses {
    List<Account> accounts;

    public Expenses() {
        accounts = new ArrayList<>();
    }

    public void addTransaction(Transaction transaction) {
        String category = transaction.getCategory();

        boolean categoryAccountExists = false;
        int categoryAccountIndex = -1;
        for (int i = 0; i < accounts.size(); i++) {
            if (category.equals(accounts.get(i).getPath())) {
                categoryAccountExists = true;
                categoryAccountIndex = i;
            }
        }

        Account expensesAccount;
        if (categoryAccountExists) {
            expensesAccount = accounts.get(categoryAccountIndex);
        } else {
            expensesAccount = new Account(category);
        }

        expensesAccount.addEntry(transaction);

        if (categoryAccountExists) {
            accounts.set(categoryAccountIndex, expensesAccount);
        } else {
            accounts.add(expensesAccount);
        }
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public int calculateSumOfExpenses() {
        int sumOfExpenses = 0;
        for (Account expense : accounts) {
            sumOfExpenses = expense.sumOverAllEntries();
        }
        return sumOfExpenses;
    }

    public Account getCategoryAccount(String category) {
        for (Account account : accounts) {
            if (account.getPath().equals(category)) {
                return account;
            }
        }
        return null;
    }
}