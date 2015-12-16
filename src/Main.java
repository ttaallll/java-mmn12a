import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {

        try {

            Scanner s = new Scanner(System.in);

            System.out.println("enter polynom 1 members, format \"x, x\". example \"2, 5\" for 2x^5, for next polynom press \"next\", to exit \"exit\"");
            String inputResult = "";
            ArrayList<String> polynom1Strings = new ArrayList<String>();
            ArrayList<String> polynom2Strings = new ArrayList<String>();

            while (!inputResult.equals("exit")) {
                inputResult = s.nextLine();

                if (inputResult.equals("exit"))
                    return;

                if (inputResult.equals("next"))
                    break;

                polynom1Strings.add(inputResult);
            }

            System.out.println("enter polynom 2 members");
            while (!inputResult.equals("exit")) {
                inputResult = s.nextLine();

                if (inputResult.equals("exit"))
                    return;

                if (inputResult.equals("next"))
                    break;

                polynom2Strings.add(inputResult);
            }




            Polynom poly1 = createPolynomFromStrings(polynom1Strings);
            Polynom poly2 = createPolynomFromStrings(polynom2Strings);


            System.out.println("poly1 - " + poly1);
            System.out.println("poly2 - " + poly2);

            Polynom sum = poly1.plus(poly2);
            Polynom difference = poly1.minus(poly2);

            System.out.println("sum, poly1+poly2        - " + sum);
            System.out.println("difference, poly1-poly2 - " + difference);
            System.out.println("differentiation poly1   - " + poly1.differentiation());
            System.out.println("differentiation poly2   - " + poly2.differentiation());
            System.out.println("is equal poly1 to poly1 - " + poly1.equals(poly1));
            System.out.println("is equal poly1 to poly2 - " + poly1.equals(poly2));

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static Polynom createPolynomFromStrings(ArrayList<String> strings) {
        float[] newCoefficients = new float[strings.size()];
        int[] newPowers = new int[strings.size()];

        for(int i = 0; i < strings.size(); ++i) {
            String[] splittedResult = strings.get(i).replaceAll("\\s+","").split(",");

            newCoefficients[i] = Float.parseFloat(splittedResult[0]);
            newPowers[i] = Integer.parseInt(splittedResult[1]);
        }

        Polynom newPoly = null;

        try {
            // create a polynom with the arrays
            newPoly = new Polynom(newCoefficients, newPowers);
        } catch (Exception e) {
            System.out.println("something went wrong");
        }

        return newPoly;
    }
}
