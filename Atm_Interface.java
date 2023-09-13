import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Account {
    private int accountId;
    private int balance;

    public Account(int accountId, int initialBalance) {
        this.accountId = accountId;
        this.balance = initialBalance;
    }

    public int getAccountId() {
        return accountId;
    }

    public int getBalance() {
        return balance;
    }

    public void deposit(int amount) {
        balance += amount;
    }

    public boolean withdraw(int amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public void transfer(Account receiver, int amount) {
        if (withdraw(amount)) {
            receiver.deposit(amount);
        } else {
            System.out.println("Insufficient balance for the transfer.");
        }
    }
}
1234
class Transaction {
    private String type;
    private int amount;

    public Transaction(String type, int amount) {
        this.type = type;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return type + ": " + amount;
    }
}

class TransactionHistory {
    private List<Transaction> transactions;

    public TransactionHistory() {
        transactions = new ArrayList<>();
    }

    public void addTransaction(String type, int amount) {
        transactions.add(new Transaction(type, amount));
    }

    public void displayTransactionHistory() {
        System.out.println("Transaction History:");
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }
}

public class Atm_Interface {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Simulate two accounts
        Account account1 = new Account(1234, 1000);
        Account account2 = new Account(5678, 500);

        TransactionHistory history = new TransactionHistory();

        // ATM Interface
        System.out.print("Enter your account ID: ");
        int accountId = sc.nextInt();
        System.out.print("Enter your PIN: ");
        int pin = sc.nextInt();

        // Check if the user is authenticated (simplified for this example)
        if (accountId == 1234 && pin == 1234) {
            while (true) {
                System.out.println("\nATM Menu:");
                System.out.println("1. View Balance");
                System.out.println("2. Withdraw");
                System.out.println("3. Deposit");
                System.out.println("4. Transfer");
                System.out.println("5. Transaction History");
                System.out.println("6. Quit");

                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("Your balance is: " + account1.getBalance());
                        break;
                    case 2:
                        System.out.print("Enter the amount to withdraw: ");
                        int withdrawAmount = sc.nextInt();
                        if (account1.withdraw(withdrawAmount)) {
                            System.out.println("Withdraw successful.");
                            history.addTransaction("Withdraw", withdrawAmount);
                        } else {
                            System.out.println("Insufficient balance.");
                        }
                        break;
                    case 3:
                        System.out.print("Enter the amount to deposit: ");
                        int depositAmount = sc.nextInt();
                        account1.deposit(depositAmount);
                        System.out.println("Deposit successful.");
                        history.addTransaction("Deposit", depositAmount);
                        break;
                    case 4:
                        System.out.print("Enter the recipient's account ID: ");
                        int recipientId = sc.nextInt();
                        System.out.print("Enter the amount to transfer: ");
                        int transferAmount = sc.nextInt();
                        if (recipientId == account2.getAccountId()) {
                            account1.transfer(account2, transferAmount);
                            System.out.println("Transfer successful.");
                            history.addTransaction("Transfer", transferAmount);
                        } else {
                            System.out.println("Recipient account not found.");
                        }
                        break;
                    case 5:
                        history.displayTransactionHistory();
                        break;
                    case 6:
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        sc.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } else {
            System.out.println("Authentication failed. Please try again.");
        }
    }
}
