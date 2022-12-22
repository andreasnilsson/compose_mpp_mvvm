import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.seiko.imageloader.ImageLoader
import com.seiko.imageloader.ImageLoaderBuilder

@Composable
actual fun generateImageLoader(): ImageLoader {
    return ImageLoaderBuilder(LocalContext.current).build()
}