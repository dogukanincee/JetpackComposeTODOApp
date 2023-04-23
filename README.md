# JetpackComposeTODOApp
 Building a Todo App with Jetpack Compose: A Step-by-Step Guide

In this tutorial, we'll build a simple todo app using Jetpack Compose. We'll cover the basics of building a UI with Compose, working with Coroutines and Room for database management, and using ViewModel and Navigation for app architecture.

Prerequisites

To follow along with this tutorial, you'll need:

A basic understanding of Android development
Android Studio 4.2 or later installed on your computer
A device or emulator running Android 5.0 or later
Getting Started

Let's start by creating a new Android Studio project with an empty activity. We'll use this activity as our main entry point for the app.

Setting up the Project
To create a new Android Studio project, follow these steps:

Open Android Studio and click "Start a new Android Studio project" on the welcome screen.
Choose "Empty Activity" as the project template and click "Next".
Enter a name for your project and choose a package name. You can leave the other options as their default values.
Choose the minimum SDK version for your project. We'll use Android 5.0 (API level 21) in this tutorial.
Click "Finish" to create the project.
Once the project is created, you should see the main activity file (MainActivity.kt) and layout file (activity_main.xml) in the project explorer.

Adding Jetpack Compose
Next, we need to add the Jetpack Compose dependencies to our project. To do this, we'll add the following lines to the build.gradle file for our app module.
The buildFeatures block enables Jetpack Compose, and the composeOptions block sets the version of the Compose compiler to use. The dependencies block adds the necessary Compose dependencies to the project.

Creating the UI
With Jetpack Compose added to the project, we can start building our UI. Let's create a new Composable function for the main screen of the app. We'll call this function TaskListScreen.
In the TaskListScreen function, we first apply the Material theme using the MaterialTheme function. This sets the default styles and colors for our app. We then create a Scaffold composable, which provides a basic layout for our screen.

The Scaffold composable has a topBar parameter, which we set to a TopAppBar composable. The TopAppBar provides a navigation bar at the top of the screen, which we set to display the app title ("Todo App").

Column, we add a Text composable with the text "Hello, world!" to test that the screen is rendering correctly.

If we run the app now, we should see the main screen with the navigation bar at the top and the "Hello, world!" text in the middle of the screen.

Adding a List of Tasks
Now that we have the basic structure of our app set up, let's add a list of tasks to the main screen.

First, we'll create a data class to represent a task. This class will have a title, description, and completed flag.

Next, we'll create a list of sample tasks to display in the UI. For now, we'll just hard-code these tasks in our TaskListScreen function.

Finally, we'll replace the Text composable in our TaskListScreen function with a LazyColumn composable to display the list of tasks.

The LazyColumn composable is similar to a RecyclerView in traditional Android development. It allows us to display a large list of items efficiently, by only rendering the items that are currently visible on the screen.

The items parameter of LazyColumn takes a list of items to display, and a lambda function that defines how to display each item. In this case, we're displaying the title of each task as a Text composable.

If we run the app now, we should see a list of tasks displayed on the main screen.

Adding Navigation
Now that we have a list of tasks displayed on the main screen, let's add navigation to the app. We'll create a new screen to display the details of a selected task, and allow the user to navigate to this screen by clicking on a task in the list.

First, we'll create a new Composable function for the task detail screen. We'll call this function TaskDetailScreen.

The TaskDetailScreen function is similar to the TaskListScreen function, but it takes a Task parameter and displays the title and description of the task.

Next, we'll update the TaskItem composable that displays each task in the list to make it clickable. When a task is clicked, we'll navigate to the TaskDetailScreen to display the details of the selected task.

Next, we need to update the TaskItem composable to make each task clickable. When a task is clicked, we'll navigate to the TaskDetailScreen to display the details of the selected task.

We've added a Modifier.clickable modifier to the Card composable that wraps the Column composable that displays the task details. We pass a lambda function to the onClick parameter of clickable that calls the onClick function we defined in the TaskListScreen function.

Now, when a task is clicked, we'll call the onClick function and pass it the selected task. We'll use this function to navigate to the TaskDetailScreen and pass it the selected task as a parameter.

To enable navigation between screens, we'll use the NavHost composable from the navigation-compose library. We'll add a NavHost composable to our TaskListScreen function and define two navigation routes: one for the main screen and one for the task detail screen.

We've defined two routes in our NavHost: "taskList" and "taskDetail/{taskId}". The first route displays the main screen with the list of tasks, while the second route displays the task detail screen with the details of a selected task.

We've also passed a NavController object to the TaskListScreenContent function and used it to navigate to the TaskDetailScreen when a task is clicked. We've passed the selected task as a parameter to the TaskDetailScreen using a route argument.

Finally, we've added a floating action button to the main screen that navigates to a new screen for adding a new task.

Finally, let's create the TaskDetailScreen composable. This screen will display the details of a selected task, including its title, description, and completion status. It will also provide an option to edit or delete the task.

To create the TaskDetailScreen, we'll first create a new composable function called TaskDetailScreen. This function will take a TaskViewModel and a Task as parameters. We'll use these parameters to display the details of the selected task and to update or delete the task if the user chooses to do so.

The TaskDetailScreen function takes two parameters: a TaskViewModel and a Task. The TaskViewModel is used to update and delete the task, while the Task is used to display the task details.

We'll use mutable state variables to keep track of the title, description, and completion status of the task. We'll initialize these variables with the values of the selected task, or with empty strings and false for the completion status if the task is null.

The composable function starts with a Column composable that contains an OutlinedTextField for the title, an OutlinedTextField for the description, and a Checkbox for the completion status. It also contains a Row composable with a Delete TextButton and a Save Button. The Row composable is aligned to the end of the parent column using horizontalArrangement = Arrangement.End.

When the user clicks the Delete TextButton, we'll call the deleteTask method of the TaskViewModel and pass in the selected task as a parameter. When the user clicks the Save Button, we'll call the updateTask method of the TaskViewModel and pass in a copy of the selected task with the updated title, description, and completion status.

Now that we have completed the navigation and detail screen, let's move on to the final part of our application, the ability to create and edit tasks.

TaskFormScreen
The TaskFormScreen is the screen where the user can create or edit a task. We'll create a new composable function named TaskFormScreen that will be responsible for rendering the form UI. It will have the following parameters:

task: Task?: The task that is being edited, or null if a new task is being created.
onSave: () -> Unit: A function to be called when the user saves the task.
onCancel: () -> Unit: A function to be called when the user cancels the form.
We'll use the remember function to create local state for the form fields title and description. If the task parameter is not null, we'll use its title and description values as the initial values for the form fields. Otherwise, we'll use empty strings.




