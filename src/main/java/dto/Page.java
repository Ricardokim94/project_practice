package dto;

public class Page { //페이지를 관리하는 
	private int startPage;
	private int endPage;
	private int total;
	private Criteria cri;
	private boolean prev;
	private boolean next;
	
			public Page(int total, Criteria cri) { //page라는 객체가 만들어질때 무조건 전체페이지랑 cri가 넘어와야된다.
				super();
				this.total = total;
				this.cri = cri;
				
				this.endPage =(int)Math.ceil(cri.getCurrentPage()/(cri.getRowPerPage()*1.0))*cri.getRowPerPage();
				this.startPage = this.endPage - (cri.getRowPerPage()-1);
				
				int realEnd = (int)(Math.ceil((total * 1.0)/cri.getRowPerPage()));
				
				if(realEnd < this.endPage) {
					this.endPage = realEnd;
				}
				
				this.prev = this.startPage > 1; //트루가됨 = 나타남
				this.next = this.endPage < realEnd; //트루가됨 = 나타남
				
			}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Criteria getCri() {
		return cri;
	}

	public void setCri(Criteria cri) {
		this.cri = cri;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}
	
	
	
}
