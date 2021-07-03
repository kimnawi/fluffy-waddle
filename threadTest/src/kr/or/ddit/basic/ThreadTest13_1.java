package kr.or.ddit.basic;

import java.util.Arrays;

public class ThreadTest13_1 {

	public static void main(String[] args) {
		Horse[] horses = new Horse[]{
				new Horse("알렉산더"),
				new Horse("스피더"),
				new Horse("추풍적토마"),
				new Horse("우사인볼트"),
				new Horse("적로"),
				new Horse("유린청"),
				new Horse("용등자"),
				new Horse("현표"),
				new Horse("응상백"),
				new Horse("추풍오")	
		};
		
		GameState gs = new GameState(horses);
		
		for(Horse h: horses){
			h.start();
		}
		
		gs.start();
		
		try {
			gs.join();
		} catch (InterruptedException e) {
		}
		
		System.out.println();
		System.out.println("경기가 종료되었습니다.");
		System.out.println();
		
		//등수의 오름차순으로 정렬하기
		Arrays.sort(horses);
		
		System.out.println("-- 경기 결과 --");
		for(Horse h: horses){
			System.out.println(h);
		}
	}
	
}


class Horse extends Thread implements Comparable<Horse>{
	public static int currentRank = 0;
	
	private String horseName;	//말이름
	private int position;		//현재 위치
	private int rank;			//등수
	
	//생성자
	public Horse(String horseName) {
		this.horseName = horseName;
	}


	public String getHorseName() {
		return horseName;
	}

	public void setHorseName(String horseName) {
		this.horseName = horseName;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
	
	@Override
	public String toString() {
		return horseName + "(은)는 " + rank + "등 입니다.";
	}


	//등수를 기준으로 오름차순 정렬의 내부 정렬 기준 설정하기
	@Override
	public int compareTo(Horse h) {
		return Integer.compare(rank, h.getRank());
	}
	
	@Override
	public void run() {
		for(int i = 1; i <= 50; i++){
			this.position = i;
			try {
				Thread.sleep((int)(Math.random() * 400));
			} catch (InterruptedException e) {
			}
		}
		//한 마리의 말이 경주가 끝나면 등수를 구한다.
		currentRank++;
		this.rank = currentRank;
	}
	
}

//경기 중에 말의 현재 위치를 나타내는 thread
class GameState extends Thread{
	Horse[] hoarr;	//게임에 참여한 말들의 정보가 저장될 배열 선언
	
	//생성자
	public GameState(Horse[] hoarr) {
		this.hoarr = hoarr;
	}
	
	@Override
	public void run() {
		while(true){
			//모든 말들의 경기가 종료되었는지 여부 검사
			if(Horse.currentRank == hoarr.length){
				break;
			}
			for(int i = 1; i <= 15; i++){
				System.out.println();
			}
			
			for(int i = 0; i < hoarr.length; i++){
				System.out.print(hoarr[i].getHorseName() + "\t: ");
				for(int j = 1; j <= 50; j++){
					if(hoarr[i].getPosition() == j){
						System.out.print(">");
					}else{
						System.out.print("-");
					}
				}
				System.out.println();	//줄바꿈
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				
			}
		}
	}
}

































