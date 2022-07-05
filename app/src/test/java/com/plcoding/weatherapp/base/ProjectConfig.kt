package com.plcoding.weatherapp.base

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import io.kotest.core.config.AbstractProjectConfig

object ProjectConfig : AbstractProjectConfig() {
    override suspend fun beforeProject() {
        super.beforeProject()
        setupLiveData()
    }

    override suspend fun afterProject() {
        super.afterProject()
        resetLiveData()
    }

    private fun setupLiveData() {
        ArchTaskExecutor.getInstance().setDelegate(object : TaskExecutor() {
            override fun executeOnDiskIO(runnable: Runnable) {
                runnable.run()
            }

            override fun postToMainThread(runnable: Runnable) {
                runnable.run()
            }

            override fun isMainThread(): Boolean {
                return true
            }
        })
    }

    private fun resetLiveData() {
        ArchTaskExecutor.getInstance().setDelegate(null)
    }
}