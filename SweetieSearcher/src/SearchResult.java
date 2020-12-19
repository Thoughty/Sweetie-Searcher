public class SearchResult implements Comparable
{
	private Document document = null;
	private double score = -1;
	
	public SearchResult(Document document, double score) {
		this.document = document;
		this.score = score;
	}
	public Document getDocument() {
		return document;
	}
	public void setDocument(Document document) {
		this.document = document;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	
	@Override
	public String toString() {
		return "[score=" + score + "]"+document+"\n";
	}
	/**
	 * So that the default sort would be descending on scores.
	 */
	@Override
	public int compareTo(Object o) {
		
		if(this.score == ((SearchResult)o).score) return this.document.compareTo(((SearchResult)o).document);
		
		return -1*Double.compare(this.score,((SearchResult)o).score);
	}
}