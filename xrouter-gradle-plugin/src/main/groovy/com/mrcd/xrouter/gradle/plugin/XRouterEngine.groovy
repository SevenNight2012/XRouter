package com.mrcd.xrouter.gradle.plugin

import com.mrcd.xrouter.gradle.plugin.configs.DevelopConfig
import com.mrcd.xrouter.gradle.plugin.core.task.MakeRoutersTask
import com.mrcd.xrouter.gradle.plugin.utils.Constant
import com.mrcd.xrouter.gradle.plugin.utils.GradleUtils
import com.mrcd.xrouter.gradle.plugin.utils.StringUtils
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.tasks.compile.JavaCompile

/**
 * XRouter Gradle插件的入口类
 */
class XRouterEngine implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.extensions.create(Constant.ROUTER_CONFIG, DevelopConfig.class)

        List<String> compileModuleProjects = new ArrayList<>()

        project.rootProject.childProjects.values().each { childProject ->
            Set<Task> compileTasks = childProject.tasks.withType(JavaCompile).findAll {
                it.name.endsWith("JavaWithJavac") && !it.name.contains("Test")
            }
            compileTasks.each { compile ->
                compile.doFirst {
                    println("${compile.name}  >>> ${compile.project.name}")
                    compileModuleProjects.add("${compile.project.name}")
                }
            }
        }

        Project childProject = project.rootProject.childProjects.values().find { child -> GradleUtils.isApp(child) }

        project.afterEvaluate {
            DevelopConfig config = project.XRouterConfig
            if (StringUtils.isEmpty(config.appModule)) {
                config.appModule = childProject.name
            }
            config.buildTypes.each { buildType ->
                String taskName = "make-" + buildType + "-routers"
//                String dependsTaskName = "compile" + buildType + "JavaWithJavac"
                String dependsTaskName = ":${config.appModule}:compile${buildType}JavaWithJavac"
                Task makeRouters = project.tasks.create(taskName, MakeRoutersTask.class, {
                    mProjects = compileModuleProjects
                })
                makeRouters.group = Constant.XROUTER_NAME
                makeRouters.description = Constant.MAKE_ROUTERS_TASK_DESCRIPTION
                makeRouters.dependsOn = [dependsTaskName]
            }
        }

    }
}