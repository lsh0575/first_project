package com.threadpool.dto.hotel;

import java.util.List;

import com.threadpool.dao.HotelDao;

public class HotelDtoPaging {
	int pageTotal;
	int onePageLimit;
	int bottomPageAll; // Math.ceil
	int bottomPageLimit;
	int bottomCurrent;
	int bottomStart;
	int bottomEnd;
	List<HotelDto> list10;
	int pstartno;
	public HotelDtoPaging() { super(); }
	public HotelDtoPaging(int pstarthno) {
		HotelDao dao = new HotelDao();
		//#1전체글		:  int pageTotal  → 256
		this.pageTotal = dao.listCnt();
		//#2한페이지당	:  int onePageLimit → 10
		this.onePageLimit = 10;
		//#3하단페이지수 : int bottomPageAll → 256/10 = 25.6=> 26
		this.bottomPageAll = (int)Math.ceil((float)this.pageTotal /(float) this.onePageLimit);
		//#4 하단페이지당 : 	int bottomPageLimit → 10
		this.bottomPageLimit = 10;
		/////////////////////////////////////////////////////////////////////////////////
		//#5 하단현재페이지 : 		int bottomCurrent 	→ 	5   18
		this.bottomCurrent = (int)Math.floor(pstartno/(float)onePageLimit)+1;												// 테스트할때는 중간부분부터 테스트
		//#6 하단페이지시작 : 		int bottomStart 	→  	1	11	21
		this.bottomStart = (int)(Math.floor((bottomCurrent-1)/(float)bottomPageLimit))* bottomPageLimit +1;
		//#7 하단페이지끝	   : 	int endStart 	 	→  	10 	20	30
		this.bottomEnd = this.bottomStart + bottomPageLimit -1;
		//시작 21 -> 끝 30
		//    21 -> 26
		if( this.bottomPageAll < this.bottomEnd) { this.bottomEnd = this.bottomPageAll; };
		/////////////////////////////////////////////////////////////////////////////////
		//#8 10개의 리스트	   :	List<JSTLItem> List10 → limit ?,10 / 0,10 / 10,10
		this.list10 = dao.listHotel(pstartno);
		//#9 페이징시작번호 : 	int pstartno	
		this.pstartno = pstarthno;
	}
}
