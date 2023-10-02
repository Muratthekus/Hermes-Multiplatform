package me.thekusch.hermes.android.core.widget

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import dev.icerock.moko.resources.StringResource
import me.thekusch.hermes.util.Strings

@Composable
fun stringResourceCommonMain(id: StringResource, vararg args: Any): String {
    return Strings().get(id, args.toList()).toString(LocalContext.current)
}