package edu.lukina.to_dolist

// Imports the Context class from the Android framework.
// Context provides access to application-specific resources and classes, as well as up-calls for application-level operations.
import android.content.Context
// Imports Java I/O classes for file operations.
// FileInputStream is for reading raw bytes from a file.
import java.io.FileInputStream
// FileNotFoundException is an exception thrown when an attempt to open the file denoted by a specified pathname has failed.
import java.io.FileNotFoundException
// FileOutputStream is for writing raw bytes to a file.
import java.io.FileOutputStream
// PrintWriter is for writing formatted representations of objects to a text-output stream.
import java.io.PrintWriter
// Imports the Collections utility class from Java.
import java.util.Collections
// Note: 'java.util.*' was in the original code, which imports all classes from java.util.
// If only Collections is needed, 'import java.util.Collections' would be more specific.

// Declares a class named ToDoList.
// It has a primary constructor that takes a 'context' of type Context as a property.
class ToDoList(var context: Context) {
    // Defines a companion object. Companion objects allow you to define members that can be accessed using the class name, similar to static members in Java.
    companion object {
        // Declares a constant String named FILENAME with the value "todolist.txt".
        // This will be used as the name of the file to store the to-do list items.
        const val FILENAME = "todolist.txt"
    }

    // Declares a private mutable property named 'taskList' of type MutableList<String>.
    // It is initialized as an empty mutable list. This list will store the to-do items as strings.
    private var taskList: MutableList<String> = mutableListOf()

    // Defines a public function named 'addItem' that takes a 'item' of type String as a parameter.
    // This function adds a new to-do item to the 'taskList'.
    fun addItem(item: String) {
        // Adds the provided 'item' string to the 'taskList'.
        taskList.add(item)
    }

    // Defines a public function named 'getItems' that returns an immutable List<String>.
    // This function provides read-only access to the current to-do items.
    fun getItems(): List<String> {
        // Returns an unmodifiable view of the 'taskList'.
        // This prevents external code from directly modifying the internal list through the returned reference.
        return Collections.unmodifiableList(taskList)
    }

    // Defines a public function named 'clear'.
    // This function removes all items from the 'taskList'.
    fun clear() {
        // Clears all elements from the 'taskList'.
        taskList.clear()
    }

    // Defines a public function named 'saveToFile'.
    // This function writes the current 'taskList' to a file in the app's internal storage.
    fun saveToFile() {
        // Opens a private file associated with this application's context for writing.
        // FILENAME (todolist.txt) is the name of the file.
        // Context.MODE_PRIVATE means the file can only be accessed by the calling application.
        // This returns a FileOutputStream.
        val outputStream = context.openFileOutput(FILENAME, Context.MODE_PRIVATE)
        // Calls the private helper function 'writeListToStream' to perform the actual writing.
        writeListToStream(outputStream)
    }

    // Defines a public function named 'readFromFile'.
    // This function reads to-do items from a file in the app's internal storage and populates the 'taskList'.
    fun readFromFile() {
        // Starts a try-catch block to handle potential exceptions, specifically FileNotFoundException.
        try {
            // Opens the private file named FILENAME (todolist.txt) associated with this application's context for reading.
            // This returns a FileInputStream.
            val inputStream: FileInputStream = context.openFileInput(FILENAME)
            // Creates a BufferedReader that wraps the 'inputStream' for efficient reading of text lines.
            val reader = inputStream.bufferedReader()
            // Clears any existing items from the 'taskList' before reading new items from the file.
            taskList.clear()
            // Iterates over each line read by the 'reader' from the file.
            // 'it' refers to the current line (task) read.
            reader.forEachLine { taskList.add(it) }
            // Catches a FileNotFoundException if the file does not exist.
        } catch (_: FileNotFoundException) {
            // If the file is not found (e.g., first time running the app), this block is executed.
            // The underscore '_' indicates that the exception object itself is not used in the catch block.
            // In this case, it simply ignores the exception, meaning the taskList will remain empty if the file doesn't exist.
            // Ignore
        }
    }

    // Defines a private function named 'writeListToStream' that takes a 'outputStream' of type FileOutputStream as a parameter.
    // This helper function writes the items in 'taskList' to the provided output stream.
    private fun writeListToStream(outputStream: FileOutputStream) {
        // Creates a PrintWriter that will write text to the 'outputStream'.
        // PrintWriter provides convenient methods for writing formatted text, like println.
        val writer = PrintWriter(outputStream)
        // Iterates over each 'item' (string) in the 'taskList'.
        for (item in taskList) {
            // Writes the current 'item' followed by a line separator to the file via the PrintWriter.
            writer.println(item)
        }
        // Closes the PrintWriter. This is important as it flushes any buffered output to the underlying stream
        // and then closes the stream itself.
        writer.close()
    }
}