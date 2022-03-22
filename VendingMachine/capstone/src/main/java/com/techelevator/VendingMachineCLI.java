package com.techelevator;

import com.techelevator.view.Menu;

import java.util.Scanner;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	//private static final String MAIN_MENU_OPTION_SALES_REPORT = null;
	private static final String[] MAIN_MENU_OPTIONS =
			{MAIN_MENU_OPTION_DISPLAY_ITEMS,
					MAIN_MENU_OPTION_PURCHASE};

	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS =
			{PURCHASE_MENU_OPTION_FEED_MONEY,
					PURCHASE_MENU_OPTION_SELECT_PRODUCT,
					PURCHASE_MENU_OPTION_FINISH_TRANSACTION};

	private Menu menu;
	private VendingMachine vendingMachine;

	public VendingMachineCLI(Menu menu, VendingMachine vendingMachine) {
		this.menu = menu;
		this.vendingMachine = vendingMachine;
	}

	public void run() {
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				vendingMachine.displayItems();
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				this.purchaseMenu();
			}else if(choice.equals("Sales Report")){
				vendingMachine.salesReport();
			}
		}
	}

	public void purchaseMenu(){
		String choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);

		if(choice.equals(PURCHASE_MENU_OPTION_FEED_MONEY)){
			vendingMachine.feedMoney();
			this.purchaseMenu();
		}else if(choice.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)){
			vendingMachine.selectProduct();
			this.purchaseMenu();
		}else if(choice.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)){
			vendingMachine.giveChange();
			this.run();
		}
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		String filePath = "C:\\Users\\Student\\workspace\\module-1-capstone-team-0\\capstone\\vendingmachine.csv";
		VendingMachine vendingMachine = new VendingMachine(filePath);

		VendingMachineCLI cli = new VendingMachineCLI(menu, vendingMachine);
		cli.run();
	}
}
