package com.whvcse.utils;

import com.whvcse.pojo.Employees;


//bootstrap分页格式

public class BootstrapPage2<T> {
	@Override
	public String toString() {
		return "BootstrapPage [total=" + total + ", rows=" + rows + "]";
	}

	private int total;
	private Employees rows;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public Employees getRows() {
		return rows;
	}
	public void setRows(Employees rows) {
		this.rows = rows;
	}
}
