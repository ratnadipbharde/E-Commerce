package com.example.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;



@Entity
@Table(name="products")

public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long productId;
	
	@Column(name="product_name")
	private String productName;
	
	@Column(name="product_type")
	private String productType;
	
	@Column(name="product_category")
	private String productCategory;
	
	@Column(name="product_price")
	private String productPrice ;
	
	@Column(name="product_saleby")
	private long productSaleBy;
	
	@Lob
    private byte[] pic;
	
	public Product() {
		
	}

	public Product(long productId, String productName, String productType, String productCategory, String productPrice,
			long productSaleBy, byte[] pic) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productType = productType;
		this.productCategory = productCategory;
		this.productPrice = productPrice;
		this.productSaleBy = productSaleBy;
		this.pic = pic;
	}
	
	
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductType() {
		return productType;
	}
	
	public long getProductSaleBy() {
		return productSaleBy;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	public String getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}
	public void setProductSaleBy(long productSaleBy) {
		this.productSaleBy = productSaleBy;
	}

	public byte[] getPic() {
		return pic;
	}

	public void setPic(byte[] pic) {
		this.pic = pic;
	}
	
}
