# Sweetie-Searcher
Simulated Search Engine for Dessert Recipe from Cookbook.com

# Introduction
  Sweetie is a search engine for dessert recipes to serve the purpose of searching
a certain dessert recipe among a thousand recipes on the internet. As a matter of fact,
there are ample dessert recipes on the internet, so it’s quite difficult to search for the
needed recipes or related recipes. Therefore, we would like to implement this search
engine so that the users will find the recipe that is relevant to what they need more
easily and quickly. The target customers of Sweetie can be classified into two
groups. People who begin to cook dessert on their own and pastry chefs who desire
to seek new recipes for their bakery or restaurant.


# Problem
  Due to the fact that there are plenty of dessert recipes in books as well as the
internet, finding one desired recipe or other related recipes would be arduous and
time-consuming. This problem also involves and affects our target customers.
As for beginners, there is a possibility that they have no idea what their wanted
recipe is called; thus, it’s difficult to randomly search for unknown recipes on the
internet. To resolve this problem, Sweetie allows users to search with the keywords
whether it be ingredients or type of dessert, such as cookie, cake, pastry, etc. Despite
not knowing the recipe name, users remain searching for their desired recipes.
Pastry chefs who are already familiar with well-known recipes, on the other
hand, may encounter difficulties in finding other recipes that are related to their
recipes when they attempt to find a new taste for their dessert or come up with new
menus. With Sweetie search engine, it’s easier for the users to search for relevant
dessert recipes as they would be able to search with the recipe's name directly.
Afterward, Sweetie would return the relevant recipes back to the user so that they
can adjust to their recipes. Furthermore, they will also have an opportunity to explore
new recipes to be new menus in their bakery or restaurant.


# Literature Review or Existing Relevant System
  Our system is relevant to google search engines because it takes the input of
word query or a long word query and searches for the similar document that has the
same data as the input. This algorithm is widely used due to the accuracy of the
search results and is easy to implement in various data.
Another existing relevant system is cookbook.com, the web that we used to
scrape the data to make our database. On this website, the search engine is also
similar to our search engine in terms of query received from the user, but our search
engine only focuses on dessert and we return only 10 relevant documents. On the
contrary, cookbook.com returns all documents by putting relevant documents on the
first page and the others will be sorted alphabetically. 


# Methodology
  We used the web scraping technique to extract the data from the website in to CSV file and use data as a database of our system. After we had the database of the system, we categorized and formatted data for our search engine . Next, we will receive the query or input , which can be either dessert's name or an ingredient, from the user to search in the engine that we built. Our search engine uses the TF-IDF method to find the highest relevant score between the input and the database from our system. After all of the search, the top 10 match documents will be displayed to the user as the result of our search engine.
  
  
  
# Implementation
  
  To implement Sweetie search engine, we commence with gathering data from
“www.cookbook.com”, which is our data source. After that we preprocess data to
extract only significant data that will be used in the system. The processes of
preprocessing data are as follows:
 
 - Go to the website and screen the data
 
   ![Cookbook's Website](/Sweetie-Searcher/Cookbook.PNG)
   
 - Perform web scraping to extract data from the website by using JavaScript
   and Cheerio package. We separate the data into four fields composed of
   document ID, Recipe’s title, Ingredient, and Preparation. Then, export the data
   into a CSV file so that we can view the data more easily.
   
   ![Scraping Technique](/Sweetie-Searcher/WebScraping.PNG)
   ![Scraping CSV](/Sweetie-Searcher/ScrapingCSV.PNG)
   
 - Convert data in CSV file into text file to be able to be used in the search
   system. This is done by using Python. In the text file, there will be one line
   per one document, each line has the following format:
   
    **docID TAB title TAB ingredients TAB preparation**
    
   Where **docID** is the document ID, **TAB** is a tab character, **title** is the raw text of the recipe's title, **ingredient** is the      
   raw text of the ingredient of the recipe, and **preparation** is the raw text of the preparation for the recipe.
  
   ![CSVtoText](/Sweetie-Searcher/CSVtoText.PNG)
   ![TextFile](/Sweetie-Searcher/TextFile.PNG)
 
 - Once the data is preprocessed and constructs the inverted index, we let the
   users search our system. We receive a query from the user and use it to
   compute the score from the TFIDF method to find the highest score which
   means it is the most relevant recipe related to the query that the user wants to
   search. After the score is computed, it ranks the top 10 most relevant sorted
   by the score from TFIDF method and returns those documents to the user.
 
