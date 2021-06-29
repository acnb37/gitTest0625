package edu.kh.semi.board.model.vo;

public class Category {
	private int categoryCode;
	private String categoryName;
	public Category() {
		// TODO Auto-generated constructor stub
	}
	public Category(int categoryCode, String categoryName) {
		super();
		this.categoryCode = categoryCode;
		this.categoryName = categoryName;
	}
	public int getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCade(int categoryCode) {
		this.categoryCode = categoryCode;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	@Override
	public String toString() {
		return "Category [categoryCode=" + categoryCode + ", categoryName=" + categoryName + "]";
	}
	
}
