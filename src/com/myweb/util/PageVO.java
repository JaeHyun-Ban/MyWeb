package com.myweb.util;

public class PageVO {
	
	//화면에 그려질 pageNation을 계산하는 클래스(pageNum과 전체게시글 수, amount를 가지고 다님)
	
	private int startPage;//게시글 화면에 보여질 첫번째 번호
	private int endPage;//게시글 화면에 보여질 마지막 번호
	private boolean prev, next;//다음, 이전버튼 활성화 여부
	
	private int pageNum;//조회하고 있는 페이지 번호
	private int amount;//화면에 보여질 데이터 개수
	private int total;//전체 게시글 수
	
	//생성될 때 계산처리
	public PageVO(int pageNum, int amount, int total) {
		//매개값을 넣어줘야만 필요한 값들을 계산할 수 있다.
		
		//초기화
		this.pageNum = pageNum;
		this.amount = amount;
		this.total = total;
		
		//순서대로 계산을 진행 해 줄 것
		//1. endPage 계산
		//현재페이지 번호 1~10이다 -> 화면에 보여질 끝 번호 -10
		//현재페이지 번호 14 -> 화면에 보여질 끝번호 - 20
		//공식: (int)Math.ceil(페이지번호 / 페이지 바 길이) x 페이지 바 길이>> 올림을 이용해서 계산
		this.endPage = (int)Math.ceil(this.pageNum / (double)10) * 10; 
		
		
		//2. startPage계산
		//공식: 끝페이지 - 화면에보여질 페이지 수
		this.startPage = this.endPage - 10 + 1;
		
		
		//3. realEnd 실제 끝번호
		//만약 게시글이 52개라면?-> 끝페이지 번호 6
		//만약 게이글이 105개라면? -> 끝페이지 번호 11
		//공식: (int)Math.ceil(전체게시글 개수 / 보여지는 데이터 개수) >> 올림을 이용 +1이 자동으로 됨
		int realEnd = (int)Math.ceil(this.total / (double)this.amount);
		
		//마지막 페이지 도달시에 보여져야하는 끝번호가 달라져야 한다
		//ex) 131개 게시물 
		//1번페이지 클릭시 -> endPage = 10, realEnd = 14(endPage로 세팅)
		//11번페이지 클릭시 -> endPage = 20, realEnd = 14(realEnd로 세팅)
		//결론 endPage > realEnd = realEnd를 세팅
		//endPage < realEnd = endPage를 세팅
		if(endPage > realEnd) {
			this.endPage = realEnd;
		}
		
		//4. 이전버튼 활성화 여부(1페이지만 아니면 다 활성화)
		//startPage점핑값 1, 11, 21, 31,...형태로 진행
		this.prev = this.startPage > 1;
		
		//5. 다음페이지 활성화 여부
		//위 realEnd와 endPage서로 연관이 있음
		//ex) 131개 게시물
		//1~10번 조회 endPage = 10, realEnd = 14 -> 다음버튼 활성화 true
		//11번 조회시 endPage = 14, realEnd = 14 -> 다음버튼 활성화 false
		this.next = realEnd > endPage;
		
//		System.out.println("PageVO [startPage=" + startPage + ", endPage=" + endPage + ", realEnd=" + realEnd + ", prev=" + prev + ", next=" + next
//				+ ", pageNum=" + pageNum + ", amount=" + amount + ", total=" + total + "]");
		
	}


	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
	
	
	
	
	
}













































