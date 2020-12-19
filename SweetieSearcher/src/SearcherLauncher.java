import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
public class SearcherLauncher {

	public static final String ScrappingData = "./data";
	public static final int k = 10;
	

	public static void StartTFIDF_Search(String data)
	{
		System.out.println("@@@ Searching for [ "+data + " ] using TF-IDF seacher @@@");
		//String documentFilename = ScrappingData +"/DessertRecipe.txt";
		String documentFilename2 = ScrappingData +"/DessertWithIngre.txt";
		long startTime = System.currentTimeMillis();

		//Searcher Start = new SweetieTFIDF(documentFilename);
		Searcher Start = new SweetieTFIDF(documentFilename2);
		List<SearchResult> results = Start.search(data, k);
		System.out.println("@@@ Results: "+(data.length() > 50? data.substring(0, 50)+"...":data));
		Searcher.displaySearchResults(results);
		System.out.println();
		
		long endTime = System.currentTimeMillis();
		System.out.println("@@@ Total time used: "+(endTime-startTime)+" milliseconds. @@@");
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner SearchQuery = new Scanner(System.in);
		System.out.println("@@@ Sweetie Searcher @@@");
		System.out.print("Enter keyword to search : ");
		String Query = SearchQuery.nextLine();
		
		StartTFIDF_Search(Query);
	}

}
