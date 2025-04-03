# TaskManager

TaskManager is an Android application designed to help users manage their tasks efficiently. Built using Kotlin, it leverages modern Android development practices to provide a seamless user experience.

## Features

- **Task Creation**: Add new tasks with details such as title, description, and due date.
- **Task Editing**: Modify existing tasks to keep information up-to-date.
- **Task Deletion**: Remove tasks that are no longer needed.
- **Task Listing**: View all tasks in a structured list format.

## Screenshots

![Screenshot_20250403_154833](https://github.com/user-attachments/assets/cda0723e-e7df-4b50-a000-7e46a8b99b77)
![Screenshot_20250403_154824](https://github.com/user-attachments/assets/37c69add-f529-41cd-b57d-3bcb5d5c7698)
![Screenshot_20250403_154816](https://github.com/user-attachments/assets/7861a8c1-3f9f-4431-a26c-737ed9264265)
![Screenshot_20250403_154801](https://github.com/user-attachments/assets/92bf1f7b-3a3d-474b-893a-6b6f00468f1d)


## Installation

1. **Clone the Repository**:

   ```bash
   git clone https://github.com/jay-vaghasiya/TaskManager.git
   ```

2. **Open in Android Studio**:

   - Launch Android Studio.
   - Select "Open an existing project".
   - Navigate to the cloned repository's directory and select it.

3. **Build the Project**:

   - Allow Android Studio to sync and build the project. Ensure all dependencies are resolved.

4. **Run the Application**:

   - Connect an Android device or start an emulator.
   - Click the "Run" button in Android Studio to install and launch the app.

## Package Structure

The project follows a modular approach to maintain clean architecture.

```
com.taskmanager
│── data
│   │── model         # Data models for tasks
│   │── repository    # Repository handling data sources
│
│── ui
│   │── components    # Reusable UI components
│   │── screens       # Screens (Task List, Add/Edit Task)
│
│── utils            # Utility classes and helper functions
│── viewmodel        # ViewModel classes for managing UI logic
```

## Dependencies

- **Kotlin Standard and Androidx Libraries**: Kotlin, Jetpack Compose, Datetime, Room and Datastore.
- ** Libraries**: Lottiefiles.
- **Material Components**: Implements Material Design UI components.

## Contributing

I welcome contributions to enhance TaskManager. To contribute:

1. Fork the repository.
2. Create a new branch for your feature or bug fix.
3. Implement your changes.
4. Submit a pull request with a detailed description of your changes.


## Contact

For questions or suggestions, please open an issue in the repository or contact the maintainer at [jayvaghasiya25@gmail.com).

---


