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

10. Project JSON Parser  
JSON  
Java Script Object Notation  
primary method for data exchange on web  
it is human readable and builds arrays  

We parse JSON into GSO before we can inser it into the field as a string,   
and we can parse back to JSON to ommunicate back with the web  
The app first uses the JSON to parse into GSON to store later into local phone storage   

11. Retrofit GitHub Query  
Update from Async Github Query  
The difference is that Async Query App uses basic OkHttp calls to get the data,   
while retrofit does the same thing in much nicer way.  

Retrofit is the REST client to upload JSON via REST Based Web Services     
Retrofit uses Http library for Http requests  
OkHttp also contains logging interceptor  
While OkHttp makes low level calls and is able to cache them, retrofit is abl;e to manimulate URL and is coupled with OkHttp to make the network calls.  

12. Broadcast Receiver TaskMaker

Broadcast Receivers are used to monitor state of the phone or an event.  
in this app Broadcast Receiver is used to build a clock and listen for time change and update the UI with each change.   
Its best when you register the receiver, to unregister it onPause() or onStop()  
so the receiver wont continue running in the background  
Services are also good for running in the background. Unlike Broadcast Receivers   
Services are not visible on UI  
also app contains the stops on how to set up on click listeners using Lambda  

13. Networking Sprint Challenge Pokemon API  
Redone from scratch. Using Okhttp3, Retrofit2, Recyclerview, CardView, Picasso to loasd the sprites(images)  

14. Project NoteTaker Testing  

In the nutshell :  
Robolectric allows testing on local machine without emulator or physical device  
Robolectric is good for integration testing  
JUnit4 testing is used for unit testing, works with robolectric   
Esspresso is used for UI testing.  

15. Project Catch Pokemon Game

Maps Activity    
implements onCallbackReady on maps   
initialize maps and request permissions at runtime  

MyLocation  
Is similar way to maps activity but no map.  
Implements Google Play Services listener to listen to network and listen to multiple APIs  

Popup navigation Drawer  
Change layout to navigation layout  
need library for material design to get the popup menu  
options menu to call the popup menu  
custom toolbar  

ExoPlayer is used to play the web video at app launch  
Media Player is used for app sound effects  
Both player are essentially the same only Exo player is more customizable  

