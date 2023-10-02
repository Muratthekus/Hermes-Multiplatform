package me.thekusch.hermes.android

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.*
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint
import me.thekusch.hermes.android.home.HomeActivity
import me.thekusch.hermes.android.home.HomeScreen
import me.thekusch.hermes.android.intro.ui.IntroFragment
import me.thekusch.hermes.util.KMMStorage
import me.thekusch.hermes.util.GlobalConstant

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (KMMStorage(this)
                .getBoolean(
                    GlobalConstant.PrefKeys.IS_USER_COMPLETE_REGISTRATION
                ).not()
        ) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container, IntroFragment.newInstance())
                .commit()
        } else {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}