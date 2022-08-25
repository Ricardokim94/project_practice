package dto;

public class Criteria {
	private int currentPage;
	private int rowPerPage;
	
	public Criteria() {
		super();
	}
	public Criteria(int currentPage, int rowPerPage) {
		super();
		this.currentPage = currentPage;
		this.rowPerPage = rowPerPage;
	}
	

	
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getRowPerPage() {
		return rowPerPage;
	}

	public void setRowPerPage(int rowPerPage) {
		this.rowPerPage = rowPerPage;
	}

	
	
}
