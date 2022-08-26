package dto;

public class Criteria {
	private int currentPage;
	private int rowPerPage;
	private String search;
	
	
	public Criteria() {
		super();
	}
	public Criteria(int currentPage, int rowPerPage) {
		super();
		this.currentPage = currentPage;
		this.rowPerPage = rowPerPage;
	}
	

	
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
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
