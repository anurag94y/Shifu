Hello Sir,

This is our project "Scrooge".

Project is in jsoup_check folder and it can be imported in ANDROID STUDIO only for run and checking purposes.

1. All the java codes are in jsoup_check\app\src\main\java\com\example\flamealchemist\jsoup_check

	Here you will find 8 folders and here are the specifications
	1. Activity - Contain all the activity classes which basically interact with user and handles input output.
		      Here are four classes namely :- MainActivity, HistoryActivity, FavouriteActivity, ResultActivity

	2. Adapter - Contain the ArrayAdapter class which works as an iterface to put results in ListView Classes.
	
	3. Background - Contain the NotificationGen class which works in background and after invocation by AlarmManager 
			in Android OS it will generate notification if the price of any entry in Favourites goes down 
			It will do this after every 6 hours.
	
	4. Crawler_Package - Contains 3 classes namely
			     - Crawler Handles separate thread for each inner class in Company class.
			     - Company class has inner class of every company included and do scrapping those sites.
			     - New_Price scrap sites for favourite items.
	
	5. Helper - DatabaseHelper class is defined here it will contain all the functions which are required for 
	   	    Database related tasks.

	6. Model - Contains definition class for model of every table with column name and get set function on them.
	
	7. Popup - Contains Popup classes for every popup used in application with positive and negative function 
		   definition. This has three classes namely :- DisplayPopup ,DisplayPopupFavourite, DisplayPopupHistory

	8. Tools - Contains all other classes needed for functioning.

2. Resources like images, color template, shape definition etc. are defined in the res folder with path jsoup_check\app\src\main\res

3. Report and PPT are also included.

4. Installable apk files are here \jsoup_check\app\build\outputs\apk.

Have a good time !!!

 
	