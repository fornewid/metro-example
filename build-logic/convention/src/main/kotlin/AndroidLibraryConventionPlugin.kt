import io.github.fornewid.metro.buildlogic.configureAndroid
import io.github.fornewid.metro.buildlogic.configureKotlin
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidLibraryConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
            }
            configureAndroid()
            configureKotlin()
        }
    }
}
