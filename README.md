# RandomApp

This is a example project of a code test I did for a Spanish company.

## Task

You work for a random company (Randomco). As a good random company they want to
show random information about random users. Your task for this code test is design an

Android/iOS application with these requirements:

* Download a list of random users from http://randomuser.me/ API.
* Don’t show duplicated users. If Random User API returns the same user more than once
you have to store just one user inside your system.
* Show a list of random users with this information sorted by name:
      ○ User name and surname.
      ○ User email.
      ○ User picture.
      ○ User phone.
* Add one button to retrieve more users and add to your current users list.
* Add one button to each cell to delete users. If you press that button your user will not be
shown anymore in your user list.
* Add one button to each cell to mark users as favorite.
* If you press the user picture you have to show another view with the user detailed
information:
      ** User gender.
      ** User name and surname.
      ** User location: street, city and state.
      ** Registered date.
      ** User email.
      ** User picture.
* Test your code, think in the most important parts of your application and write
some unit tests.
* Try to resolve this code test like a real life project. Think in the architecture and design of your
model and try to implement it as modular as possible.

## Extra points
* Your user interface should contains a text box to search users by name, surname or
email. Once the user stop typing, your list will be updated with users that matches with
the search term.
* Support tablets with your application UI implemented to show two lists: one with all users
and other with just favorite users.
* Create another view, similar to the first one, to show users marked as favorite.
* Show a list of users next to you. Show them in a list if they are at less than 1KM.
Random User API doesn’t provide you information users location try to insert random
latitudes and longitudes during the json parsing process.
* Add some checkboxes to your UI to sort users by name and gender.
