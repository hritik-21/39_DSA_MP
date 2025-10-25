# Call Center Simulator 📞

A simple desktop application built with Java Swing that simulates a call center's waiting line. This project was created as a mini-project for a Data Structures and Algorithms course to demonstrate a practical, real-world application of the **`Queue`** data structure.

The simulator manages incoming customer calls based on the **First-In, First-Out (FIFO)** principle, ensuring the first caller to wait is the first to be helped.

<img width="703" height="504" alt="Screenshot 2025-10-26 at 1 02 28 AM" src="https://github.com/user-attachments/assets/889c4590-c43c-40e0-9b7f-e1df20830896" />

-----

## 🎯 Core Concepts Demonstrated

  * **Data Structure: `Queue`**

      * **Implementation:** `java.util.LinkedList`
      * **Why:** The `Queue` is the perfect data structure for this problem, providing constant-time **$O(1)$** complexity for both adding (enqueuing) and removing (dequeuing) elements. This is far more efficient than an `ArrayList`, which has an $O(n)$ complexity for removing from the front.

  * **Algorithm: FIFO (First-In, First-Out)**

      * **Why:** This is the core logic of any fair waiting line. The simulator strictly enforces that the first call to be added to the queue is the first one to be processed.

-----

## ✨ Features

  * **Modern UI:** Uses the third-party **FlatLaf (Light)** library for a clean, modern, and responsive user interface (a significant improvement over the default Java Swing look).
  * **Add Calls:** A simple form to enter a caller's name and their issue.
  * **Process Calls:** A "Process Next Call" button that removes the call from the *front* of the queue (FIFO) and displays its details in a pop-up.
  * **Real-time Queue Display:** A scrollable text area that updates instantly, showing all calls currently waiting in the queue, in order.
  * **Clear Queue:** A button to clear all waiting calls from the queue (with a confirmation dialog).
  * **Demo Data:** Pre-loads the queue with sample data on startup for immediate demonstration.

-----

## 🛠️ Technology Stack

  * **Language:** Java (JDK 8+)
  * **Core Data Structure:** `java.util.Queue` (implemented with `java.util.LinkedList`)
  * **GUI:** `javax.swing` (Java Swing)
  * **GUI Theme:** [FlatLaf](https://github.com/JFormDesigner/FlatLaf) (for the modern look and feel)

-----

## 🚀 Getting Started

To run this project locally, you'll need Java (JDK 8 or higher) and an IDE like IntelliJ IDEA or Eclipse.

### 1\. Clone the Repository

```bash
git clone https://github.com/[YOUR_USERNAME]/[YOUR_REPOSITORY_NAME].git
cd [YOUR_REPOSITORY_NAME]
```

### 2\. Add the FlatLaf Library (Crucial\!)

This project requires the **FlatLaf** library (`.jar` file) to run, as it controls the GUI theme.

1.  **Download:** Go to the [FlatLaf GitHub releases page](https://github.com/JFormDesigner/FlatLaf/releases) and download the latest `flatlaf-x.x.jar` file (e.g., `flatlaf-3.4.1.jar`).
2.  **Add to your IDE:**
      * **In IntelliJ IDEA:**
          * Go to **File \> Project Structure...** (or press `Ctrl+Alt+Shift+S`).
          * Select **Libraries** from the left-hand menu.
          * Click the **`+`** (Add) button and select **Java**.
          * Navigate to and select the `flatlaf-x.x.jar` file you just downloaded.
          * Click **OK** to apply the changes.

### 3\. Run the Application

Once the library is added, you can run the project:

  * Locate the `CallCenterGUI.java` file.
  * Right-click it and select **"Run 'CallCenterGUI.main()'"**.

-----

## 📋 How to Use

1.  Launch the application. The queue display will show the pre-loaded demo calls.
2.  To add a new call, type a **Name** and **Issue** in the top panel and click **"Add Call to Queue"**.
3.  Observe the new call appearing at the *bottom* of the queue display.
4.  To process a call, click **"Process Next Call"**. A pop-up will show the details of the call from the *top* of the list.
5.  The queue display will update, showing the processed call has been removed.
6.  Click **"Clear All Calls"** to empty the queue.

-----

## 📄 License

This project is licensed under the MIT License. See the `LICENSE` file for details.
