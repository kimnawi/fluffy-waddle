package kr.or.ddit.basic;

//데본 thread 연습 -> 자동 저장 기능 구현하기

public class ThreadTest08 {

	public static void main(String[] args) {
		AutoSaveThread auto = new AutoSaveThread();
		
		//데몬 thread로 설정하기 -> 반드시 start()메서드 호출 전에 설정해야 한다.
		auto.setDaemon(true);	//데몬thread를 사용하지 않으면 끝이 없음
		//setDaemon을 start보다 앞에 둬야 한다.
		
		auto.start();
		
		try {
			for(int i = 1; i <= 20; i++){
				System.out.println(i);
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
		}
		System.out.println("main thread 종료");
	}

}


//자동 저장하는 thread(3초에 한 번씩 저장하는 thread)
class AutoSaveThread extends Thread{
	//작업 내용을 저장하는 메서드
	public void save(){
		System.out.println("작업 내용을 저장합니다.");
	}
	
	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
			}
			save();
		}
	}
}