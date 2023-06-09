package com.threadpool.service.travel;

import java.util.List;

import com.threadpool.dao.TravelDao;
import com.threadpool.dto.TravelDto;

public class TPaging {
	private int pageTotal;
	private int onePageLimit;
	private int bottomPageAll;
	private int bottomPageLimit;
	private int bottomCurrent;
	private int bottomStart;
	private int bottomEnd;
	private List<TravelDto> list10;
	private int pstartno;
	public TPaging() { super(); }
	
	public TPaging(int pstartno) {
		TravelDao dao = new TravelDao();
		this.pageTotal = dao.listCnt();
		this.onePageLimit = 10;
		this.bottomPageAll = (int)Math.ceil(this.pageTotal / (float)this.onePageLimit);
		this.bottomPageLimit = 10;
		this.bottomCurrent = (int)Math.floor(pstartno/(float)this.onePageLimit)+1;
		this.bottomStart = (int)(Math.floor((this.bottomCurrent-1)/this.bottomPageLimit)*10+1);
		this.bottomEnd = (this.bottomStart + this.bottomPageLimit - 1)<bottomPageAll?(this.bottomStart + this.bottomPageLimit - 1):bottomPageAll;
		this.list10 = dao.listpage(pstartno);
		this.pstartno = pstartno;
	}
	
	public TPaging(int pageTotal, int onePageLimit, int bottomPageAll, int bottomPageLimit, int bottomCurrent,
			int bottomStart, int bottomEnd, List<TravelDto> list10, int pstartno) {
		super();
		this.pageTotal = pageTotal;
		this.onePageLimit = onePageLimit;
		this.bottomPageAll = bottomPageAll;
		this.bottomPageLimit = bottomPageLimit;
		this.bottomCurrent = bottomCurrent;
		this.bottomStart = bottomStart;
		this.bottomEnd = bottomEnd;
		this.list10 = list10;
		this.pstartno = pstartno;
	}

	@Override
	public String toString() {
		return "TPaging [pageTotal=" + pageTotal + ", onePageLimit=" + onePageLimit + ", bottomPageAll=" + bottomPageAll
				+ ", bottomPageLimit=" + bottomPageLimit + ", bottomCurrent=" + bottomCurrent + ", bottomStart="
				+ bottomStart + ", bottomEnd=" + bottomEnd + ", list10=" + list10 + ", pstartno=" + pstartno + "]";
	}

	public int getPageTotal() { return pageTotal; }
	public void setPageTotal(int pageTotal) { this.pageTotal = pageTotal; }

	public int getOnePageLimit() { return onePageLimit; }
	public void setOnePageLimit(int onePageLimit) { this.onePageLimit = onePageLimit; }

	public int getBottomPageAll() { return bottomPageAll; }
	public void setBottomPageAll(int bottomPageAll) { this.bottomPageAll = bottomPageAll; }

	public int getBottomPageLimit() { return bottomPageLimit; }
	public void setBottomPageLimit(int bottomPageLimit) { this.bottomPageLimit = bottomPageLimit; }

	public int getBottomCurrent() { return bottomCurrent; }
	public void setBottomCurrent(int bottomCurrent) { this.bottomCurrent = bottomCurrent; }

	public int getBottomStart() { return bottomStart; }
	public void setBottomStart(int bottomStart) { this.bottomStart = bottomStart; }

	public int getBottomEnd() { return bottomEnd; }
	public void setBottomEnd(int bottomEnd) { this.bottomEnd = bottomEnd; }

	public List<TravelDto> getList10() { return list10; }
	public void setList10(List<TravelDto> list10) { this.list10 = list10; }

	public int getPstartno() { return pstartno; }
	public void setPstartno(int pstartno) { this.pstartno = pstartno; }
	
	
}
