package dates;

import java.util.Comparator;

public class ProductComparatorName implements Comparator{
    @Override
    public int compare(Object arg0, Object arg1) {
        Product p0 = (Product) arg0;
        Product p1 = (Product) arg1;
        return p0.getName().compareTo(p1.getName());
    }
}
