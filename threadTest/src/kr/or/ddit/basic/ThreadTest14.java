package kr.or.ddit.basic;

//thread에서 객체를 공통으로 사용하는 예제
/*
 * 원주율을 예산하는 thread와 계산된 원주율을 출력하는 thread가 있다.
 * 
 * 원주율을 저장하는 객체가 필요하다.
 * 이 객체를 두 thread에서 공통으로 사용해서 처리한다.
 */

public class ThreadTest14 {

	public static void main(String[] args) {
		//공통으로 사용할 객체 생성
		ShareData sd = new ShareData();
		
		//thread 객체를 생성하고 공통으로 사용할 객체를 thread에 주입한다.
		CalcPIThread cpi = new CalcPIThread();
		cpi.setSd(sd);
		
		PrintPIThread ppi = new PrintPIThread(sd);
		
		cpi.start();
		ppi.start();
		
	}
	
}

//원주율을 관리하는 클래스(공통으로 사용할 클래스)
class ShareData{
	public double result;	//계산된 원주율이 저장될 변수
	public volatile boolean isOk;	//계산이 완료되었는지 여부를 나타내는 변수
	//volatile: 이 키워드가 붙은 변수는 컴파일러의 최적화 대상에서 제외한다.
	//			즉, 이 변수의 값이 변경되면 즉시 변수에 적용시킨다.
	
}

//원주율을 계산하는 thread
class CalcPIThread extends Thread{
	private ShareData sd;
	
	//setter
	public void setSd(ShareData sd){
		this.sd = sd;
	}
	@Override
	public void run() {
		while(true){
			if(sd.isOk == true){
				break;
			}
		}
		System.out.println();
		System.out.println("결과: " + sd.result);
		System.out.println("PI: " + Math.PI);
	}
}

//계산이 완료되면 계산된 원주율을 출력하는 thread
class PrintPIThread extends Thread{
	private ShareData sd;
	
	//생성자
	public PrintPIThread(ShareData sd){
		this.sd = sd;
	}
	
	@Override
	public void run() {
		//원주율 = (1/1 - 1/3 + 1/5 - 1/7 + ...) * 4
		
		double sum = 0.0;
		for(int i = 1; i <= 1000000000; i += 2){	//1부터 2씩 증가되는 반복문
			if((i / 2) % 2 == 0){
				sum += 1.0 / i;
			}else{
				sum -= 1.0 / i;
			}
		}
		sd.result = sum * 4;
		sd.isOk = true;
	}
}



































