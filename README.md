[![](https://www.jitpack.io/v/ArchitGarg1/changa-lite.svg)](https://www.jitpack.io/#ArchitGarg1/changa-lite)

Use this library for integration of Changa lite in your android Project.

***Step 1. Add the JitPack repository to your build file***
Add this to your root build.gradle at the end of repositories:
```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
***Step 2. Add the dependency***
```
	dependencies {
	        implementation 'com.github.ArchitGarg1:android-library:1.0'
	}
```
***Step 3. Update your layout***
> Just add this tag in your layout
```
<com.bitcs.desitalent.changalite.ChangaLiteView
        android:id="@+id/changa_lite_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>
```
***Step 4. Update AppId***
> Set the AppId in the java file
```
ChangaLiteView myChangaLiteView = findViewById(R.id.changa_lite_view);
myChangaLiteView.setAppId("Your-app-id");
```
***Step 5. Add permission to use the internet***
> Add permission in AndroidManifest.xml inside manifest tag
```
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.myapplication">

    <!--  copy this line in your code  -->
    <uses-permission android:name="android.permission.INTERNET" />

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name" ...
```

Set Background Colour to match your app design like this:
```myChangaLiteView.setBackgroundColor(Color.BLACK);```