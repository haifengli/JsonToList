#JSONToList Coding Challenge Instructions

##General Information
This is considered an "open book" challenge. We expect, and encourage, you to look things up the way you normally would during the course of your work -- including, but not limited to: Android Documentation, StackOverflow, Google, blog posts, and old personal projects.

##Goals
* The primary goal of this challenge is to fetch an array of products. The basic hookup is already done with Retrofit but will require modification.
* Once the list of products has been retrieved, these should be displayed in some sort of list element of your choosing.
* The list will be built with whatever techniques you prefer -- programmatic UI creation, Jetpack Compose, Epoxy, RecyclerView, ListView, etc.
* Please handle thrown errors and other unexpected code paths
* Use of third-party libraries is highly advisable.

##Detailed Instructions
* In the `Product.kt` class, ensure you have the fields required for parsing the data from the JSON file.
* The current networking call is located within the `BaseAppCompatActivity.kt` file. Please modify this at your discretion.
* Create a UI that will display in the Android emulator and display a list of products. For each object, display the `name` and the `tagline` Strings. Ensure that no strings are truncated.
* Map the `rating` field from the response, rounding it to the nearest 0.5 (ex, 1.5, 2, 3.5, 4) and display this rating in the UI, always showing one decimal place.
* Write unit tests to confirm that the `rating` rounding code works as intended.

##Submission Instructions
To submit this project, please see the emails and documentation that were given to you by your recruiter.

