# Announcement Procedures Automation System 📢✨

A comprehensive JavaFX application designed to optimize the management of organizational announcements, providing modern features with an intuitive interface.

## 🌟 Key Features

### 🔒 User Authentication
Secure login and registration fuctionalities.

### 📣 Diverse Announcement Types
- **Advertisements**: Promote events or products.  
- **Proclamations**: Share official organizational updates.  
- **Personal Announcements**: For individual use or small groups.

### ✏️ CRUD Operations
Easily create, view, update, and delete announcements.

### 🎨 Visual Categorization
Intuitive grouping and display of announcements.

### 🖥️ Modern UI
- Gradient backgrounds for a poished look.  
- Responsive design to ensure usability across different screen sizes.

---

## 🛠️ Technical Stack

- **Programming Language**: Java ☕  
- **UI Framework**: JavaFX 🎭  
- **Layout Design**: FXML 🖌️  
- **Data Handling**: Google Gson & Jackson Annotations 📊  
- **Build Tool**: Maven 🏗️

---

## 🗂️ Project Structure

```plaintext
src/
├── main/
│   ├── java/
│   │   └── com/example/announcement_procedures_automation_projectoop/
│   │       ├── Controllers/        # Manages user interactions and app logic
│   │       ├── Announcements/      # Defines announcement types and related logic
│   │       ├── CustomCells/        # Custom UI components for better visuals
│   │       ├── DataBases/          # Simulated or actual database logic
│   │       └── Main.java           # Main application entry point
│   └── resources/
│       └── com/example/announcement_procedures_automation_projectoop/
│           ├── Images/             # UI assets
│           └── FXML files          # JavaFX layout files
```

---

## ⚙️ Setup Instructions

1. **Clone the Repository**  
```bash
git clone https://github.com/your-username/announcement-system.git
```

2. **Install Java JDK**  
Ensure Java JDK 17 or newer is installed.

3. **Install Maven**  
If not already installed, download and configure Maven.

4. **Build the Project**  
```bash
mvn clean install
```

5. **Run the Application**  
```bash
mvn javafx:run
```

---

## 📦 Dependencies

- **JavaFX**: For building the user interface.  
- **Google Gson**: To parse and handle JSON data.  
- **Jackson Annotations**: For data binding and serialization.
