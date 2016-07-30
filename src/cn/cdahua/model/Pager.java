package cn.cdahua.model;

import java.util.List;

public class Pager<T> {
	private int pageOffSet;
	private int pageSize;
	private long totalRecord;
	private List<T> datas;

	public int getPageOffSet() {
		return pageOffSet;
	}

	public void setPageOffSet(int pagePageOffSet) {
		this.pageOffSet = pagePageOffSet;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(long totalRecord) {
		this.totalRecord = totalRecord;
	}

	public List<T> getDatas() {
		return datas;
	}

	public void setDatas(List<T> datas) {
		this.datas = datas;
	}

}
