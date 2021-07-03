//package kr.or.ddit.basic;
//
///*
// * 10마리의 말들이 경주하는 프로그램을 작성하시오.
// * 
// * 말은 Horse라는 이름의 thread클래스로 작성하는데,
// * 이 클래스는 말이름(String), 현재위치(int), 등수(int)를 멤버변수로 가진다.
// * 그리고 이 클래스에는 등수를 오름차순으로 처리할 수 있는 내부 정렬 기준이 있다.(Comparable인터페이스 구현)
// * 
// * 경기 구간은 1 ~ 50 구간으로 되어있다.
// * 
// * 경기 중간 중간에 각 말들의 위치를 아래와 같이 나타낸다.
// * 예)
// * 01번말: --->------------------------------------------------
// * 02번말: ----->----------------------------------------------
// * 03번말: -->-------------------------------------------------
// * ...
// * 10번말: ---->-----------------------------------------------
// * 경기가 끝나면 등수 순으로 출력한다.
// */
//
//public class ThreadTest13 {
//
//	public static void main(String[] args) {
//		Horse[] threads = new Horse[]{
//				new Horse("알렉산더"),
//				new Horse("스피더"),
//				new Horse("추풍적토마"),
//				new Horse("우사인볼트"),
//				new Horse("적로"),
//				new Horse("유린청"),
//				new Horse("용등자"),
//				new Horse("현표"),
//				new Horse("응상백"),
//				new Horse("추풍오")
//		};
//		
//		for(Horse hor: threads){
//			hor.start();
//		}
//		
//		for(Horse hor: threads){
//			try {
//				hor.join();
//			} catch (InterruptedException e) {
//			}
//		}
//		System.out.println();
//		System.out.println("경기가 종료되었습니다.");
//		System.out.println("────────────────────────────────────────");
//		System.out.println("-- 경기 결과 --");
//		System.out.println(Horse.result);
//		
//	}
//	
//}
//
//class Horse extends Thread{
//	public static int rank;
//	private String name;
//	public static String result = "";
//	
//	public Horse(String name){
//		this.name = name;
//	}
//	
//	@Override
//	public void run() {
//		for (int i = 1; i <= 50; i++){
//			System.out.print(name + "\t: ");
//			for(int j = 1; j <= i; j++){
//				System.out.print("-");
//			}
//			System.out.print(">");
//			for(int j = i; j < 50; j++){
//				System.out.print("-");
//			}
//			System.out.println();
//			try {
//				Thread.sleep((int)(Math.random() * 300 + 201));
//			} catch (InterruptedException e) {
//			}
//		}
//		try {
//			Thread.sleep(100);
//		} catch (InterruptedException e) {
//		}
//		System.out.println("!!" + name + " 결승선 도착!!");
//		rank += 1;
//		result += rank + "등: " + name +"\n";
//
//		
//	}
//}
