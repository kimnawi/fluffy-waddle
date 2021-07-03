package kr.or.ddit.basic;

import javax.swing.JOptionPane;

public class ThreadTest07_1 {
	
	public static boolean inputCheck;
	
	public static void main(String[] args) {
	
		GameTimer gt = new GameTimer();
		
		//난수를 이용해서 컴퓨터의 가위바위보 정하기
		String[] data = {"가위", "바위", "보"};
		
		int index = (int)(Math.random() * 3);	//0 ~ 2 사이의 난수만들기
		String com = data[index];	//컴퓨터의 가위바위보를 정한다.
		
		//사용자로부터 가위바위보 입력받기
		gt.start();
		String man = null;
		do{
			man = JOptionPane.showInputDialog("가위바위보");
//		}
//		while(man == null || !(man.equals("가위") || man.equals("바위") || man.equals("보")));
		}
		while(man == null || !man.equals("가위") && !man.equals("바위") && !man.equals("보"));
		
		
		inputCheck = true;	//입력완
		
		//결과 판정하기
		String result = "";
		if(com.equals(man)){
			result = "비겼습니다.";
		}else if(man.equals("가위") && com.equals("보")
				|| man.equals("바위") && com.equals("가위")
				|| man.equals("보") && com.equals("바위")){
			result = "당신이 이겼습니다.";
		}else{
			result = "당신이 졌습니다.";
		}
		
		//결과출력
		System.out.println("-- 결 과 --");
		System.out.println("컴퓨터: " + com);
		System.out.println("사용자: " + man);
		System.out.println("결과: " + result);
	}

}

//카운트다운을 진행하는 스레드
class GameTimer extends Thread{
	@Override
	public void run() {
		System.out.println("카운트다운 시작");
		for (int i = 10; i > 0; i--){
			if(ThreadTest07_1.inputCheck == true){
				return;
			}
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
		System.out.println("-- 결 과 --");
		System.out.println("시간 초과로 당신이 졌습니다.");
		System.exit(0);
	}
}