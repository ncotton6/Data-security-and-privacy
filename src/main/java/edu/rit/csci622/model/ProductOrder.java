package edu.rit.csci622.model;

public class ProductOrder {

	private int idOrder, productId, qty, fulfilledBy;
	private boolean fulfilled;
	
	public int getIdOrder() {
		return idOrder;
	}
	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public int getFulfilledBy() {
		return fulfilledBy;
	}
	public void setFulfilledBy(int fulfilledBy) {
		this.fulfilledBy = fulfilledBy;
	}
	public boolean isFulfilled() {
		return fulfilled;
	}
	public void setFulfilled(boolean fulfilled) {
		this.fulfilled = fulfilled;
	}
	
}
