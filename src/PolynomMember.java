
public class PolynomMember implements Comparable<PolynomMember> {
    public float coefficient;
    public int power;

    @Override
    public int compareTo(PolynomMember member2) {
        return -this.power + member2.power;
    }
}
