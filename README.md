# To-Do List App

A simple Android application that allows users to add tasks to a to-do list, view them in a numbered format, and clear all items at once. The app stores tasks using internal file storage, so the list persists between app launches.

---

## 📱 Features

- Add new to-do items using the text field and **ADD** button  
- View all tasks in a clean, numbered list  
- Clear the entire list with one tap using the **CLEAR** button  
- Automatically saves tasks to internal storage  
- Restores the saved list when the app is reopened  

---

## 🖼️ Screenshots

### **1. App on first load**
<img src="./screenshots/1.png" width="300"/>

### **2. After adding several to-do items**
<img src="./screenshots/2.png" width="300"/>

### **3. After pressing CLEAR (list emptied)**
<img src="./screenshots/3.png" width="300"/>

---

## 🧱 Architecture Overview

The app contains two main Kotlin files:

### **ToDoList.kt**
Handles:
- Adding items  
- Clearing items  
- Saving items to a text file  
- Loading items on app startup  

Tasks are stored internally in `todolist.txt`.

### **MainActivity.kt**
Handles:
- User interactions (Add / Clear buttons)
- Displaying the list
- Lifecycle events (`onResume`, `onPause`)
- Communicating with the `ToDoList` class

---

## ▶️ How It Works

1. Enter a to-do item in the text field  
2. Tap **ADD** → the item appears in the list  
3. Tap **CLEAR** → the entire list resets  
4. Closing the app automatically saves the list  
5. Reopening the app restores it

---

## 🛠️ Technologies Used

- **Kotlin**
- **Android SDK**
- **Internal File Storage (FileInputStream / FileOutputStream)**
- **XML Layouts**

---
