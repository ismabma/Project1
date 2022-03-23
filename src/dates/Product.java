package dates;

import java.time.LocalDate;

public class Product implements Identificable  {
    private Integer idproduct;
    private String name;
    private double priceSell;
    private int stock;
    private LocalDate startCatalog;
    private LocalDate endCatalog;

    public Product(Integer idproducte, String nam, double priceSel, int stock, LocalDate start, LocalDate end) {
        this.idproduct = idproducte;
        this.name = nam;
        this.priceSell = priceSel;
        this.stock = stock;
        this.startCatalog = start;
        this.endCatalog = end;
    }

    public Integer getId() {
        return idproduct;
    }

    public LocalDate getStartCatalog() {
        return startCatalog;
    }

    public LocalDate getEndCatalog() {
        return endCatalog;
    }

    public void setId(Integer idproducte) {
        this.idproduct = idproducte;
    }

    public String getName() {
        return name;
    }

    public void setName(String nam) {
        this.name = nam;
    }

    public double getpriceSell() {
        return priceSell;
    }

    public void setpriceSell(double priceSel) {
        this.priceSell = priceSel;
    }

    public int getStock() {
        return stock;
    }

    public int putStock(int stock) {
        stock = this.getStock() + stock;
        this.setStock(stock);
        return stock;
    }

    public int takeStock(int stock) throws StockInsuficientException {
        stock = this.getStock() - stock;
        if(stock > 0){
            this.setStock(stock);
        } else{
            throw new StockInsuficientException("You can't take more stock than it is");
        }
        this.setStock(stock);
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Producte [idproducte=" + idproduct + ", nom=" + name + ", preuVenda=" + priceSell + ", stock=" + stock
                + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Product) {
            Product p = (Product) obj;
            if (p.getId() == this.getId()) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

}
