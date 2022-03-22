package com.techelevator.view;

import com.techelevator.VendingMachine;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.NumberFormat;

public class VendingMachineTest {
    private ByteArrayOutputStream output = new ByteArrayOutputStream();
    private PrintStream console = System.out;

    VendingMachine vendingMachine = new VendingMachine("C:\\Users\\Student\\workspace\\module-1-capstone-team-0\\capstone\\vendingmachine.csv");

    @Test
    public void salesReport(){
        vendingMachine.salesReport();


    }

    @Test
    public void testProductDisplay(){
        Menu menu = getMenuForTestingWithUserInput("1" + System.lineSeparator());
        String productDisplay = "";
        for(int i = 0; i < vendingMachine.getInventory().size(); i++){
            productDisplay +=
                    System.lineSeparator() + vendingMachine.getInventory().get(i).getName() + " (" + vendingMachine.getInventory().get(i).getSlot() + ") " +
                            NumberFormat.getCurrencyInstance().format(vendingMachine.getInventory().get(i).getPrice()) + " " + vendingMachine.getInventory().get(i).getItemCount();
        }

        Assert.assertEquals(productDisplay, output.toString());
    }

    @Test
    public void testFeedMoney(){

    }

    private Menu getMenuForTestingWithUserInput(String userInput) {
        ByteArrayInputStream input = new ByteArrayInputStream(String.valueOf(userInput).getBytes());
        return new Menu(input, output);
    }

    private Menu getMenuForTesting() {
        return getMenuForTestingWithUserInput("1" + System.lineSeparator());
    }
}
