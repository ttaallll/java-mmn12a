import java.util.*;
import java.util.concurrent.ExecutionException;

public class Polynom {
    private ArrayList<PolynomMember> members;

    public Polynom(Polynom copyPoly) {
        this.members = new ArrayList<PolynomMember>(copyPoly.members.size());

        for (int i = 0; i < copyPoly.members.size(); ++i) {
            PolynomMember tempMember = new PolynomMember();

            tempMember.coefficient = copyPoly.members.get(i).coefficient;
            tempMember.power = copyPoly.members.get(i).power;

            members.add(tempMember);
        }
    }

    public Polynom(float[] coefficients, int[] powers) throws Exception {

        if (coefficients.length != powers.length) {
            throw new Exception("coefficients and powers array should be in the same length");
        }

        members = new ArrayList<PolynomMember>(powers.length);
        for (int i = 0; i < coefficients.length; ++i) {
            PolynomMember tempMember = new PolynomMember();

            tempMember.coefficient = coefficients[i];
            tempMember.power = powers[i];

            members.add(tempMember);
        }

        Collections.sort(members);
    }

    public Polynom plus(Polynom poly2) {

        // create a dictionary that his key is the power and the value is the sum of the coefficients
        Map<Integer, Float> newPolyMembers = new HashMap<Integer, Float>();

        for (int i = 0; i < this.members.size(); ++i) {
            newPolyMembers.put(this.members.get(i).power, this.members.get(i).coefficient);
        }

        for (int i = 0; i < poly2.members.size(); ++i) {
            // if we have the power already in our dictionary, just update the sum
            if (newPolyMembers.containsKey(poly2.members.get(i).power)) {
                newPolyMembers.put(poly2.members.get(i).power,
                        newPolyMembers.get(poly2.members.get(i).power) + poly2.members.get(i).coefficient);
            } else {
                // if we dont have that power, add it as new
                newPolyMembers.put(poly2.members.get(i).power, poly2.members.get(i).coefficient);
            }
        }

        // convert the dictionary to 2 arrays

        float[] newCoefficients = new float[newPolyMembers.size()];
        int[] newPowers = new int[newPolyMembers.size()];

        Iterator<Integer> tempIterator = newPolyMembers.keySet().iterator();
        for (int i = 0; i < newPolyMembers.keySet().size(); ++ i) {
            newPowers[i] = tempIterator.next();
            newCoefficients[i] = newPolyMembers.get(newPowers[i]);
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

    public Polynom minus(Polynom poly2) {

        Polynom tempPoly2 = new Polynom(poly2);

        for (int i = 0; i < poly2.members.size(); ++i) {
            PolynomMember tempMember = new PolynomMember();
            tempMember.coefficient = tempPoly2.members.get(i).coefficient * -1;
            tempMember.power = tempPoly2.members.get(i).power;

            tempPoly2.members.set(i, tempMember);
        }

        return this.plus(tempPoly2);
    }

    public Polynom differentiation() {
        Polynom tempPoly = new Polynom(this);

        for (int i = 0; i < this.members.size(); ++i) {
            PolynomMember tempMember = new PolynomMember();
            tempMember.coefficient = this.members.get(i).coefficient * this.members.get(i).power;
            tempMember.power = this.members.get(i).power - 1;

            tempPoly.members.set(i, tempMember);
        }

        return tempPoly;
    }

    @Override
    public String toString() {

        String newStr = "";

        for (int i = 0; i < this.members.size(); ++i) {

            // if the coefficient is 0, don't print it
            if (this.members.get(i).coefficient == 0) {
                continue;
            }

            // check special if the number is not negative, add a plus sign
            if (i > 0 && this.members.get(i).coefficient > 0) {
                newStr += "+";
            }


            newStr += this.members.get(i).coefficient;

            // for power check special cases of x=1 and x=0, else print normally x^y
            if (this.members.get(i).power == 1) {
                newStr += "x";
            } else if (this.members.get(i).power == 0) {
            } else {
                newStr += "x^" + this.members.get(i).power;
            }
        }

        return newStr;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Polynom other = (Polynom) obj;
        // check the sizes of the polynom membes
        if (this.members.size() != other.members.size())
            return false;
        // go over all members and check their coefficient and also the power
        for (int i = 0; i < this.members.size(); ++i) {
            if (this.members.get(i).coefficient != other.members.get(i).coefficient) {
                return false;
            }
            if (this.members.get(i).power != other.members.get(i).power) {
                return false;
            }
        }
        return true;
    }
}
