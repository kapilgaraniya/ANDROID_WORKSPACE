package com.example.e_learning

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "QuizApp.db"
        const val DATABASE_VERSION = 1

        const val TABLE_USERS = "users"
        const val COLUMN_USER_ID = "id"
        const val COLUMN_FIRST_NAME = "first_name"
        const val COLUMN_LAST_NAME = "last_name"
        const val COLUMN_EMAIL = "email"
        const val COLUMN_PASSWORD = "password"

        const val TABLE_QUESTIONS = "questions"
        const val COLUMN_ID = "id"
        const val COLUMN_QUESTION = "question"
        const val COLUMN_OPTION1 = "option1"
        const val COLUMN_OPTION2 = "option2"
        const val COLUMN_OPTION3 = "option3"
        const val COLUMN_OPTION4 = "option4"
        const val COLUMN_ANSWER = "answer"
    }

    override fun onCreate(db: SQLiteDatabase) {

        var createUserTableQuery = """
            CREATE TABLE $TABLE_USERS (
                $COLUMN_USER_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_FIRST_NAME TEXT,
                $COLUMN_LAST_NAME TEXT,
                $COLUMN_EMAIL TEXT UNIQUE,
                $COLUMN_PASSWORD TEXT
            )
        """
        db.execSQL(createUserTableQuery)

        var createQuestionsTableQuery = """
            CREATE TABLE $TABLE_QUESTIONS (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_QUESTION TEXT,
                $COLUMN_OPTION1 TEXT,
                $COLUMN_OPTION2 TEXT,
                $COLUMN_OPTION3 TEXT,
                $COLUMN_OPTION4 TEXT,
                $COLUMN_ANSWER INTEGER
            )
        """
        db.execSQL(createQuestionsTableQuery)

        insertSampleQuestions(db)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_QUESTIONS")
        onCreate(db)
    }

    fun insertUser(firstName: String, lastName: String, email: String, password: String): Boolean {
        var db = writableDatabase
        var values = ContentValues()
        values.put(COLUMN_FIRST_NAME, firstName)
        values.put(COLUMN_LAST_NAME, lastName)
        values.put(COLUMN_EMAIL, email)
        values.put(COLUMN_PASSWORD, password)

        var result = db.insert(TABLE_USERS, null, values)
        db.close()
        return result != -1L
    }

    fun checkUser(email: String, password: String): Boolean {
        var db = readableDatabase
        var cursor: Cursor = db.rawQuery(
            "SELECT * FROM $TABLE_USERS WHERE $COLUMN_EMAIL = ? AND $COLUMN_PASSWORD = ?",
            arrayOf(email, password)
        )
        var exists = cursor.count > 0
        cursor.close()
        db.close()
        return exists
    }

    @SuppressLint("Range")
    fun readAllQuestions(): List<Model> {
        var questionsList = mutableListOf<Model>()
        var db = readableDatabase
        var cursor = db.rawQuery("SELECT * FROM $TABLE_QUESTIONS", null)

        if (cursor.moveToFirst()) {
            do {
                val question = Model(
                    id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID)),
                    questionText = cursor.getString(cursor.getColumnIndex(COLUMN_QUESTION)),
                    option1 = cursor.getString(cursor.getColumnIndex(COLUMN_OPTION1)),
                    option2 = cursor.getString(cursor.getColumnIndex(COLUMN_OPTION2)),
                    option3 = cursor.getString(cursor.getColumnIndex(COLUMN_OPTION3)),
                    option4 = cursor.getString(cursor.getColumnIndex(COLUMN_OPTION4)),
                    answer = cursor.getInt(cursor.getColumnIndex(COLUMN_ANSWER))
                )
                questionsList.add(question)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return questionsList
    }

    private fun insertSampleQuestions(db: SQLiteDatabase) {
        var insertQuestions = listOf(
            "INSERT INTO $TABLE_QUESTIONS (question, option1, option2, option3, option4, answer) VALUES ('What is the default access modifier in Kotlin?', 'public', 'private', 'protected', 'internal', 4)",
            "INSERT INTO $TABLE_QUESTIONS (question, option1, option2, option3, option4, answer) VALUES ('Which keyword is used to create a class in Kotlin?', 'class', 'data', 'interface', 'object', 1)",
            "INSERT INTO $TABLE_QUESTIONS (question, option1, option2, option3, option4, answer) VALUES ('Which of the following is a valid type of database in DBMS?', 'SQL', 'NoSQL', 'Relational Database', 'All of the above', 4)",
            "INSERT INTO $TABLE_QUESTIONS (question, option1, option2, option3, option4, answer) VALUES ('Which API is used to send HTTP requests in Android?', 'Retrofit', 'Volley', 'OkHttp', 'All of the above', 4)",
            "INSERT INTO $TABLE_QUESTIONS (question, option1, option2, option3, option4, answer) VALUES ('What is Firebase used for in Android development?', 'Real-time database', 'Authentication', 'Push notifications', 'All of the above', 4)",
            "INSERT INTO $TABLE_QUESTIONS (question, option1, option2, option3, option4, answer) VALUES ('Which method is used to start a new Activity in Android?', 'startActivity()', 'startService()', 'sendBroadcast()', 'startNewActivity()', 1)",
            "INSERT INTO $TABLE_QUESTIONS (question, option1, option2, option3, option4, answer) VALUES ('In Kotlin, which of the following is used to handle null values?', '?.', '!!', 'let', 'All of the above', 4)",
            "INSERT INTO $TABLE_QUESTIONS (question, option1, option2, option3, option4, answer) VALUES ('What is the use of a RecyclerView in Android?', 'Display a list of items', 'Create animations', 'Handle HTTP requests', 'Manage databases', 1)",
            "INSERT INTO $TABLE_QUESTIONS (question, option1, option2, option3, option4, answer) VALUES ('What is the difference between a Fragment and an Activity in Android?', 'Fragments can exist independently', 'Activity is used for UI elements', 'Fragments can be reused', 'All of the above', 4)",
            "INSERT INTO $TABLE_QUESTIONS (question, option1, option2, option3, option4, answer) VALUES ('Which of the following is a feature of Firebase?', 'Realtime database', 'Cloud storage', 'Analytics', 'All of the above', 4)",
            "INSERT INTO $TABLE_QUESTIONS (question, option1, option2, option3, option4, answer) VALUES ('What does API stand for?', 'Application Programming Interface', 'Advanced Programming Interface', 'Application Protocol Interface', 'None of the above', 1)",
            "INSERT INTO $TABLE_QUESTIONS (question, option1, option2, option3, option4, answer) VALUES ('Which method is used to retrieve a value from SharedPreferences in Android?', 'getSharedPreferences()', 'get()', 'getString()', 'getValue()', 3)",
            "INSERT INTO $TABLE_QUESTIONS (question, option1, option2, option3, option4, answer) VALUES ('What is the role of the onCreate() method in Android?', 'To initialize resources', 'To handle UI', 'To manage background tasks', 'To clean up resources', 1)",
            "INSERT INTO $TABLE_QUESTIONS (question, option1, option2, option3, option4, answer) VALUES ('What is the purpose of a ViewModel in Android?', 'To store UI-related data', 'To manage background tasks', 'To update the UI', 'None of the above', 1)",
            "INSERT INTO $TABLE_QUESTIONS (question, option1, option2, option3, option4, answer) VALUES ('Which of the following is used for dependency injection in Android?', 'Dagger', 'Hilt', 'Koin', 'All of the above', 4)",
            "INSERT INTO $TABLE_QUESTIONS (question, option1, option2, option3, option4, answer) VALUES ('What is the purpose of the Firebase Firestore database?', 'Store data offline', 'Real-time data storage', 'Store large files', 'Store analytics data', 2)",
            "INSERT INTO $TABLE_QUESTIONS (question, option1, option2, option3, option4, answer) VALUES ('Which class is used for network operations in Android?', 'HttpURLConnection', 'HttpClient', 'Retrofit', 'URLConnection', 1)",
            "INSERT INTO $TABLE_QUESTIONS (question, option1, option2, option3, option4, answer) VALUES ('What is the use of the Retrofit library in Android?', 'Handling UI', 'Handling HTTP requests', 'Handling background tasks', 'Handling database operations', 2)",
            "INSERT INTO $TABLE_QUESTIONS (question, option1, option2, option3, option4, answer) VALUES ('In Kotlin, what does the !! operator do?', 'Throws an exception if the value is null', 'Throws a warning', 'Converts the value to non-nullable', 'None of the above', 1)",
            "INSERT INTO $TABLE_QUESTIONS (question, option1, option2, option3, option4, answer) VALUES ('What is an Intent used for in Android?', 'Communicate between components', 'Start a service', 'Pass data between apps', 'All of the above', 4)",
            "INSERT INTO $TABLE_QUESTIONS (question, option1, option2, option3, option4, answer) VALUES ('What is Kotlin Coroutines used for?', 'Concurrency', 'Database operations', 'UI updates', 'Error handling', 1)",
            "INSERT INTO $TABLE_QUESTIONS (question, option1, option2, option3, option4, answer) VALUES ('What is the purpose of Firebase Authentication?', 'User sign-up and sign-in', 'Database management', 'Push notifications', 'Realtime data sync', 1)",
            "INSERT INTO $TABLE_QUESTIONS (question, option1, option2, option3, option4, answer) VALUES ('Which method is used to get the application context in Android?', 'getContext()', 'getApplicationContext()', 'getBaseContext()', 'getContext()', 2)",
            "INSERT INTO $TABLE_QUESTIONS (question, option1, option2, option3, option4, answer) VALUES ('Which layout arranges UI components in a horizontal row?', 'LinearLayout', 'RelativeLayout', 'TableLayout', 'GridLayout', 1)",
            "INSERT INTO $TABLE_QUESTIONS (question, option1, option2, option3, option4, answer) VALUES ('What is the main use of the Firebase Realtime Database?', 'Store real-time data', 'Handle notifications', 'Handle analytics', 'All of the above', 1)",
            "INSERT INTO $TABLE_QUESTIONS (question, option1, option2, option3, option4, answer) VALUES ('Which library is used for image loading in Android?', 'Glide', 'Picasso', 'Fresco', 'All of the above', 4)",
            "INSERT INTO $TABLE_QUESTIONS (question, option1, option2, option3, option4, answer) VALUES ('What is the purpose of the onPause() method in Android?', 'Pause the application', 'Release resources', 'Stop background tasks', 'None of the above', 2)",
            "INSERT INTO $TABLE_QUESTIONS (question, option1, option2, option3, option4, answer) VALUES ('Which method is used to stop a service in Android?', 'stopService()', 'stopSelf()', 'stopActivity()', 'stopBackgroundTask()', 1)",
            "INSERT INTO $TABLE_QUESTIONS (question, option1, option2, option3, option4, answer) VALUES ('Which class is used for manipulating SQLite databases in Android?', 'SQLiteDatabase', 'SQLiteOpenHelper', 'SQLiteManager', 'SQLiteHandler', 2)",
            "INSERT INTO $TABLE_QUESTIONS (question, option1, option2, option3, option4, answer) VALUES ('What is the default value of the boolean variable in Kotlin?', 'false', 'true', '0', 'null', 1)",
            "INSERT INTO $TABLE_QUESTIONS (question, option1, option2, option3, option4, answer) VALUES ('What is the main purpose of the onResume() method in Android?', 'Start UI elements', 'Resume the activity', 'Reset activity state', 'None of the above', 2)",
            "INSERT INTO $TABLE_QUESTIONS (question, option1, option2, option3, option4, answer) VALUES ('What is the main purpose of an AsyncTask in Android?', 'Execute background operations', 'Handle UI', 'Manage databases', 'Manage activities', 1)",
            "INSERT INTO $TABLE_QUESTIONS (question, option1, option2, option3, option4, answer) VALUES ('What is the main function of Firebase Cloud Messaging?', 'Handle notifications', 'Handle analytics', 'Handle database operations', 'None of the above', 1)",
            "INSERT INTO $TABLE_QUESTIONS (question, option1, option2, option3, option4, answer) VALUES ('What is the default value of the int variable in Kotlin?', '0', 'null', 'undefined', '1', 1)",
            "INSERT INTO $TABLE_QUESTIONS (question, option1, option2, option3, option4, answer) VALUES ('What is the main use of the RecyclerView.Adapter class in Android?', 'Bind data to UI components', 'Manage database operations', 'Handle user input', 'Manage views', 1)",
            "INSERT INTO $TABLE_QUESTIONS (question, option1, option2, option3, option4, answer) VALUES ('What is the purpose of Firebase Cloud Storage?', 'Store large files', 'Store real-time data', 'Push notifications', 'None of the above', 1)",
            "INSERT INTO $TABLE_QUESTIONS (question, option1, option2, option3, option4, answer) VALUES ('Which keyword in Kotlin is used to create a singleton class?', 'object', 'data', 'class', 'companion', 1)",
            "INSERT INTO $TABLE_QUESTIONS (question, option1, option2, option3, option4, answer) VALUES ('What is an IntentService used for in Android?', 'Handle background tasks', 'Manage database operations', 'Manage UI elements', 'Manage notifications', 1)",
            "INSERT INTO $TABLE_QUESTIONS (question, option1, option2, option3, option4, answer) VALUES ('Which of the following is an Android Jetpack component?', 'LiveData', 'RecyclerView', 'Volley', 'None of the above', 1)",
            "INSERT INTO $TABLE_QUESTIONS (question, option1, option2, option3, option4, answer) VALUES ('What is the main purpose of a BroadcastReceiver in Android?', 'Receive system-wide broadcasts', 'Handle UI interactions', 'Manage background tasks', 'None of the above', 1)",
            "INSERT INTO $TABLE_QUESTIONS (question, option1, option2, option3, option4, answer) VALUES ('What is the purpose of Firebase Analytics?', 'Track app usage and performance', 'Track user sign-ins', 'Handle push notifications', 'Store user data', 1)",
            "INSERT INTO $TABLE_QUESTIONS (question, option1, option2, option3, option4, answer) VALUES ('Which library is used for dependency injection in Android?', 'Hilt', 'Dagger', 'Koin', 'All of the above', 4)",
            "INSERT INTO $TABLE_QUESTIONS (question, option1, option2, option3, option4, answer) VALUES ('What is the main purpose of Firebase Crashlytics?', 'Track app crashes and issues', 'Handle analytics', 'Track user behavior', 'Store large files', 1)",
            "INSERT INTO $TABLE_QUESTIONS (question, option1, option2, option3, option4, answer) VALUES ('What does the let function in Kotlin do?', 'Handles nullability', 'Iterates over collections', 'Returns a result', 'None of the above', 3)"

        )

        for (query in insertQuestions) {
            db.execSQL(query)
        }
    }
}
