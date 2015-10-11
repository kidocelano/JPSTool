package org.jpstool.main;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimerLoopMechanic implements LoopMechanic {
	private static int CONST_THREAD_NUBMER = 5;

	private File fileWords;
	private long timeLooping;

	private PickupWordEnigne pickupEngine;
	private LoadWordsEngine loadWordEngine;

	private ScheduledExecutorService scheduleExecutor;

	public TimerLoopMechanic(long timeLooping, PickupWordEnigne pickupEngine, LoadWordsEngine loadWordEngine, File fileWords) {
		this.timeLooping = timeLooping;
		this.pickupEngine = pickupEngine;
		this.loadWordEngine = loadWordEngine;
		this.fileWords = fileWords;
		this.scheduleExecutor = Executors.newScheduledThreadPool(CONST_THREAD_NUBMER);
	}

	@Override
	public void loop(final LookMechanicCallBack calBack) throws IOException {
		final List<WordItem> listWordItem = loadWordEngine.getListWords(fileWords);
		scheduleExecutor.scheduleWithFixedDelay(new Runnable() {

			@Override
			public void run() {
				System.out.println("loop 1");
				calBack.callBack(pickupEngine.pickUpWord(listWordItem));
				System.out.println("loop ");
			}
		}, 0, timeLooping, TimeUnit.SECONDS);
	}

	@Override
	public void stopLoop() {
		if (scheduleExecutor == null) {
			return;
		}
		scheduleExecutor.shutdownNow();
		this.scheduleExecutor = Executors.newScheduledThreadPool(CONST_THREAD_NUBMER);
	}
}
