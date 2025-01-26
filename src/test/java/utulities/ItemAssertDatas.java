package utulities;

import pages.SauceDemoMainPage;

public class ItemAssertDatas {
 public   static String item1text;
 public    static String item1price;
 public    static String item2text;
 public    static String item2price;
 public    static double totalPrice;

    SauceDemoMainPage sauceDemoMainPage=new SauceDemoMainPage();


    public void itemAssertDatas(){

     item1text=sauceDemoMainPage.firstRandomAddedItem.getText();
        item2text=sauceDemoMainPage.secondRandomAddedItem.getText();
        item1price=sauceDemoMainPage.firstRandomAddedItemPrice.getText().
                replace("$","");
        item2price=sauceDemoMainPage.secondRandomAddedItemPrice.getText().replace("$","");


        totalPrice=Double.valueOf(item1price)+Double.valueOf(item2price);



    }
}
