import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidMetroConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("dev.zacsweers.metro")
        }
    }
}
