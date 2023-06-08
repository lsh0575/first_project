package com.threadpool.dto;

public class TravelDto {
	
	private int tno;
	private String tname;
	private String tpass;
	private String ttitle;
	private String tcontent;
	private String tcost;
	private int tscore;
	private String tupload_date;
	private String tstart_date;
	private String tend_date;
	private String timage;
	private int tcategory;
	private String tcategory_name;
	private int thit;
	private String tip;
	private String timages_1;
	private String timages_2;
	private String timages_3;
	
	public TravelDto() { super(); }
	

	// update_hit DTO
	public TravelDto(int tno) {
		super();
		this.tno = tno;
	}
	
	public TravelDto(int tno,  String tpass) {
		super();
		this.tno = tno;
		this.tpass = tpass;
	}
	
	// update DTO
	public TravelDto(String ttitle,  String tcontent, String tcost,
			String tstart_date, String tend_date, String tpass) {
		super();
		this.ttitle = ttitle;
		this.tcontent = tcontent;
		this.tcost = tcost;
		this.tstart_date = tstart_date;
		this.tend_date = tend_date;
		this.tpass = tpass;
	}
	
	public TravelDto(int tno, String tname, String ttitle, String tupload_date, int thit, String timages_1) {
		super();
		this.tno = tno;
		this.tname = tname;
		this.ttitle = ttitle;
		this.tupload_date = tupload_date;
		this.thit = thit;
		this.timages_1 = timages_1;
	}

	// insert DTO
	public TravelDto(int tno, String tname, String tpass, String ttitle, String tcontent, String tcost, int tscore,
			String tupload_date, String tstart_date, String tend_date, String timage, int tcategory, int thit,
			String tip) {
		super();
		this.tno = tno;
		this.tname = tname;
		this.tpass = tpass;
		this.ttitle = ttitle;
		this.tcontent = tcontent;
		this.tcost = tcost;
		this.tscore = tscore;
		this.tupload_date = tupload_date;
		this.tstart_date = tstart_date;
		this.tend_date = tend_date;
		this.timage = timage;
		this.tcategory = tcategory;
		this.thit = thit;
		this.tip = tip;
	}

	// detail DTO
	public TravelDto(int tno, String tname, String tpass, String ttitle, String tcontent, String tcost, int tscore,
			String tstart_date, String tend_date, String tcategory_name, int thit,
			String timages_1, String timages_2, String timages_3) {
		super();
		this.tno = tno;
		this.tname = tname;
		this.tpass = tpass;
		this.ttitle = ttitle;
		this.tcontent = tcontent;
		this.tcost = tcost;
		this.tscore = tscore;
		this.tstart_date = tstart_date;
		this.tend_date = tend_date;
		this.tcategory_name = tcategory_name;
		this.thit = thit;
		this.timages_1 = timages_1;
		this.timages_2 = timages_2;
		this.timages_3 = timages_3;
	}

	
	// List DTO
	public TravelDto(int tno, String tname, String tpass, int tcategory, String ttitle, String tcontent, int tscore,
			String tupload_date, String tcost, String tstart_date, String tend_date, int thit, String tip,
			String timages_1, String timages_2, String timages_3) {
		super();
		this.tno = tno;
		this.tname = tname;
		this.tpass = tpass;
		this.tcategory = tcategory;
		this.ttitle = ttitle;
		this.tcontent = tcontent;
		this.tscore = tscore;
		this.tupload_date = tupload_date;
		this.tcost = tcost;
		this.tstart_date = tstart_date;
		this.tend_date = tend_date;
		this.thit = thit;
		this.tip = tip;
		this.timages_1 = timages_1;
		this.timages_2 = timages_2;
		this.timages_3 = timages_3;
	}


	public int getTno() { return tno; }
	public void setTno(int tno) { this.tno = tno; }
	
	public String getTname() { return tname; }
	public void setTname(String tname) { this.tname = tname; }
	
	public String getTpass() { return tpass; }
	public void setTpass(String tpass) { this.tpass = tpass; }
	
	public String getTtitle() { return ttitle; }
	public void setTtitle(String ttitle) { this.ttitle = ttitle; }
	
	public String getTcontent() { return tcontent; }
	public void setTcontent(String tcontent) { this.tcontent = tcontent; }
	
	public String getTcost() { return tcost; }
	public void setTcost(String tcost) { this.tcost = tcost; }

	public int getTscore() { return tscore; }
	public void setTscore(int tscore) { this.tscore = tscore; }
	
	public String getTupload_date() { return tupload_date; }
	public void setTupload_date(String tupload_date) { this.tupload_date = tupload_date; }
	
	public String getTstart_date() { return tstart_date; }
	public void setTstart_date(String tstart_date) { this.tstart_date = tstart_date; }
	
	public String getTend_date() { return tend_date; }
	public void setTend_date(String tend_date) { this.tend_date = tend_date; }
	
	public String getTimage() { return timage; }
	public void setTimage(String timage) { this.timage = timage; }

	public int getTcategory() { return tcategory; }
	public void setTcategory(int tcategory) { this.tcategory = tcategory; }
	
	public String getTip() { return tip; }
	public void setTip(String tip) { this.tip = tip; }

	public int getThit() { return thit; }
	public void setThit(int thit) { this.thit = thit; }
	

	public String getTimages_1() { return timages_1; }
	public void setTimages_1(String timages_1) { this.timages_1 = timages_1; }

	public String getTimages_2() { return timages_2; }
	public void setTimages_2(String timages_2) { this.timages_2 = timages_2; }


	public String getTimages_3() { return timages_3; }
	public void setTimages_3(String timages_3) { this.timages_3 = timages_3; }

	public String getTcategory_name() { return tcategory_name; }
	public void setTcategory_name(String tcategory_name) { this.tcategory_name = tcategory_name; }


	@Override
	public String toString() {
		return "TravelDto [tno=" + tno + ", tname=" + tname + ", tpass=" + tpass + ", ttitle=" + ttitle + ", tcontent="
				+ tcontent + ", tcost=" + tcost + ", tscore=" + tscore + ", tupload_date=" + tupload_date
				+ ", tstart_date=" + tstart_date + ", tend_date=" + tend_date + ", timage=" + timage + ", tcategory="
				+ tcategory + ", thit=" + thit + ", tip=" + tip + "]";
	}
	
}
