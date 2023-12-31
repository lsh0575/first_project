package com.threadpool.dto;

import java.util.List;

import com.threadpool.dao.MessageDao;

public class MessageListPage {
	private int listTotal;			//전체 갯수
	private int onePageListCount;	//한페이지에 나타날 갯수
	private int pageCount;			//페이지 전체 갯수
	private int pagesLimit;			//한 화면에 보여줄 페이지 갯수 <1 . . . 10 > 이거
	private int currentPages;		//현재 페이지
	private int pagesStart;			//페이지 하단바 맨 왼쪽
	private int pagesEnd;			//페이지 하단바 맨 오른쪽
	private List<MessageDto> list;	//한페이지에 나타날 리스트
	private int pageStartNum;			//리스트에서 시작할 숫자
	
	///////Constructor
	public MessageListPage() {
		super();
	}
	
	public MessageListPage(int pageStartNum,AccountDto dto) {
		MessageDao dao= new MessageDao();
		listTotal=dao.listCount(dto);
		onePageListCount=10;
		pageCount=(int)Math.ceil(listTotal/(double)onePageListCount);
		pagesLimit=10;
		currentPages=(int)Math.floor(pageStartNum/(double)onePageListCount)+1;
		pagesStart=((currentPages-1)/pagesLimit)*pagesLimit+1;
		pagesEnd=(pageCount>pagesStart+pagesLimit-1)?
						pagesStart+pagesLimit-1	: pageCount;
		list = dao.messageList(dto, pageStartNum, onePageListCount);
		this.pageStartNum = pageStartNum;
	}
	
	
	///////Getter,Setter
	public int getListTotal() { return listTotal; } public void setListTotal(int listTotal) { this.listTotal = listTotal; } 
	public int getOnePageListCount() { return onePageListCount; } public void setOnePageListCount(int onePageListCount) { this.onePageListCount = onePageListCount; } 
	public int getPageCount() { return pageCount; } public void setPageCount(int pageCount) { this.pageCount = pageCount; } 
	public int getPagesLimit() { return pagesLimit; } public void setPagesLimit(int pagesLimit) { this.pagesLimit = pagesLimit; } 
	public int getCurrentPages() { return currentPages; } public void setCurrentPages(int currentPages) { this.currentPages = currentPages; } 
	public int getPagesStart() { return pagesStart; } public void setPagesStart(int pagesStart) { this.pagesStart = pagesStart; } 
	public int getPagesEnd() { return pagesEnd; } public void setPagesEnd(int pagesEnd) { this.pagesEnd = pagesEnd; } 
	public List<MessageDto> getList() { return list; } public void setList(List<MessageDto> list) { this.list = list; } 
	public int getPageStartNum() { return pageStartNum; } public void setPageStartNum(int pageStartNum) { this.pageStartNum = pageStartNum; } 
	
}
