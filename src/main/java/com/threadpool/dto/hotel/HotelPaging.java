package com.threadpool.dto.hotel;

import java.util.List;

import com.threadpool.dao.HotelDao;

public class HotelPaging { // 재활용 가능

		int pageTotal;
		int onePageLimit;
		int bottomPageAll; // Math.ceil
		int bottomPageLimit;
		int bottomCurrent;
		int bottomStart;
		int bottomEnd;
		List<HotelDtoProd> list10;
		int pstartno;
		public HotelPaging() { super(); }
		public HotelPaging(int pstartno) {
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
			this.list10 = dao.HotelListpage(pstartno);
			//#9 페이징시작번호 : 	int pstartno	
			this.pstartno = pstartno;
		}
		
		
		
		
		
		
		public HotelPaging(int pageTotal, int onePageLimit, int bottomPageAll, int bottomPageLimit, int bottomCurrent,
				int bottomStart, int endStart, List<HotelDtoProd> list10, int pstartno) {
			super();
			this.pageTotal = pageTotal;
			this.onePageLimit = onePageLimit;
			this.bottomPageAll = bottomPageAll;
			this.bottomPageLimit = bottomPageLimit;
			this.bottomCurrent = bottomCurrent;
			this.bottomStart = bottomStart;
			this.bottomEnd = endStart;
			this.list10 = list10;
			this.pstartno = pstartno;
		}

		public int getPageTotal() { return pageTotal; } public void setPageTotal(int pageTotal) { this.pageTotal = pageTotal; }
		public int getOnePageLimit() { return onePageLimit; } public void setOnePageLimit(int onePageLimit) { this.onePageLimit = onePageLimit; }
		public int getBottomPageAll() { return bottomPageAll; } public void setBottomPageAll(int bottomPageAll) { this.bottomPageAll = bottomPageAll; }
		public int getBottomPageLimit() { return bottomPageLimit; } public void setBottomPageLimit(int bottomPageLimit) { this.bottomPageLimit = bottomPageLimit; }
		public int getBottomCurrent() { return bottomCurrent; } public void setBottomCurrent(int bottomCurrent) { this.bottomCurrent = bottomCurrent; }
		public int getBottomStart() { return bottomStart; } public void setBottomStart(int bottomStart) { this.bottomStart = bottomStart; }
		public List<HotelDtoProd> getList10() { return list10; } public void setList10(List<HotelDtoProd> list10) { this.list10 = list10; }
		public int getPstartno() { return pstartno; } public void setPstartno(int pstartno) { this.pstartno = pstartno; }
		public int getBottomEnd() { return bottomEnd; } public void setBottomEnd(int bottomEnd) { this.bottomEnd = bottomEnd; }
		@Override
		public String toString() {
			return "HotelPaging [pageTotal=" + pageTotal + ", onePageLimit=" + onePageLimit + ", bottomPageAll="
					+ bottomPageAll + ", bottomPageLimit=" + bottomPageLimit + ", bottomCurrent=" + bottomCurrent
					+ ", bottomStart=" + bottomStart + ", bottomEnd=" + bottomEnd + ", list10=" + list10 + ", pstartno="
					+ pstartno + "]";
		} }
////STEP 1)
//전체글		:  int pageTotal  → 256
//한페이지당	:  int onePageLimit → 10
//전체페이지 수 : int bottomPageAll →
//256/10 = 25.6=> 26  , 21/10 = 2.1=>  3 , 33/10 =3.3 => 4
//		  250/10 = 25  => 25
//		  Math.ceil
//하단페이지당 : 	int bottomPageLimit → 10
//하단현재페이지 : 	int bottomCurrent → 5   18
//하단페이지시작 : 	int bottomStart →  1	11	21
//하단페이지끝	   : 	int endStart 	 →  10 	20	30
//10개의 리스트	   :	List<JSTLItem> List10 → limit ?,10 / 0,10 / 10,10
//페이징시작번호 : 	int pstartno

/////STEP 2)
//#5 하단현재페이지 : 		int bottomCurrent 	→ 	5   18
/*
			 1(0~9)			2(10~19)			   3(20~29).....	  10(90~99)	다음>>
<<이전	11(100~109)		 12(110~119)			13(120~129).....	20(190~199)	다음>>
		
ver-1
	 5	pstartno( 40)	->  5	 40/10   4+1
	15	pstartno(140)	-> 15	140/10	14+1
	25	pstartno(240)	-> 25	240/10	24+1
ver-2
   0	> 1	 0/10.0 = 0.0+1	Math.floor()
   1	> 1  1/10.0 = 0.1+1
   9	> 1  9/10.0 = 0.9+1
   
  10	> 2	 10/10.0 =  1.0+1
  11	> 2	 11/10.0 =  1.1+1
  19	> 2  19/10.0 =  1.2+1
   
  20	> 3  20/10.0 =  2.0+1
  21	> 3  21/10.0 =  2.1+1
  29	> 3  29/10.0 =  2.9+1
 	[원본] 즐거운하루!  -	(delete)
 		ㄴ ㅎㅎㅎ
 			ㄴ 웃겨요...
 				화이팅예여~! (개인정보 문제가 있음)
 */