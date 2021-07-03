package kr.or.ddit.basic;

import javax.swing.JOptionPane;

public class ThreadTest06 {

	public static void main(String[] args) {
		Thread th1 = new DataInput();
		Thread th2 = new MyCountDown();
		
		th1.start();
		th2.start();
		
	}

}


//데이터를 입력하는 thread
class DataInput extends Thread{
	//입력 여부를 확인하기 위한 변수 선언 -> thread에서 공통으로 사용할 변수
	public static boolean inputCheck = false;
	@Override
	public void run() {
		String str = JOptionPane.showInputDialog("아무거나 입력하세요.");
		
		inputCheck = true;	//입력이 완료되면 inputCheck값을 true로 변경
		System.out.println("입력값: " + str);
	}
}

//카운트다운을 진행하는 thread
class MyCountDown extends Thread{
	@Override
	public void run() {
		for(int i = 10; i > 0; i--){
			//입력이 완료되었는지 여부를 검사해서 입력이 완료되면 thread를 종료시킨다.
			if(DataInput.inputCheck == true){
				return;	//run()메서드가 종료되면 해당 thread가 종료된다.
			}
			
			System.out.println(i);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
		System.out.println("10초가 지났습니다. 프로그램을 종료합니다.");
		System.exit(0);
	}
}