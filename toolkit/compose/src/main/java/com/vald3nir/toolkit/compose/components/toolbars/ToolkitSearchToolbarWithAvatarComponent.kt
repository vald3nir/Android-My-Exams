package com.vald3nir.toolkit.compose.components.toolbars

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.vald3nir.toolkit.compose.components.base.DefaultSpaceHeight
import com.vald3nir.toolkit.compose.components.base.ToolkitIcons
import com.vald3nir.toolkit.compose.components.base.defaultSpace
import com.vald3nir.toolkit.compose.components.inputs.ToolkitInputTextComponent
import com.vald3nir.toolkit.compose.designSystem.DefaultThemeColors
import com.vald3nir.toolkit.compose.designSystem.schema.ScreenColorSchema

@Composable
fun ToolkitSearchToolbarWithAvatarComponent(
    modifier: Modifier = Modifier,
    colors: ScreenColorSchema,
    searchQuery: String = "",
    placeholderText: String = "Pesquisar",
    userImageUrl: String? = null,
    onQueryChange: (String) -> Unit = {},
    onAvatarClick: () -> Unit = {},
) {
    var isLoading by remember { mutableStateOf(false) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = colors.toolbarBackgroundColor,
                shape = RoundedCornerShape(24.dp)
            )
            .padding(horizontal = defaultSpace),
        verticalAlignment = Alignment.CenterVertically
    ) {

        ToolkitInputTextComponent(
            inputValue = searchQuery,
            useTransparentBackend = true,
            onValueChange = onQueryChange,
            placeholder = placeholderText,
            modifier = Modifier.weight(1f),
            singleLine = true,
            colors = colors,
            startIcon = ToolkitIcons.Search,
            endIcon = if (searchQuery.isNotEmpty()) ToolkitIcons.Close else null,
            onClickEndIcon = { onQueryChange("") }
        )

        if (searchQuery.isEmpty()) {
            val avatarModifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
            if (isLoading) {
                CircularProgressIndicator(modifier = avatarModifier)
            } else {
                AsyncImage(
                    model = userImageUrl,
                    colorFilter = ColorFilter.tint(color = colors.toolbarIconTint),
                    placeholder = rememberVectorPainter(Icons.Default.AccountCircle),
                    error = rememberVectorPainter(Icons.Default.AccountCircle),
                    contentDescription = "Avatar do usuário",
                    onLoading = { isLoading = true },
                    onSuccess = { isLoading = false },
                    onError = {
                        it.result.throwable.printStackTrace()
                        isLoading = false
                    },
                    contentScale = ContentScale.Crop,
                    modifier = avatarModifier.clickable { onAvatarClick() },
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
private fun Preview() {
    val colors = DefaultThemeColors().darkColors
    Column(modifier = Modifier.padding(8.dp)) {
        ToolkitSearchToolbarWithAvatarComponent(colors = colors)
        DefaultSpaceHeight()
        ToolkitSearchToolbarWithAvatarComponent(colors = colors, searchQuery = "Teste")
    }
}