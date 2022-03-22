package com.techelevator;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

public class VendingMachine {
    private BigDecimal balance = BigDecimal.valueOf(0);
    private List<Item> inventory = new ArrayList<Item>();
    private BigDecimal coinSlotBalance = BigDecimal.valueOf(0);
    private AuditFile auditFile = new AuditFile();

    public VendingMachine(String filePath) {
        File inventoryFile = new File(filePath);
        this.inventory = parseInventoryFile(inventoryFile);
    }

    public void feedMoney(){
        Scanner userInput = new Scanner(System.in);
        System.out.println("Please insert a valid bill ($1, $2, $5, or $10)");
        BigDecimal inputMoney;
        try{
            inputMoney = BigDecimal.valueOf(Integer.parseInt(userInput.nextLine()));
            auditFile.UpdateAuditFile("FEED MONEY: " +
                    NumberFormat.getCurrencyInstance().format(inputMoney) + " " +
                    NumberFormat.getCurrencyInstance().format(coinSlotBalance));
        }catch(NumberFormatException e){
            System.out.println("invalid number format");
            return;
        }
        if(!inputMoney.equals(BigDecimal.valueOf(1)) && !inputMoney.equals(BigDecimal.valueOf(2)) && !inputMoney.equals(BigDecimal.valueOf(5)) && !inputMoney.equals(BigDecimal.valueOf(10))){
            System.out.println("Please insert a valid bill");
            return;
        }
        coinSlotBalance = coinSlotBalance.add(inputMoney);
        System.out.println("Current Money Provided: $" + coinSlotBalance);
    }

    public void selectProduct(){
        Scanner userInput = new Scanner(System.in);
        this.displayItems();
        System.out.println("Please enter a product code to select an item");
        String code = userInput.nextLine();
        for(int i = 0; i < inventory.size(); i++){
            if(inventory.get(i).getSlot().equals(code)){
                if(inventory.get(i).getItemCount() <= 0){
                    System.out.println("Product is sold out");
                    return;
                }
                if(inventory.get(i).getPrice().compareTo(this.getCoinSlotBalance()) == 1){
                    System.out.println("u 2 poor");
                    return;
                }
                this.sellItem(i);
                return;
            }
        }
        System.out.println("Invalid product code");
    }

    public void giveChange(){
        int numQuarters = 0;
        int numDimes =  0;
        int numNickels = 0;
        auditFile.UpdateAuditFile("GIVE CHANGE: " +
                NumberFormat.getCurrencyInstance().format(coinSlotBalance) + " " +
                NumberFormat.getCurrencyInstance().format(0));

        numQuarters = coinSlotBalance.divide(BigDecimal.valueOf(0.25)).intValue();
        coinSlotBalance = coinSlotBalance.subtract(BigDecimal.valueOf(numQuarters * 0.25));

        numDimes = coinSlotBalance.divide(BigDecimal.valueOf(0.1)).intValue();
        coinSlotBalance = coinSlotBalance.subtract(BigDecimal.valueOf(numDimes * 0.1));

        numNickels = coinSlotBalance.divide(BigDecimal.valueOf(0.05)).intValue();
        coinSlotBalance = coinSlotBalance.subtract(BigDecimal.valueOf(numNickels * 0.05));

        System.out.println("Your Change is " + numQuarters + " quarters, " + numDimes
            + " dimes, and " + numNickels + " nickels.");
    }

    private List<Item> parseInventoryFile(File inventoryFile){
        List<Item> itemList = new ArrayList<>();
        try(Scanner fileScanner = new Scanner(inventoryFile)){
            while (fileScanner.hasNextLine()){
                Item item = new Item();
                String nextLine = fileScanner.nextLine();
                String[] nextLineArray = nextLine.split("\\|");
                item.setSlot(nextLineArray[0]);
                item.setName(nextLineArray[1]);
                item.setPrice(BigDecimal.valueOf(Double.parseDouble(nextLineArray[2])));
                item.setType(nextLineArray[3]);
                item.setItemCount(5);
                itemList.add(item);
            }
        }catch(FileNotFoundException e){
            System.out.println("file not found");
            System.exit(1);
        }
        return itemList;
    }

    public void salesReport(){
        File salesReport = new File("C:\\Users\\Student\\workspace\\module-1-capstone-team-0\\capstone\\salesreport.txt");

        try {
            salesReport.delete();
            salesReport.createNewFile();
        } catch (IOException e) {}

        try(PrintWriter salesReportWriter = new PrintWriter(salesReport)){
            for(int i = 0; i < inventory.size(); i++){
                salesReportWriter.print(inventory.get(i).getName());
                salesReportWriter.print("|" + (5 - inventory.get(i).getItemCount()) + "\n");
            }
            salesReportWriter.println();
            salesReportWriter.println("**TOTAL SALES** $" + this.balance);
        }catch (FileNotFoundException e){
            System.out.println("file not found");
            System.exit(2);
        }
    }

    public void displayItems(){
        for(int i = 0; i < inventory.size(); i++){
            System.out.print(inventory.get(i).getName());
            System.out.print(" (" + inventory.get(i).getSlot() + ") ");
            System.out.print((NumberFormat.getCurrencyInstance().format(inventory.get(i).getPrice()) + " "));
            if(inventory.get(i).getItemCount() == 0){
                System.out.print("SOLD OUT\n");
            }else{
                System.out.print(inventory.get(i).getItemCount() + "\n");
            }
        }
    }


    public void sellItem(int i){
        balance = balance.add(inventory.get(i).getPrice());
        coinSlotBalance = coinSlotBalance.subtract(inventory.get(i).getPrice());
        inventory.get(i).setItemCount(inventory.get(i).getItemCount() - 1);

        System.out.println(inventory.get(i).getName() + " "
                + NumberFormat.getCurrencyInstance().format(inventory.get(i).getPrice())
                + " (" + NumberFormat.getCurrencyInstance().format(coinSlotBalance)
                + " remaining)");

        auditFile.UpdateAuditFile(inventory.get(i).getName() + " " + inventory.get(i).getSlot() + " " +
                NumberFormat.getCurrencyInstance().format(inventory.get(i).getPrice().add(coinSlotBalance)) + " " +
                NumberFormat.getCurrencyInstance().format(coinSlotBalance));
        if(inventory.get(i).getType().equals("Chip")){
            System.out.println("Crunch Crunch, Yum!");
        }else if(inventory.get(i).getType().equals("Candy")){
            System.out.println("Munch Munch, Yum!");
        }else if(inventory.get(i).getType().equals("Drink")){
            System.out.println("Glug Glug, Yum!");
        }else if(inventory.get(i).getType().equals("Gum")){
            System.out.println("Chew Chew, Yum!");
        }
    }


    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public void setInventory(List<Item> inventory) {
        this.inventory = inventory;
    }

    public BigDecimal getCoinSlotBalance() {
        return coinSlotBalance;
    }

    public void setCoinSlotBalance(BigDecimal coinSlotBalance) {
        this.coinSlotBalance = coinSlotBalance;
    }

    public AuditFile getAuditFile() {
        return auditFile;
    }

    public void setAuditFile(AuditFile auditFile) {
        this.auditFile = auditFile;
    }
}
