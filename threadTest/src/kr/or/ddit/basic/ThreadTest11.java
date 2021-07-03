package kr.or.ddit.basic;

/*
 * Thread의 stop()메서드를 호출하면 Thread가 바로 멈춘다.
 * 이 때 사용하던 자원을 정리하지 못하고 프로그램이 종료되어 나중에 실행되는 프로그램에 영향을 줄 수 있다.
 * 그래서 stop()메서드는 비추천으로 되어있다.
 */

public class ThreadTest11 {

	public static void main(String[] args) {
//		ThreadStopTest1 th1 = new ThreadStopTest1();
//		th1.start();
//		
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//		}
//		th1.setStop(true);
////		th1.stop();		//──────: 사용은 가능하나 앞으로의 버전에서는 지원하지 않을 수도 있다.(비추천)
		
		ThreadStopTest2 th2 = new ThreadStopTest2();
		th2.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		
		th2.interrupt();
		
	}
	
}


//thread를 멈추게 하는 연습용 thread
class ThreadStopTest1 extends Thread{
	private boolean stop;	//기본값이 false가 된다.
	
	public void setStop(boolean stop){
		this.stop = stop;
	}
	
	@Override
	public void run() {
		while(!stop){
			System.out.println("thread 실행 중");
		}
		System.out.println("자원 정리");
		System.out.println("thread 종료");
	}
}

//interrupt()메서드를 이용하여 thread를 멈추게 하는 방법
class ThreadStopTest2 extends Thread{
	@Override
	public void run() {
//		//방법1: interrupt()메서드와 sleep()메서드를 이용하는 방법
//		try {
//			while(true){
//				System.out.println("thread 실행 중");
//				Thread.sleep(1);
//			}
//		} catch (InterruptedException e) {
//		}
		
		//방법2
		while(true){
			System.out.println("thread 실행 중");
			
			//interrupt()메서드가 호출되었는지를 검사한다.
			
//			//검사 방법1:	thread의 인스턴스 메서드인 isInterrupted()메서드 이용하기
//			//			isInterrupted()메서드는 interrupt()메서드가 호출되면 true를 반환한다.
//			if(this.isInterrupted()){
//				break;
//			}
			
			//검사 방법2: 	thread의 정적(static) 메서드인 interrupted()메서드 이용하기
			//			이 메서드도 interrupt()메서드가 호출되면 true를 반환한다.
			if(Thread.interrupted()){
				break;
			}
		}
		
		
		System.out.println("자원 정리");
		System.out.println("thread 종료");
		
	}
}





















