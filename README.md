# User details Validation - Android Library
It's is an android application written in 'KOTLIN', it  provides a UI component (User details form) that is validated and the information is sent to the server,for posting the user info  RequestBin endpoint is created at:  
https://still-brook-84569.herokuapp.com/

it is designed using android's architectural components such as :

  - ViewModel
  - Live Data
  - Data Repository


# Features

  - orientation independent
  - Easily extendible
  - Clean UI and code
  - Ready Unit Tests for all the data validations and ViewModel


### Tech

This app uses a number of open source projects Libraries and frameworks:

* [Kotlin] - written in kotlin
* [SOLID] - Use of Solid Design principles
* [Dagger2] - Dependency injection using Dagger2
* [Coroutines] - Async Calls using coroutines
* [Repository pattern] - Use of repository pattern for data storage and retrieval
* [View Model]


### Installation and Usage Instructions

Step #1 : Adding this library to your android project
-----------------------------------------------------
Copy the latest release of this project from github using the link :
https://github.com/smtrz/UILibrary/releases

OR

Clone this project and assemble the release of it using gradle app tasks, and copy the generated aar file into your project's lib folder.

Step #2 : Adding library into your project's dependencies
---------------------------------------------------------

- Add the following line into your project level gradle under repository :
 ```
 allprojects {
	repositories {
		...
		flatDir {
            dirs 'libs'
        }
	}
	}
 
 ```
 after that inside your app level gradle add the aar file with following line :
 ```
implementation files('libs/app-release_v1.0.aar')
```
Step#3: Adding supporting third party dependencies
--------------------------------------------------
As mentioned above the library uses android's architectural components and some other third party libraries, these libraries are very common and normally are added into any project but since i am assuming we are starting from scratch,
so therefore add the following dependencies:

```
    def support_library_version = '28.0.0'
    def lifecycle_version = "2.2.0"
    def coroutine_version = "1.4.2"
    def arch_version = "2.1.0"
    implementation 'androidx.core:core-ktx:1.3.2'
    implementation 'com.google.android.material:material:1.2.1'
    implementation "com.google.dagger:dagger:2.28"
    kapt "com.google.dagger:dagger-compiler:2.28"
    implementation 'com.squareup.okhttp3:logging-interceptor:4.3.1'
    implementation 'com.google.code.gson:gson:2.8.6'
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
    // ViewModel
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version"
    implementation "androidx.lifecycle:lifecycle-extensions:$lifecycle_version"
    // LiveData
    implementation "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version"
    // Coroutine
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutine_version"
```

Step#4: Adding Kotlin-kapt plugin
---------------------------------
Add the kapt plugin at the top of your app level gradle file that is needed for dependency injecting using dagger2
```
apply plugin: 'kotlin-kapt'
```
Step5: Launching Library's UI component
---------------------------------------
first create your request bin id from the following url :
https://still-brook-84569.herokuapp.com/
and after creating the id pass it as a bundle parameter with "id" key to launch library's UI component .
```
   val intent = Intent(this, com.tahir.validateuser_lib.activities.UserValidationActivity::class.java)
   intent.putExtra("id", apiId)
   startActivity(intent)
   ```
To get the the response from the library :
------------------------------------------
just implement the interface  Validationcallbacks with the methods of success and failure.

and set its listener , simply by adding the following line into your activity.
```
Validations.setListener(this)
   
```
Last words:
-----------
Sample project is also added that use this library and it demonstrates the usage clearly.


###  Author
Designed and developed by :<br/>
Tahir Raza (http://highbryds.com/tahir-raza)

