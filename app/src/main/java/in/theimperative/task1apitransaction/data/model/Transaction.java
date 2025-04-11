package in.theimperative.task1apitransaction.data.model;

public class Transaction {
    private int id;
    private String date;
    private int amount;
    private String category;
    private String description;

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public int getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }
}