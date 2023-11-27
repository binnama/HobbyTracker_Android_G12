package hiof.g12

import android.app.Application
import androidx.test.runner.AndroidJUnitRunner
import dagger.hilt.android.testing.HiltTestApplication
import android.content.Context

// A custom runner to set up the instrumented application class for tests.
// Hentet fra: https://developer.android.com/training/dependency-injection/hilt-testing
class CustomTestRunner : AndroidJUnitRunner() {

    override fun newApplication(cl: ClassLoader?, name: String?, context: Context?): Application {
        return super.newApplication(cl, HiltTestApplication::class.java.name, context)
    }
}