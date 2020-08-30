import java.lang.*;
import java.util.Scanner;

public class Coffee {
    // Type Of Coffee According To Preference
    private enum TypeOfCoffee {
        espresso(250, 0, 16, 4),
        latte(350, 75, 20, 7),
        cappuccino(200, 100, 12, 6);

        int water;
        int milk;
        int coffeeBeans;
        int cost;

        TypeOfCoffee(int water, int milk, int coffeeBeans, int cost) {
            this.water = water;
            this.milk = milk;
            this.coffeeBeans = coffeeBeans;
            this.cost = cost;
        }

        public static TypeOfCoffee preference(String n) {
            switch (n) {
                case "1":
                    return espresso;
                case "2":
                    return latte;
                case "3":
                    return cappuccino;
                default:
                    break;
            }
            return espresso;
        }

        public static int prepare(int[] Ingredients, int money, TypeOfCoffee cafe) {
            if (Ingredients[0] < cafe.water) {
                System.out.println("Sorry, not enough water");
            } else if (Ingredients[1] < cafe.milk) {
                System.out.println("Sorry, not enough milk");
            } else if (Ingredients[2] < cafe.coffeeBeans) {
                System.out.println("Sorry, not enough coffee beans");
            } else {
                System.out.println("I have enough resorces, making you a coffee!");
                Ingredients[0] -= cafe.water;
                Ingredients[1] -= cafe.milk;
                Ingredients[2] -= cafe.coffeeBeans;
                money += cafe.cost;
                Ingredients[3]--;
            }
            return money;
        }
    }
    // amount
    public static void amountLeft(int[] Ingredients, int cash) {
        System.out.println("The coffee machine has:");
        System.out.printf("%d of water\n", Ingredients[0]);
        System.out.printf("%d of milk\n", Ingredients[1]);
        System.out.printf("%d of coffee beans\n", Ingredients[2]);
        System.out.printf("%d of disposable cups\n", Ingredients[3]);
        System.out.printf("%d of money\n", cash);
    }
    // buy
    public static int buy(int[] Ingredients, int cash) {
        Scanner scan = new Scanner(System.in);
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        String opt = scan.next();
        if ("back".equals(opt)) {
            return cash;
        } else {
            TypeOfCoffee cafe = TypeOfCoffee.preference(opt);
            return cafe.prepare(Ingredients, cash, cafe);
        }
    }
    // fill
    public static void fill(int[] Ingredients) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Write how many ml of water do you want to add:");
        int w = scan.nextInt();
        Ingredients[0] += w;
        System.out.println("Write how many ml of milk do you want to add:");
        w = scan.nextInt();
        Ingredients[1] += w;
        System.out.println("Write how many grams of coffee beans do you want to add:");
        w = scan.nextInt();
        Ingredients[2] += w;
        System.out.println("Write how many disposable cups do you want to add:");
        w = scan.nextInt();
        Ingredients[3] += w;
    }
    // take
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[] Ingredients = {400, 540, 120, 9};
        int cash = 550;
        String action = "";
        while(!("exit".equals(action))) {
            System.out.println("Write action (buy, fill, take, remaining, exit): ");
            action = scan.next();
            System.out.println();
            switch (action) {
                case "buy":
                    cash = buy(Ingredients, cash);
                    break;
                case "fill":
                    fill(Ingredients);
                    break;
                case "take":
                    System.out.println("I gave you $" + cash);
                    cash = 0;
                    break;
                case "remaining":
                    amountLeft(Ingredients, cash);
                default:
                    break;
            }
            if (!("exit".equals(action))) {
                System.out.println();
            }
        }
    }
}
