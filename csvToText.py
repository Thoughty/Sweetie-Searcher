import csv

#Open file with "w"(write)
txt_file = open("./web-scraping/text.txt", "w")

#Open CSV file to read CSV
with open('./web-scraping/output.csv') as csv_file:
	#Read CSV
	readCsv = csv.reader(csv_file)

	for row in readCsv:
		#Get Values and manupilate in the txt_file.write
		docID = row[0]
		title = row[1].upper()
		ingredient = row[2].upper()
		preparation = row[3].upper()
				
		txt_file.write(str(docID) + "	" + title + "	" + 
		ingredient + "	" + preparation + "\n")

txt_file.close()