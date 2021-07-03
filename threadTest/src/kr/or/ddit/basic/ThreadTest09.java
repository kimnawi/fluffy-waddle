package kr.or.ddit.basic;

//thread의 상태를 출력하는 예제


public class ThreadTest09 {

	public static void main(String[] args) {
		StatePrintThread th = new StatePrintThread(new TargetThread());
		
		th.start();
	}
}


//thread상태의 검사 대상이 되는 thread
class TargetThread extends Thread{
	@Override
	public void run() {
		for(long i = 1L; i <= 20000000000L; i++){}	//시간지연용
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
		}
		for(long i = 1L; i <= 20000000000L; i++){}	//시간지연용
	}
}

//검사 대상의 thread의 상태를 출력하는 thread
class StatePrintThread extends Thread{
	private TargetThread target;
	
	//생성자
	public StatePrintThread(TargetThread target) {
		this.target = target;
	}
	
	@Override
	public void run() {
		while(true){
			
			//thread의 현재 상태값 구하기
			Thread.State state = target.getState();		//현재 상태를 enum값으로 반환
			System.out.println("TargetThread의 현재 상태값: " + state);
			
			if(state == Thread.State.NEW){	//NEW상태: 객체는 만들어졌지만 start하기 전 상태
				target.start();
			}
			
			if(state == Thread.State.TERMINATED){	//targetThread가 작업이 끝나면(종료 상태면)
				break;
			}
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
			}
			
		}
	}
}


















