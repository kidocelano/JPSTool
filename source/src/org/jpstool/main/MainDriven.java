package org.jpstool.main;

import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainDriven {
	public static void main(String[] args) {
		// new FlashResultFrame().setVisible(true);
		testing_loopRandom();
	}

	private static void testing_loopRandom() {
		ScheduledExecutorService executer = Executors.newScheduledThreadPool(5);

		Runnable runable = new Runnable() {

			@Override
			public void run() {
				System.out.println("heelo 1");
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		executer.scheduleWithFixedDelay(runable, 0, 2, TimeUnit.SECONDS);

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		executer.shutdownNow();
		executer = Executors.newScheduledThreadPool(5);
		executer.scheduleWithFixedDelay(new Runnable() {

			@Override
			public void run() {
				System.out.println("heelo 2");
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, 0, 2, TimeUnit.SECONDS);
	}
}
