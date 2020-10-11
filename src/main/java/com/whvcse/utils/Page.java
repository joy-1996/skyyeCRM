package com.whvcse.utils;

import java.util.List;

/*
 * 泛型page类
 * 用于分页查询
 * */
public class Page<E> {
	@Override
	public String toString() {
		return "Page [currentPage=" + currentPage + ", pageSize=" + pageSize + ", totalRecord=" + totalRecord
				+ ", list=" + list + "]";
	}


	//当前页
	private int  currentPage;
	//页大小
	private int  pageSize;
	//总记录数 数据库中拿到的东西
	private int  totalRecord;
	//保存数据 数据库中拿到的东西
	private  List<E> list;
	
	//-------构造方法--------
	public Page(int currentPage, int pageSize) {
		super();
		this.currentPage = currentPage;
		this.pageSize = pageSize;
	}
	//总页数 (总记录数+页大小-1)/页大小
	public  int  getTotalPage() {
		return (totalRecord+pageSize-1)/pageSize;
	}
	//下一页
	public  int getNextPage() {
		if (currentPage<getTotalPage()) {
		
			return currentPage+1;
			
		}
		return currentPage;
	
	}
	
	//上一页
	public  int getBeforePage() {
		//判断当前页是否为第一页
		if (currentPage>1) {
			return currentPage-1;
		}
		return currentPage;
	}
	//-------方法--------
	public int productsServiceImpl() {
		return currentPage;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public List<E> getList() {
		return list;
	}
	public void setList(List<E> list) {
		this.list = list;
	}
	
	
	
}
