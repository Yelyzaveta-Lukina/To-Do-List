// Defines the package for this Kotlin file.
package edu.lukina.to_dolist

// Imports the Bundle class from the Android framework.
// Bundles are generally used for passing data between various Android activities.
// It can also be used to save an activity's state.
import android.os.Bundle
// Imports the Button class from the Android widget package.
// A Button is a UI element that the user can tap or click to perform an action.
import android.widget.Button
// Imports the EditText class from the Android widget package.
// EditText is a UI element for entering and modifying text.
import android.widget.EditText
// Imports the TextView class from the Android widget package.
// TextView is a UI element that displays text to the user.
import android.widget.TextView
// Imports the AppCompatActivity class from the AndroidX library.
// AppCompatActivity is a base class for activities that want to use the support library action bar features.
import androidx.appcompat.app.AppCompatActivity

// Declares the MainActivity class, which inherits from AppCompatActivity.
// This class is the main screen of the to-do list application.
class MainActivity : AppCompatActivity() {
    // Declares a private property 'toDoList' and initializes it with a new instance of the ToDoList class.
    // 'this' (referring to the MainActivity instance) is passed as the Context to the ToDoList constructor.
    private var toDoList = ToDoList(this)
    // Declares a private lateinit property 'itemEditText' of type EditText.
    // 'lateinit' indicates that this property will be initialized before its first use (typically in onCreate).
    private lateinit var itemEditText: EditText
    // Declares a private lateinit property 'listTextView' of type TextView.
    // 'lateinit' indicates that this property will be initialized before its first use.
    private lateinit var listTextView: TextView

    // Overrides the onCreate method, which is called when the activity is first created.
    // 'savedInstanceState' is a Bundle containing the activity's previously saved state, if any.
    override fun onCreate(savedInstanceState: Bundle?) {
        // Calls the superclass's (AppCompatActivity) onCreate method to perform default initialization.
        super.onCreate(savedInstanceState)
        // Sets the user interface layout for this activity from the XML file R.layout.activity_main.
        setContentView(R.layout.activity_main)

        // Finds the EditText widget with the ID 'todo_item' in the layout and assigns it to 'itemEditText'.
        itemEditText = findViewById(R.id.todo_item)
        // Finds the TextView widget with the ID 'item_list' in the layout and assigns it to 'listTextView'.
        listTextView = findViewById(R.id.item_list)
        // Finds the Button widget with the ID 'add_button' in the layout.
        // Sets an OnClickListener for this button, which calls the 'addButtonClick()' method when the button is clicked.
        findViewById<Button>(R.id.add_button).setOnClickListener { addButtonClick() }
        // Finds the Button widget with the ID 'clear_button' in the layout.
        // Sets an OnClickListener for this button, which calls the 'clearButtonClick()' method when the button is clicked.
        findViewById<Button>(R.id.clear_button).setOnClickListener { clearButtonClick() }
    }

    // Overrides the onResume method, which is called when the activity will start interacting with the user.
    override fun onResume() {
        // Calls the superclass's onResume method.
        super.onResume()

        // Calls the 'readFromFile' method of the 'toDoList' object to attempt to load a previously saved list.
        toDoList.readFromFile()
        // Calls the 'displayList' method to update the TextView with the items from the to-do list.
        displayList()
    }

    // Overrides the onPause method, which is called when the system is about to pause the activity.
    override fun onPause() {
        // Calls the superclass's onPause method.
        super.onPause()

        // Calls the 'saveToFile' method of the 'toDoList' object to save the current list for later.
        toDoList.saveToFile()
    }

    // Defines a private function 'addButtonClick' that is called when the "Add" button is clicked.
    private fun addButtonClick() {

        // Gets the text from 'itemEditText', converts it to a string, and removes any leading or trailing whitespace.
        val item = itemEditText.text.toString().trim()

        // Clears the text in 'itemEditText' so it's ready for another item.
        itemEditText.setText("")

        // Checks if the 'item' string is not empty after trimming.
        if (item.isNotEmpty()) {
            // If the item is not empty, adds it to the 'toDoList'.
            toDoList.addItem(item)
            // Calls 'displayList' to refresh the displayed list in the TextView.
            displayList()
        }
    }

    // Defines a private function 'displayList' that updates the 'listTextView' with the current to-do items.
    private fun displayList() {

        // Creates a StringBuffer to efficiently build the string that will be displayed.
        val itemText = StringBuffer()
        // Retrieves the list of to-do items from the 'toDoList' object.
        val items = toDoList.getItems()
        // Gets the system's line separator string (e.g., "\n" on Linux/macOS, "\r\n" on Windows).
        val lineSeparator = System.getProperty("line.separator")

        // Iterates through the 'items' list using their indices.
        for (i in items.indices) {
            // Appends the item number (index + 1), a period, a space, the item itself, and a line separator to 'itemText'.
            itemText.append(i + 1).append(". ").append(items[i]).append(lineSeparator)
        }

        // Sets the text of 'listTextView' to the string built in 'itemText'.
        listTextView.text = itemText.toString()
    }

    // Defines a private function 'clearButtonClick' that is called when the "Clear" button is clicked.
    private fun clearButtonClick() {
        // Calls the 'clear' method of the 'toDoList' object to remove all items from the list.
        toDoList.clear()
        // Calls 'displayList' to refresh the displayed list in the TextView, which should now be empty.
        displayList()
    }
}
