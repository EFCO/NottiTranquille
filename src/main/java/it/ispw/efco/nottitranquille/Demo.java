package it.ispw.efco.nottitranquille;

import it.ispw.efco.nottitranquille.model.*;

public class Demo {
    private Price price;

    public Demo(){
        this.setPrice(null);
    }

    public Demo(Price price){
        this.setPrice(price);
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public void display(){
        System.out.printf("%.2f€%n", this.price.showPrice());
    }

    public static void main(String[] args) {
        BasePrice basePrice = new BasePrice.Builder()
                .setPrice(20)
                .build();

        Demo demo = new Demo(basePrice);
        System.out.print("Cost: ");
        demo.display();
        System.out.println();

        double discount = 10;
        FixDiscount priceWithFixDiscount = new FixDiscount.Builder()
                .applyTo(basePrice)
                .setDiscount(discount)
                .build();

        demo.setPrice(priceWithFixDiscount);
        System.out.printf("Cost with a fee of %.2f€: ", discount);
        demo.display();

        double percentageDiscount = 50;
        PercentageDiscount priceWithFixDiscountAndPercentageDiscount = new PercentageDiscount.Builder()
                .applyTo(priceWithFixDiscount)
                .setDiscount(percentageDiscount)
                .build();

        demo.setPrice(priceWithFixDiscountAndPercentageDiscount);
        System.out.printf("Cost discounted down to %.0f%%: ", percentageDiscount);
        demo.display();

        double percentageFee = 21;
        PercentageFee priceWithFixDiscountAndPercentageDiscountAndPercentageFee = new PercentageFee.Builder()
                .applyTo(priceWithFixDiscountAndPercentageDiscount)
                .setFee(percentageFee)
                .build();

        demo.setPrice(priceWithFixDiscountAndPercentageDiscountAndPercentageFee);
        System.out.printf("Cost with a fee of %.0f%%: ", percentageFee);
        demo.display();
    }
}
