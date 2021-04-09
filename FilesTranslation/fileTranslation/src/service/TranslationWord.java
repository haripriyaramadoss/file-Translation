package service;

public class TranslationWord {

	private String originalWord;
	private String replaceWord;
	private int count;

	public String getOriginalWord() {
		return originalWord;
	}

	public void setOriginalWord(String originalWord) {
		this.originalWord = originalWord;
	}

	public String getReplaceWord() {
		return replaceWord;
	}

	public void setReplaceWord(String replaceWord) {
		this.replaceWord = replaceWord;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		// return super.hashCode();
		final int prime = 31;
		int result = 1;
		result = prime * result + ((originalWord == null) ? 0 : originalWord.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		// return super.equals(obj);
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TranslationWord other = (TranslationWord) obj;
		if (originalWord == null) {
			if (other.originalWord != null)
				return false;
		} else if (!originalWord.equalsIgnoreCase(other.originalWord))
			return false;
		return true;
	}

}
