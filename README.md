# Alberts-android-study
Going over all of the material for android development
Project list:

1. Shared Preferences for DAO project

The goal of shared preferences is to not lose data while exiting an app  
in this case we will use shared prefs on our database   
Two Activities  
Main Activity and Edit Book Activity  

implementation "com.jakewharton.timber:timber:4.7.1"  
Book model class  
BookData shared book test data  
Application to plant the files in string format  

2. Data-Persistence-with-Room-and-MVVM

livedataviewmodel Data persistence with Room Database Using View models and MVVM architecture

3. SQL Lite Data persistance

Data persistence Alternative to Room Database

4. Storage for Books

Create a Json object from prefs in Book Model Class  
Reference the preference to the Repository  
Create book repo interface  
Create Book Repo = where you specify how you wanna store the files and where  

5. Favorite Movies Sprint MVVM

6. Project Lifecycles

Timber helps with writing log statements   
-Timber generates tags  
-Timber avoids logs in release  
-Easy integration with crash reporting  

To set up Timber:
-Add Timber to build.gradle'  
-Make Application class  
-Add it to manifest  
-Initialize Timber in Application class 

After that Timber works just like Log.i 

onCreate gets called only once  
Fragments are better solution to working with different screen sizes   
When starting something ion callback, make sure to stop it correspondingly  

onSaveInstanceState handles storing information into a bundle while rotatiing the screen or going away from it


7. Project Notifications 

In the Notification class:

Create notification channel to put it in the system and it is cast specific  
Make sure sure the version of android is matching

Use Builder pattern for older versions of android or  
create a channel for newer versions

Then place the notification you just built where you need it in the app 

You can set sounds and vibrations to DEFAULT and HIGH_PRIORITY notifications 

8. Project AsyncGithubQuery

Async Task is like simplified Facade of Custom Threads.   
Async Tasks are used to run code in background so there isnt too much running in main thread  

9. Project Custom Handler Threads  
Threads work just like Async task and is keptAlive with a Looper  

Create a Handler and attach the looper.  
Once the handler is returned in form of an object overrride fun handleMessage  
Handle the message in the object (code logic goes here)  
Initialize the message and process it.  
Then finally send it to UI  

Once Handler created it has to be set up  
Manage the Runnable by declaring orderHandlerThread  
And then add it to run() overriden method  

Then finally update the UI in main activity  

