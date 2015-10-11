package org.jpstool.smartcore;

import java.io.Serializable;

import org.jpstool.main.WordItem;

public class ProfileLearning implements Serializable {
	private WordItem word;
	private long appearCount;
	private long notKnewCount;
	private long knewCount;

	public ProfileLearning(WordItem word, long appearCount, long notKnewCount, long knewCount) {
		this.word = word;
		this.appearCount = appearCount;
		this.notKnewCount = notKnewCount;
		this.knewCount = knewCount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((word == null) ? 0 : word.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProfileLearning other = (ProfileLearning) obj;
		if (word == null) {
			if (other.word != null)
				return false;
		} else if (!word.equals(other.word))
			return false;
		return true;
	}

	public WordItem getWord() {
		return word;
	}

	public void setWord(WordItem word) {
		this.word = word;
	}

	public long getAppearCount() {
		return appearCount;
	}

	public void setAppearCount(long appearCount) {
		this.appearCount = appearCount;
	}

	public long getNotKnewCount() {
		return notKnewCount;
	}

	public void setNotKnewCount(long notKnewCount) {
		this.notKnewCount = notKnewCount;
	}

	public long getKnewCount() {
		return knewCount;
	}

	public void setKnewCount(long knewCount) {
		this.knewCount = knewCount;
	}
	
	public void increaseFail() {
		this.notKnewCount++;
	}
	
	public void inscreaseKnew() {
		this.knewCount++;
	}

	@Override
	public String toString() {
		return "ProfileLearning [word=" + word + ", appearCount=" + appearCount + ", notKnewCount=" + notKnewCount + ", knewCount=" + knewCount + "]";
	}
}
