package org.jpstool.main;

import java.io.IOException;

public interface LoopMechanic {
	public static interface LookMechanicCallBack {
		public void callBack(WordItem wordItem);
	}

	public void loop(LookMechanicCallBack calBack) throws IOException;

	public void stopLoop();
}
