package se.nackademin;

public class Account {
    int id;
    double amount;
    double interestRate;

    public Account(int id, double amount, double interestRate) {
        this.id = id;
        this.amount = amount;
        this.interestRate = interestRate;
    }

    @Override
    public String toString() {
        return "Amount: " + amount + " Interest rate: " + interestRate + "%\n";
    }
}
