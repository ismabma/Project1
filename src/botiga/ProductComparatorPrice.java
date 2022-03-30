package botiga;

import java.util.Comparator;

public class ProductComparatorPrice implements Comparator{
    @Override
    public int compare(Object arg0, Object arg1) {
        Product p0 = (Product) arg0;
        Product p1 = (Product) arg1;
        return Double.compare(p0.getpriceSell(), p1.getpriceSell());
    }
}
