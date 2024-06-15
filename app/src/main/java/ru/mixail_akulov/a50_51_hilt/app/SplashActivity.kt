package ru.mixail_akulov.a50_51_hilt.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import ru.mixail_akulov.a50_51_hilt.app.screens.splash.SplashFragment
import ru.mixail_akulov.a50_51_hilt.app.screens.splash.SplashViewModel

/**
 * Точка входа в приложение. Заставка содержит только фон окна,
 * вся остальная логика инициализации размещена в [SplashFragment] and [SplashViewModel].
 */
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

}
