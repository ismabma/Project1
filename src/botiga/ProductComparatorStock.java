package botiga;

import java.util.Comparator;

public class ProductComparatorStock implements Comparator{
    @Override
    public int compare(Object arg0, Object arg1) {
        Product p0 = (Product) arg0;
        Product p1 = (Product) arg1;
        return Integer.compare(p0.getStock(), p1.getStock());
    }
}
