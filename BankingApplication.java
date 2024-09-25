import java.io.*;
import java.util.Scanner;

public class BankingApplication {

    public static void main(String[] args) {
        // Load existing account
        BankAccount obj = BankAccount.loadAccount();
        if (obj == null) {
            obj = new BankAccount("Riya Sharma", "SL00001");
        }

        obj.showMenu();
        obj.saveAccount(); // Save account before exiting
    }

}

class BankAccount implements Serializable {
    private static final long serialVersionUID = 1L; // Unique ID for serialization
    int balance;
    int previousTransaction;
    String customerName;
    String customerId;

    BankAccount(String cname, String cid) {
        customerName = cname;
        customerId = cid;
    }

    void deposit(int amount) {
        if (amount != 0) {
            balance = balance + amount;
            previousTransaction = amount;
        }
    }

    void withdraw(int amount) {
        if (amount != 0) {
            balance = balance - amount;
            previousTransaction = -amount;
        }
    }

    void getPreviousTransaction() {
        if (previousTransaction > 0) {
            System.out.println("Deposited: " + previousTransaction);
        } else if (previousTransaction < 0) {
            System.out.println("Withdraw: " + Math.abs(previousTransaction));
        } else {
            System.out.println("No Transaction Occurred");
        }
    }

    void showMenu() {
        char option = '\0';
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome " + customerName);
        System.out.println("Your ID is " + customerId);
        System.out.println("\n");

        System.out.println("A : Check Your Balance");
        System.out.println("B : Deposit");
        System.out.println("C : Withdraw");
        System.out.println("D : Previous Transaction");
        System.out.println("E : Exit The System");

        do {
            // System.out.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
            System.out.println("Enter Your Option");
            // System.out.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
            option = scanner.next().charAt(0);
            System.out.println("\n");

            switch (option) {
                case 'A':
                    System.out.println("-------------------------------------------------------");
                    System.out.println("Balance = " + balance);
                    System.out.println("-------------------------------------------------------");
                    System.out.println("\n");
                    break;

                case 'B':
                    System.out.println("-------------------------------------------------------");
                    System.out.println("Enter an amount to deposit ");
                    System.out.println("-------------------------------------------------------");

                    int amount = scanner.nextInt();
                    deposit(amount);
                    System.out.println("\n");
                    break;

                case 'C':
                    System.out.println("-------------------------------------------------------");
                    System.out.println("Enter an amount to withdraw ");
                    System.out.println("-------------------------------------------------------");

                    int amount2 = scanner.nextInt();
                    withdraw(amount2);
                    System.out.println("\n");
                    break;

                case 'D':
                    System.out.println("-------------------------------------------------------");
                    getPreviousTransaction();
                    System.out.println("-------------------------------------------------------");
                    System.out.println("\n");
                    break;

                case 'E':
                    System.out.println("=========================================================================================================");
                    break;

                default:
                    System.out.println("Invalid Option!! Please Enter Correct Option...");
                    break;
            }
        } while (option != 'E');
        System.out.println("Thank You for Using our Services.....!!");
    }

    // Save account to a file
    void saveAccount() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("account.dat"))) {
            oos.writeObject(this);
            System.out.println("Account saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving account: " + e.getMessage());
        }
    }

    // Load account from a file
    static BankAccount loadAccount() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("account.dat"))) {
            return (BankAccount) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No existing account found. Creating a new one.");
            return null;
        }
    }
}
