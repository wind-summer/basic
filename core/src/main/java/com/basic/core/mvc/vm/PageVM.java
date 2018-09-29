package com.basic.core.mvc.vm;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 分页工具类
 * 
 * @author wenlongfei
 *
 * @date 2016年11月4日 下午12:59:00
 */
@Data
@Accessors(chain = true)
public class PageVM implements Serializable {
	private static final long serialVersionUID = 1L;
	//总记录数
	private long totalCount;
	//每页记录数
	private int pageSize;
	//总页数
	private int totalPage;
	//当前页数
	private int currPage;
	//列表数据
	private List<?> list;
	
	/**
	 * 分页
	 * @param list        列表数据
	 * @param totalCount  总记录数
	 * @param pageSize    每页记录数
	 * @param currPage    当前页数
	 */
	public PageVM(List<?> list, long totalCount, int pageSize, int currPage) {
		this.list = list;
		this.totalCount = totalCount;
		this.pageSize = pageSize;
		this.currPage = currPage;
		this.totalPage = (int)Math.ceil((double)totalCount/pageSize);
	}
	
}
