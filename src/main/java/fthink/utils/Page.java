package fthink.utils;

public class Page {
	/**
	 * 当前页
	 * 默认第一页
	 */
	private int currentPage = 1;
	/**
	 * 每页显示的条数
	 * 默认每页显示15条记录
	 */
	private int pageSize = 15;
	/**
	 * 总页数
	 */
	private int pageCount;
	/**
	 * 记录总数
	 */
	private int recordCount;
	
	public Page() {
	}
	
	public Page(int currentPage,int pageSize) {
		if(currentPage == 0) {
			currentPage = 1;
		}
		if(pageSize == 0) {
			pageSize = 15;
		}
		this.currentPage = currentPage;
		this.pageSize = pageSize;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageCount() {
		//向上取整
		pageCount = (int) Math.ceil( ((double)this.recordCount) / ((double)this.pageSize) );
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

}
