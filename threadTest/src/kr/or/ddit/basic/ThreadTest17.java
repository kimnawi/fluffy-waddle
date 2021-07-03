package kr.or.ddit.basic;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * 은행의 입출금을 thread로 처리하는 예제
 * (Lock객체를 이용한 동기화 처리 예제)
 */

public class ThreadTest17 {
	
	private int balance;	//잔액
	
	//Lock객체 생성: 되도록이면 private final로 만든다.
	private final Lock lock = new ReentrantLock();
	
	public void setBalance(int balance){
		this.balance = balance;
	}

	public int getBalance(){
		return balance;
	}

	//입금하는 메서드
	public void deposit(int money){
		// Lock객체는 lock()메서드로 락을 설정하고 반드시 unlock()메서드로 락을 해제해줘야 한다.
		lock.lock();	//락 설정 시작
		balance += money;
		lock.unlock();	//락 설정 해제
	}

	//출금하는 메서드
	public boolean withdraw(int money){
		boolean chk = false;
		//try ~ catch 블럭이 사용되는 부분에서는 unlock()메서드는 finally영역에서 호출되도록 해야 한다.
		try {
			lock.lock();
			if(balance >= money){
				for(int i = 1; i <= 100000000; i++){}	//시간 지연용
				balance -= money;
				System.out.println("*** 메서드 안에서 balance: " + getBalance());
				chk = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			lock.unlock();
		}
		return chk;
	}
	
	public static void main(String[] args) {
		final ThreadTest17 acount = new ThreadTest17();
		acount.setBalance(10000);	//잔액을 10000원으로 설정

		//익명 구현체로 thread 구현
		Runnable test = new Runnable() {

			@Override
			public void run() {
				boolean result = acount.withdraw(6000);	//6000원 출금하기
				System.out.println("*** thread에서 출금여부(result): " + result
						+ ", balance: " + acount.getBalance());
			}
		};

		//---------------------------------------------------------------------
		Thread th1 = new Thread(test);
		Thread th2 = new Thread(test);

		th1.start();
		th2.start();
	}
	
}
