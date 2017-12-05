package com.echo.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

public class PageController implements Serializable{

	private boolean isPage = true;// �Ƿ��ҳ

	private int totalRows; // ������
	private int pageSize = 34;// ÿҳ����
	private int currentPage = 1; // ��ǰҳ��

	private int nextPage;

	private int previousPage;

	private int totalPages; // ��ҳ��

	private boolean hasNext; // �Ƿ�����һҳ

	private boolean hasPrevious; // �Ƿ���ǰһҳ

	private int pageStartRow;

	private int pageEndRow;

	private String pageURL; // ��תҳ

	private boolean isIndex;

	private String indexKey;
	private String indexValue;

	private List<Criterion> conditions = new ArrayList<Criterion>(0);

	private List<Order> orderList = new ArrayList<Order>(0);

	public List<Criterion> getConditions() {
		return conditions;
	}

	/**
	 * Restrictions.eq() Restrictions.like()
	 * 
	 * @param simpleExpression
	 */
	public void addCondition(Criterion criterion) {
		this.conditions.add(criterion);
	}

	public List<Order> getOrderList() {
		return orderList;
	}

	public void addOrder(Order order) {
		this.orderList.add(order);
	}

	public boolean isPage() {
		return isPage;
	}

	public void setPage(boolean isPage) {
		this.isPage = isPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		nextPage = currentPage + 1;
		previousPage = currentPage - 1;
	}

	public boolean isHasNext() {
		if (this.getTotalPages() != 0 && this.getCurrentPage() != 0) {
			if (this.getCurrentPage() + 1 <= this.getTotalPages())
				return true;
		}
		return false;
	}

	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}

	public boolean isHasPrevious() {
		if (this.getTotalPages() != 0 && this.getCurrentPage() != 0) {
			if (this.getCurrentPage() - 1 > 0)
				return true;
		}

		return false;
	}

	public void setHasPrevious(boolean hasPrevious) {
		this.hasPrevious = hasPrevious;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getPageEndRow() {
		return pageEndRow;
	}

	public void setPageEndRow(int pageEndRow) {
		this.pageEndRow = pageEndRow;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageStartRow() {
		return pageStartRow;
	}

	public void setPageStartRow(int pageStartRow) {
		this.pageStartRow = pageStartRow;
	}

	public String getPageURL() {
		return pageURL;
	}

	public void setPageURL(String pageURL) {
		this.pageURL = pageURL;
	}

	public int getPreviousPage() {
		return previousPage;
	}

	public void setPreviousPage(int previousPage) {
		this.previousPage = previousPage;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
		if (totalRows % pageSize == 0)
			totalPages = totalRows / pageSize;
		else
			totalPages = totalRows / pageSize + 1;
	}

	public boolean isIndex() {
		return isIndex;
	}

	public void setIndex(boolean isIndex) {
		this.isIndex = isIndex;
	}

	public String getIndexKey() {
		return indexKey;
	}

	public void setIndexKey(String indexKey) {
		this.indexKey = indexKey;
	}

	public String getIndexValue() {
		return indexValue;
	}

	public void setIndexValue(String indexValue) {
		this.indexValue = indexValue;
	}

}