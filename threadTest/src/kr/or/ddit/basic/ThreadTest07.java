package kr.or.ddit.basic;

import java.util.ArrayList;

import javax.swing.JOptionPane;

/*
 * 컴퓨터 가위바위보를 진행하는 프로그램을 작성하세요.
 * 
 * 컴퓨터의 가위바위보는 난수를 이용하여 구하고,
 * 사용자의 입력은 showInputDialog()메서드를 이용해서 입력받는다.
 * 
 * 입력 시간은 5초로 제한하고 카운트다운을 진행한다.
 * 5초동안 입력이 없으면 게임에 진 것으로 처리한다.
 * 
 * 5초 안에 입력이 완료되면 승패를 구해서 결과를 출력한다.
 * 
 * 결과 예시1) - 5초 안에 입력을 못했을 경우
 * -- 결 과 --
 * 시간초과로 당신이 졌습니다.
 * 
 * 결과 예시2) - 5초 안에 입력을 했을 경우
 * -- 결 과 --
 * 컴퓨터: 가위
 * 사용자: 바위
 * 결과: 당신이 이겼습니다.
 */

public class ThreadTest07 {

	public static void main(String[] args) {
		
		
		Thread th1 = new RSPInput();
		Thread th2 = new RSPCountDown();
		
		th1.start();
		th2.start();
		
	}

}

class RSPInput extends Thread{
	
	public static boolean inputCheck = false;
	
	@Override
	public void run() {
		String str = JOptionPane.showInputDialog("가위바위보");
		
		inputCheck = true;
		System.out.println("사용자: " + str);
		
		ArrayList<String> RSP1 = new ArrayList<String>();
		String[] com = {"가위", "바위", "보"};
		for (int i = 0; i < com.length; i++){
			RSP1.add(com[i]);
		}
		
		double randomValue = Math.random();
		int ran = (int)(randomValue * RSP1.size());
		
		String getRSP = RSP1.get(ran);
		
		RSP1.remove(ran);
		
		System.out.println("컴퓨터: " + getRSP);
		
		if(str.contains("가위")){
			if(getRSP.equals("가위")){
				System.out.println("결과: 비겼습니다.");
			}else if(getRSP.equals("바위")){
				System.out.println("결과: 당신이 졌습니다.");
			}else if(getRSP.equals("보")){
				System.out.println("결과: 당신이 이겼습니다.");
			}else return;
		}else if(str.contains("바위")){
			if(getRSP.equals("가위")){
				System.out.println("결과: 당신이 이겼습니다.");
			}else if(getRSP.equals("바위")){
				System.out.println("결과: 비겼습니다.");
			}else if(getRSP.equals("보")){
				System.out.println("결과: 당신이 졌습니다.");
			}
		}else if(str.contains("보")){
			if(getRSP.equals("가위")){
				System.out.println("결과: 당신이 졌습니다.");
			}else if(getRSP.equals("바위")){
				System.out.println("결과: 당신이 이겼습니다.");
			}else if(getRSP.equals("보")){
				System.out.println("결과: 비겼습니다.");
			}
		}else{
			return;
		}

	}
	
}

class RSPCountDown extends Thread{
	@Override
	public void run(){
		for(int i = 5; i > 0; i--){
			
			if(RSPInput.inputCheck == true){
				return;
			}
			System.out.println(i);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
		System.out.println("시간초과로 당신이 졌습니다.");
		System.exit(0);
	}
}
