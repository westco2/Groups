package com.project.groups.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.Data;

@Data
public class PageVO {
	private int start; //페이지네이션 시작
	private int end; //페이지네이션 끝
	private boolean prev; //이전버튼 활성화여부
	private boolean next; //다음버튼 활성화여부
	private int page; //현재조회 페이지번호(criteria)
	private int amount; //조회데이터개수(criteria)
	private int total; //전체데이터개수
	private int realEnd; //종료번호
	private Criteria cri; //페이지 기준
	private List<Integer> pageList; //페이지 시작~끝 item
	
	//생성자 - 계산에 필요
	public PageVO(Criteria cri, int total) {
		//생성될 때, 페이지번호, 데이터개수, 전체게시글수 수 초기값 설정
		this.page = cri.getPage();
		this.amount = cri.getAmount();
		this.total = total;
		this.cri = cri;
	
	//시작페이지 계산
	//끝페이지 - 페이지네이션 개수 + 1
	this.start = this.end - 10 + 1;
		
	//끝페이지 계산 = 현재조회하는 페이지에 따라 변함
	//ex) page 11이면, 끝페이지 20 / 1이면 10, 31이면 40
	this.end = (int)(Math.ceil(this.page / 5.0 )) * 5;
		
	//종료페이지 계산 - realEnd
	//총게시글 53개-끝10, 종료6 / 163개-끝20, 종료 17 / 163>1~100조회 - 끝10
	//(int)올림(총게시글수/화면에 뿌려지는 데이터개수)
	this.realEnd = (int)(Math.ceil(this.total / (double)this.amount));
	
	//끝페이지 재계산
	if(this.end > this.realEnd) {
		this.end = this.realEnd;
	}
	
	//이전버튼 활성화여부
	//start 1, 11, 21, 31..
	this.prev = this.start > 1; //1보다 크면 true
	
	//다음버튼 활성화여부 - 끝페이지 재계산과 연관
	this.next = this.realEnd > this.end;
	
	//thymeleaf에서는 향상된 for문밖에 지원이 안되서, 페이지번호를 들고 있는 item을 생성
	this.pageList = IntStream.rangeClosed(this.start, this.end)
							 .boxed().collect(Collectors.toList());
	}
}