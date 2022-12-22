import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.myapplication.contacts.ContactsScreen
import com.myapplication.ui.theme.MvvmProjectTheme
import com.seiko.imageloader.ImageLoader
import com.seiko.imageloader.LocalImageLoader

@Composable
fun App() {
    MvvmProjectTheme {
        CompositionLocalProvider(
            LocalImageLoader provides generateImageLoader(),
        ) {
            ContactsScreen()
        }
    }
}

@Composable
expect fun generateImageLoader(): ImageLoader

