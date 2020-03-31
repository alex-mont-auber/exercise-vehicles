package com.vehicles.project;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

    // 1st, make the all variables, objects, scanner's, etc...
        Scanner scOne = new Scanner(System.in);

        boolean correctionUser = false;
        boolean plateOk;
        boolean infoWheel = false;

        String vehicleSelection;
        String newPlate;
        String brandWheel = "";

        double diameterWheel = 0.0;

    // 2nd, ask what vehicle user want, car or bike. And check if you write correctly the answer.
        do
        {
            System.out.println("Choose one option: car or bike?");
            vehicleSelection = scOne.nextLine().toLowerCase();
            if(vehicleSelection.equals("car") || vehicleSelection.equals("bike"))
            {
                correctionUser = true;
            }
            else
            {
                System.out.println("You have misspelled the selection, try again.");
                vehicleSelection = "";
            }
        }
        while(!correctionUser);

    // 3rd, ask brand, color and plate number with check if it's correct pattern.

        // 3.1 ask Brand and Color
        System.out.println("Wich brand is your " + vehicleSelection + "?");
        String newBrand = scOne.nextLine();
        System.out.println("What color have?");
        String newColor = scOne.nextLine();

        // 3.2 ask plate and check if it's correct.
        do
        {
            System.out.println("What is your plate number?");
            newPlate = scOne.nextLine().toUpperCase();
            Matcher m = Pattern.compile("\\d\\d\\d\\d[A-Z][A-Z][A-Z]").matcher(newPlate);
            plateOk = m.find();
            if(!plateOk)
            {
                System.out.println("ITV's result: NO OK(" + plateOk + "). Introduce a valid plate number!");
            }
            else
            {
                System.out.println("ITV's result: OK(" + plateOk + "), it's a valid plate number!");
            }
        }
        while (plateOk == false);

        ArrayList<Wheel> backWheels = new ArrayList<>();
        ArrayList<Wheel> frontWheels = new ArrayList<>();

        // EL ORDEN ES EL SIGUIENTE:
        /*
            brandWheel recives String - DONE
            diameterWheel recives Double - DONE
            Front/back Wheels recives Brand y Diameter - DONE
            allWheels recives brand and diameter - DONE
            MyCar/MyBike recives AllWheels - DONE
            MyCar/MyBike show CarWheels - DONE
         */

    // 4rth, with "vehicleSelection" info, we go to one case or another.
        switch (vehicleSelection)
        {
            // 4.1 START CAR
            case "car":
                Car myCar = new Car(newPlate, newBrand, newColor);
                for(int e = 0; e < 4; e++)
                {
                    if(e <= 1)
                    {
                        System.out.println("Front wheel, brand of the wheel: ");

                    }
                    else
                    {
                        System.out.println("Back wheel, brand of wheel: ");
                    }
                    brandWheel = scOne.next();
                    do {
                        System.out.println("Diameter of wheel(with one decimal): ");
                        diameterWheel = scOne.nextDouble(); //Pass String to double
                        if(diameterWheel < 0.4 || diameterWheel > 4.0)
                        {
                            System.out.println("Com back to introduce the correct diameter between 0.4 and 4 cm!");
                            frontWheels.remove(e);
                            e--;
                        }
                        else
                        {
                            if(e > 1)
                            {
                                infoWheel = true;
                            }
                        }
                    }
                    while (diameterWheel < 0.4 && diameterWheel > 4.0 );
                    Wheel CarWheels = new Wheel(brandWheel, diameterWheel);
                    if(infoWheel) // Here add front and back
                    {
                        backWheels.add(CarWheels);
                    }
                    else
                    {
                        frontWheels.add(CarWheels);
                    }
                }

                // Try and catch adding to addWheels and posible exceptions

                try {
                    myCar.addWheels(frontWheels,backWheels);
                    System.out.println("Try/Catch Pass!! Wheels correct!");
                    System.out.println();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //Printing results

                System.out.println("Brand: " + myCar.brand + ", plate: " + myCar.plate + " and color " + myCar.color);
                System.out.println();
                System.out.println("Front wheels: " + frontWheels);
                System.out.println("Back wheels: " +backWheels);
                System.out.println();
                System.out.println("Ruedas de car: " + myCar.wheels);
                break;

            // 4.1 END CAR

            // 4.2 START BIKE
            case "bike":
                Bike myBike = new Bike(newPlate, newBrand, newColor);
                for(int e = 0; e < 2; e++) // 2 quantity of wheels
                {
                    if(e <= 0) // front and back answer
                    {
                        System.out.println("Front wheel, brand of the wheel: ");
                    }
                    else
                    {
                        System.out.println("Back wheel, brand of wheel: ");
                    }
                    brandWheel = scOne.next(); // add answer to brand
                    do
                    {   //This "two" loop, are maded for input mistakes, diameter conditional, and repeat if this conditional is false.
                        System.out.println("Diameter of wheel(with one decimal): ");
                        diameterWheel = scOne.nextDouble();
                        if(diameterWheel < 0.4 || diameterWheel > 4.0)
                        {
                            System.out.println("Com back to introduce the correct diameter between 0.4 and 4 cm!");
                        }
                        else
                        {
                            Wheel bikeWheel = new Wheel(brandWheel, diameterWheel);
                            myBike.wheels.add(bikeWheel);
                        }
                    }
                    while (diameterWheel < 0.4 && diameterWheel > 4.0 );
                }
                System.out.println("Brand: " + myBike.brand + ", plate: " + myBike.plate + " and color " + myBike.color);
                System.out.println("Wheels of bike: " + myBike.wheels);
                break;
            default:
                break;
        }
    }
}