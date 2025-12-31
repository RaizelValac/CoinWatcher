package com.example.coinwatcher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.coinwatcher.ui.CoinScreen
import com.example.coinwatcher.ui.theme.CoinWatcherTheme
import com.example.coinwatcher.worker.SyncWorker
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        schedulePeriodicWork()
        enableEdgeToEdge()
        setContent {
            CoinWatcherTheme {
                CoinScreen()
            }
        }
    }

    fun schedulePeriodicWork() {
        val workRequest = PeriodicWorkRequestBuilder<SyncWorker>(15, TimeUnit.MINUTES)
            .setConstraints(
                Constraints.Builder(
                ).setRequiredNetworkType(NetworkType.CONNECTED).build()
            )

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "syncData",
            ExistingPeriodicWorkPolicy.KEEP,
            workRequest.build()
        )
    }

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CoinWatcherTheme {
        Greeting("Android")
    }
}