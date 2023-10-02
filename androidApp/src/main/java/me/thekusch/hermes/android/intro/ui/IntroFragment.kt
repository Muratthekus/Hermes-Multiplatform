package me.thekusch.hermes.android.intro.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint
import me.thekusch.hermes.android.R
import me.thekusch.hermes.android.ui.theme.HermesTheme
import me.thekusch.hermes.android.core.widget.HorizontalPager
import me.thekusch.hermes.android.signup.ui.SignUpInfoScreen
import me.thekusch.hermes.SharedRes
import me.thekusch.hermes.android.core.widget.stringResourceCommonMain
import me.thekusch.hermes.android.intro.domain.model.IntroItem

@ExperimentalPagerApi
@AndroidEntryPoint
class IntroFragment : Fragment() {

    private lateinit var composeView: ComposeView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return ComposeView(requireContext()).also {
            composeView = it
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        composeView.setContent {
            HermesTheme {
                IntroScreen()
            }
        }
    }

    @Composable
    fun IntroScreen() {
        IntroContent()
    }

    @Composable
    fun IntroContent() {

        var introPagerIndex by remember {
            mutableStateOf(0)
        }

        val result = getIntroItems()
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
        ) {
            HorizontalPager(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(bottom = 60.dp)
                    .align(Alignment.Center),
                itemsCount = result.size,
                items = result
            ) { introItem, pagerIndex ->
                introPagerIndex = pagerIndex
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        modifier = Modifier.size(270.dp),
                        painter = painterResource(id = introItem.image),
                        contentDescription = "image"
                    )

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(130.dp)
                            .padding(top = 40.dp, start = 45.dp, end = 45.dp),
                        text = introItem.text,
                        style = MaterialTheme.typography.h2,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colors.onBackground
                    )
                }
            }
            SharedRes.strings.intro_page_button
            AnimatedVisibility(
                modifier = Modifier.align(Alignment.BottomCenter),
                visible = introPagerIndex == result.size - 1,
            ) {
                Button(
                    modifier = Modifier
                        .padding(horizontal = 24.dp, vertical = 18.dp)
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = RoundedCornerShape(30.dp),
                    onClick = {
                        activity?.supportFragmentManager?.beginTransaction()
                            ?.replace(R.id.container, SignUpInfoScreen.newInstance())
                            ?.addToBackStack(null)
                            ?.commit();
                    }) {
                    Text(
                        text = stringResourceCommonMain(id = SharedRes.strings.intro_page_button),
                        color = MaterialTheme.colors.onPrimary
                    )
                }
            }
        }
    }

    @Composable
    fun getIntroItems(): List<IntroItem> = buildList {
        add(
            IntroItem(
                R.drawable.intro_item_1,
                stringResourceCommonMain(id = SharedRes.strings.intro_item_1_text)
            )
        )
        add(
            IntroItem(
                R.drawable.intro_item_1,
                stringResourceCommonMain(id = SharedRes.strings.intro_item_1_text)
            )
        )
        add(
            IntroItem(
                R.drawable.intro_item_1,
                stringResourceCommonMain(id = SharedRes.strings.intro_item_1_text)
            )
        )
        add(
            IntroItem(
                R.drawable.intro_item_1,
                stringResourceCommonMain(id = SharedRes.strings.intro_item_1_text)
            )
        )
    }

    companion object {
        @JvmStatic
        fun newInstance() = IntroFragment()
    }
}