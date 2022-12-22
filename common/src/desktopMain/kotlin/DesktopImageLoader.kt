import com.seiko.imageloader.ImageLoader
import com.seiko.imageloader.ImageLoaderBuilder

actual fun generateImageLoader(): ImageLoader {
    return ImageLoaderBuilder().build()
}