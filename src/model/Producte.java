package model;

import java.io.Serializable;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Locale.Category;
import java.util.TreeSet;

public class Producte implements Serializable {

	private static final long serialVersionUID = 1L;
	static Locale localeFormat = Locale.getDefault(Category.FORMAT);
    static NumberFormat priceFormat = NumberFormat.getCurrencyInstance(localeFormat);
    private Integer idproduct;
    private String name;
    private double priceSell;
    private int stock;
    private LocalDate startCatalog;
    private LocalDate endCatalog;
    private TreeSet<Producte> products;
    private int discount;

	public Producte(Integer idproducte, String nam, double priceSel, int stock, LocalDate start, LocalDate end) {
        this.idproduct = idproducte;
        this.name = nam;
        this.priceSell = priceSel;
        this.stock = stock;
        this.startCatalog = start;
        this.endCatalog = end;
    }

    public Producte(Integer idproduct, String name, double priceSell, int stock, LocalDate startCatalog,
			LocalDate endCatalog, TreeSet<Producte> products, int discount) {
		super();
		this.idproduct = idproduct;
		this.name = name;
		this.priceSell = priceSell;
		this.stock = stock;
		this.startCatalog = startCatalog;
		this.endCatalog = endCatalog;
		this.products = products;
		this.discount = discount;
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
    
    public TreeSet<Producte> getProducts() {
		return products;
	}

	public void setProducts(TreeSet<Producte> products) {
		this.products = products;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
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
		return "Producte [idproduct=" + idproduct + ", name=" + name + ", priceSell=" + priceSell + ", stock=" + stock
				+ ", startCatalog=" + startCatalog + ", endCatalog=" + endCatalog + ", products=" + products
				+ ", discount=" + discount + "]";
	}

	@Override
    public boolean equals(Object obj) {
        if (obj instanceof Producte) {
            Producte p = (Producte) obj;
            if (p.getId() == this.getId()) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }	
	
	public void imprimir(){
		System.out.println(this.toString());
	}
}
