package com.whvcse.utils;

import java.util.List;


//bootstrap分页格式

public class BootstrapPage<T> {
	@Override
	public String toString() {
		return "BootstrapPage [total=" + total + ", rows=" + rows + "]";
	}

	private int total;
	private List<T> rows;
	private Object object;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
