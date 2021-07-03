package kr.or.ddit.basic;

public class ThreadTest03 {

	public static void main(String[] args) {
		//스레드가 수행되는 시간 체크해보기
		Thread th = new Thread(new MyRunner());
		
		//1970년 1월 1일0시 0분 0초(표준시간)로부터 경과한 시간을 밀리세컨드(1/1000초) 단위로 반환한다.
		long startTime = System.currentTimeMillis();
		
		th.start();
		
		try {
			th.join();	//현재 실행 중인 스레드(main 스레드)에서 대상이 되는 스레드(변수 th 스레드)가 종료될 때까지 기다린다. 
		} catch (InterruptedException e) {

		}
		
		long endTime = System.currentTimeMillis();
		
		System.out.println("경과 시간: " + (endTime - startTime));
	}

}

class MyRunner implements Runnable{
	@Override
	public void run() {
		long sum = 0L;
		for(int i = 1; i <= 1000000000L; i++){
			sum += i;	//sum = sum + i;
		}
		System.out.println("합계: " + sum);
	}
}
