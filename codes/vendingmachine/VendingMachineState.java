
package vendingmachine;

import java.util.*;
import coin.Coin;
import item.*;

public class VendingMachineState {
    public VendingMachineState(){
        System.out.println("Currently machine is in the idle state");
    }

    public void clickOnStartProductSelectionButton(){
        System.out.println("Vending machine is in HasMoneyState");
    }

    public void insertCoin(VendingMachine vendingMachine, Coin coin){
        System.out.println("Vending machine is in HasMoneyState");
        System.out.println("Accepted the coin");
        vendingMachine.getCoinList().add(coin);
    }

    public List<Coin> refundFullMoney(VendingMachine vendingMachine){
       System.out.println("Returned the full amount back in the coin dispense tray");
       vendingMachine.setVendingMachineState(new VendingMachineState());
       return vendingMachine.getCoinList();
    }

    public void updateInventory(VendingMachine vendingMachine, Item item, int itemCodeNumber){
        vendingMachine.getInventory().addItem(item, itemCodeNumber);
    }

    public void dispenseProduct(VendingMachine vendingMachine, int itemCodeNumber){
        System.out.println("Currently vending machine is in Dispense state");
        System.out.println("Product has been dispense");
        vendingMachine.getInventory().updateSoldOutItem(itemCodeNumber);
        vendingMachine.setVendingMachineState(new VendingMachineState());
    }

    public void chooseProduct(VendingMachine vendingMachine, int itemCodeNumber){
        Item item = vendingMachine.getInventory().getItem(itemCodeNumber);

        int paidByUser = 0;
        for(Coin coin:vendingMachine.getCoinList()){
            paidByUser += coin.getValue();
        }

        if(item.getPrice() > paidByUser){
            refundFullMoney(vendingMachine);
            return;
        } else {
            getChange(item.getPrice(),paidByUser);
            dispenseProduct(vendingMachine, itemCodeNumber);
            return;
        }
    }

    public void getChange(int itemPrice, int paidByUser){
        System.out.println("Returned the change in the Coin Dispense Tray: " + (paidByUser-itemPrice));
    }

}
