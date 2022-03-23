package dates;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.TreeSet;

public final class Pack extends Product {
    private TreeSet<Product> products;
    private int discount;

    public Pack(Integer idproduct, String name, double sellPrice, int stock, TreeSet<Product> productss,
            int discoun, LocalDate start, LocalDate end) {
        super(idproduct, name, sellPrice, stock, start, end);
        this.products = productss;
        this.discount = discoun;
    }

    public Pack(Integer idproduct, String name, double sellPrice, int stock, int discount, LocalDate start, LocalDate end) {
        super(idproduct, name, sellPrice, stock, start, end);
        this.products = new TreeSet<Product>();
        this.discount = discount;
    }

    public void addProduct(Product p) {
        this.products.add(p);
    }

    public void deleteProduct(Product p) {
        this.products.remove(p);
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public TreeSet<Product> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "Producte [idproducte=" + super.getId() + ", nom=" + super.getName() + ", preuVenda=" + super.getpriceSell() + ", stock=" + super.getStock() +
        "discount=" + discount + ", products=" + products.toString()+ "]";
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Pack) {
            Pack p = (Pack) obj;

            Iterator<Product> itr = this.products.iterator();
            Iterator<Product> itrr = p.getProducts().iterator();
            while (itr.hasNext()) {
                if (!itr.next().equals(itrr.next())) {
                    return false;
                }
            }

            return true;
        }
        return false;
    }

}
