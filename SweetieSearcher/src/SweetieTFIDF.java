import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

public class SweetieTFIDF extends Searcher {

	HashSet<String> allTerms; 					// All terms from all of documents in the corpus
	List<String> termIndex;						// term index
	LinkedHashMap<String, Double> idfMap;		// map term and idf 
	HashMap<Integer, double[]> docVectorMap = new HashMap<>();	// map docID and document vector
	HashMap<Integer, Double> docLengthMap = new HashMap<>(); 	// map docID and length of the docID 
	double[] queryVector;
	int numberOfDocs = documents.size();		// number of documents in the corpus
	public SweetieTFIDF(String docFilename) {
		super(docFilename);
		/************* YOUR CODE HERE ******************/
		//Calculate all terms from from all of documents in the corpus
				allTerms = new HashSet<>();
				idfMap = new LinkedHashMap<>();
				createIdfMap();
				termIndex = new ArrayList<String>(idfMap.keySet());
				createDocumentVector();
		/***********************************************/
	}

	private void createDocumentVector() {
		// TODO Auto-generated method stub
		double[] vector;
		for (Document doc : documents) {
			vector = new double[allTerms.size()];
			double sum = 0.0;
			
			// Compute tf-idf of each term in each document and put it in the document vector map
			for (String term : doc.getTokens()) {
				double tf_idf = computeTf_Idf(doc.getTokens(), term) ;
				vector[termIndex.indexOf(term)] = tf_idf;
			}
			docVectorMap.put(doc.getId(), vector);
			
			// Compute the length of each document vector
			for (int i = 0; i < vector.length; i++) 
				sum += Math.pow(vector[i], 2);
			docLengthMap.put(doc.getId(), Math.sqrt(sum));
		}
	}

	public double computeTf(List<String> listOfTerms, String term) {
		double tfWeight = 0.0, rawTf = 0.0;
		if (listOfTerms.contains(term)) {
			// Find term frequency
			for (int i = 0; i < listOfTerms.size(); i++) {
				if (listOfTerms.get(i).equals(term))
					rawTf++;
			}
			tfWeight = 1 + Math.log10(rawTf);
			return tfWeight;
		}
		return 0;
	}
	private double computeTf_Idf(List<String> listOfTerms, String term) {
		// TODO Auto-generated method stub
		double tf = computeTf(listOfTerms, term); 
		double idf = idfMap.get(term);
		return tf * idf;
	}

	private void createIdfMap() {
		// TODO Auto-generated method stub
		for (Document doc : documents) {
			allTerms.addAll(doc.getTokens());
			Set<String> distinctTerm = new HashSet<>(doc.getTokens());	
			for (String term: distinctTerm) {
				double rawIdf = 1.0;
				
				if(idfMap.containsKey(term)) {
					// idfMap already contains this term 
					rawIdf = idfMap.get(term) + 1.0; 
					idfMap.replace(term, rawIdf);
				} else {
					// new term in idfMap
					idfMap.put(term, 1.0);
				}
			}
		}
		
		// Normalize idf got from previous loop and replace it in the idf map
		for (String term :idfMap.keySet()) {
			double idf = Math.log10(1 + (numberOfDocs / idfMap.get(term) ));
			idfMap.replace(term, idf);
		}
	}
	public double cosinesim(double[] queryVector, double[] docVector, double queryVectorLength, double docLength) {
		double score = 0.0;
		
		// Compute score according to cosinesim formula
		for (int i = 0; i < queryVector.length; i++) {
			score += queryVector[i] * docVector[i];
		}
		score /= docLength * queryVectorLength;
		
		return score;
	}
	@Override
	public List<SearchResult> search(String queryString, int k) {
		/************* YOUR CODE HERE ******************/
		List<SearchResult> results = new ArrayList<>(); 	
		List<String> query_tokens = tokenize(queryString);	

		// Create query vector
		queryVector = new double[allTerms.size()];
		// Compute tf-idf of each term in query and put it in query vector
		for (String token: query_tokens) {
			if(termIndex.indexOf(token) != -1){
				queryVector[termIndex.indexOf(token)] = computeTf_Idf(query_tokens, token);
			}
		}
		//Compute length of query vector
		double sum = 0.0;
		for (int i = 0; i < queryVector.length; i++) 
			sum += Math.pow(queryVector[i], 2);
		double queryVectorLength = Math.sqrt(sum);

		//Compute Score of each document and put it in the search result
		for (Document doc : documents) {
			double[] docVector = docVectorMap.get(doc.getId());
			double docLength = docLengthMap.get(doc.getId());
			double score = cosinesim(queryVector, docVector, queryVectorLength, docLength);
			SearchResult result = new SearchResult(doc, score);
			results.add(result);
		}
		
		//Sort score in the search result from highest to lowest
		Collections.sort(results);
		List<SearchResult> topK_Result = results.subList(0, k);
		
		/***********************************************/
		return topK_Result;
		/***********************************************/
	}

}
