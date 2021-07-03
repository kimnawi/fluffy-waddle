package kr.or.ddit.basic;

//yield()메서드 연습

public class ThreadTest10 {

	public static void main(String[] args) {
		YieldThread th1 = new YieldThread("1번 thread");
		YieldThread th2 = new YieldThread("2번 thread");
		
		th1.start();
		th2.start();
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
		}
		System.out.println("-------------------------------------------111111111111111");
		th1.work = false;
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
		}
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^222222222222222");
		th2.work = true;
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
		}
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		th1.stop = true;
		th2.stop = true;
		
	}

}


//yield()메서드 연습용 thread
class YieldThread extends Thread{
	public boolean stop = false;	//thread의 종료 여부를 나타내는 변수
	public boolean work = true;		//작업의 처리 여부를 나타내는 변수
	
	public YieldThread(String name){
		super(name);	//thread의 이름을 설정한다.
	}
	@Override
	public void run() {
		while(!stop){	//stop이 true가 되면 반복문이 종료된다.
			if(work){
				//getName() -> thread에 설정된 thread의 이름을 반환한다.
				System.out.println(getName() + "작업 중");
			}else{
				System.out.println(getName() + "양보");
				Thread.yield();
			}
		}
		System.out.println(getName() + "thread 종료");
	}
}
