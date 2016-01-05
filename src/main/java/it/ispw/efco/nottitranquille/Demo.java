package it.ispw.efco.nottitranquille;

import it.ispw.efco.nottitranquille.model.*;
import it.ispw.efco.nottitranquille.model.enumeration.Day;
import it.ispw.efco.nottitranquille.model.enumeration.RepetitionType;

import java.util.Collections;

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

        double percentageFee = 21;
        PercentageFee basePriceWithPercentageFee = new PercentageFee.Builder()
                .applyTo(basePrice)
                .setFee(percentageFee)
                .build();

        demo.setPrice(basePriceWithPercentageFee);
        System.out.printf("Cost with a fee of %.0f%%: ", percentageFee);
        demo.display();

        double discount = 10;
        FixDiscount basePriceWithPercentageFeeAndFixDiscount = new FixDiscount.Builder()
                .applyTo(basePriceWithPercentageFee)
                .setRepetitionType(RepetitionType.EVERY_MONTH)
                .setDays(Collections.singletonList(Day.FIRST_SUNDAY))
                .setDiscount(discount)
                .build();

        demo.setPrice(basePriceWithPercentageFeeAndFixDiscount);
        System.out.printf("Cost with a discount of %.2f€: ", discount);
        demo.display();

        System.out.print(basePriceWithPercentageFeeAndFixDiscount.toString());
/*
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
        demo.display();*/
    }
}
