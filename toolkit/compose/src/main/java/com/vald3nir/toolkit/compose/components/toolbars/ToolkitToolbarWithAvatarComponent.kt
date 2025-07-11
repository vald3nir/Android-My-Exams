package com.vald3nir.toolkit.compose.components.toolbars

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.vald3nir.toolkit.compose.components.base.DefaultSpaceHeight
import com.vald3nir.toolkit.compose.components.base.DefaultSpaceWidth
import com.vald3nir.toolkit.compose.components.base.ToolkitIcon
import com.vald3nir.toolkit.compose.components.base.ToolkitIcons
import com.vald3nir.toolkit.compose.components.base.ToolkitText
import com.vald3nir.toolkit.compose.components.base.halfSpace
import com.vald3nir.toolkit.compose.designSystem.DefaultThemeColors
import com.vald3nir.toolkit.compose.designSystem.schema.ScreenColorSchema

@Composable
fun ToolkitToolbarWithAvatarComponent(
    modifier: Modifier = Modifier,
    colors: ScreenColorSchema,
    title: String = "",
    useRoundedBorder: Boolean = true,
    userImageUrl: String? = null,
    onBackClick: (() -> Unit)? = null,
    onMenuClick: (() -> Unit)? = null,
    onAvatarClick: () -> Unit = {},
) {
    var showUserErrorState by remember { mutableStateOf(userImageUrl.isNullOrBlank()) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                colors.toolbarBackgroundColor,
                shape = RoundedCornerShape(if (useRoundedBorder) 24.dp else 0.dp)
            )
            .padding(halfSpace),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (onBackClick != null) {
            ToolkitIcon(
                imageVector = ToolkitIcons.ArrowBack,
                tint = colors.toolbarIconTint,
                modifier = Modifier.clickable { onBackClick() },
            )
        }
        if (onMenuClick != null) {
            ToolkitIcon(
                imageVector = ToolkitIcons.Menu,
                tint = colors.toolbarIconTint,
                modifier = Modifier.clickable { onMenuClick() },
            )
        }

        DefaultSpaceWidth()

        ToolkitText.Label(
            modifier = Modifier
                .weight(1f)
                .wrapContentWidth(Alignment.Start),
            text = title,
            textColor = colors.toolbarTextColor,
        )

        Spacer(Modifier.weight(1f))

        val avatarModifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
            .clickable {
                onAvatarClick()
            }

        if (showUserErrorState) {
            ToolkitIcon(
                imageVector = ToolkitIcons.AccountCircle,
                tint = colors.toolbarIconTint,
                modifier = avatarModifier,
                onClick = onAvatarClick
            )
        } else {
            AsyncImage(
                model = userImageUrl,
                placeholder = rememberVectorPainter(ToolkitIcons.AccountCircle),
                error = rememberVectorPainter(ToolkitIcons.AccountCircle),
                contentDescription = "Avatar do usuário",
                onError = {
                    it.result.throwable.printStackTrace()
                    showUserErrorState = true
                },
                contentScale = ContentScale.Crop,
                modifier = avatarModifier,
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
private fun Preview() {
    val colors = DefaultThemeColors().lightColors
    Column(modifier = Modifier.padding(8.dp)) {
        ToolkitToolbarWithAvatarComponent(
            colors = colors,
            title = "Toolbar Exemplo"
        )

        DefaultSpaceHeight()

        ToolkitToolbarWithAvatarComponent(
            colors = colors,
            title = "Toolbar Exemplo",
            onMenuClick = {})

        DefaultSpaceHeight()

        ToolkitToolbarWithAvatarComponent(
            colors = colors,
            title = "Toolbar Exemplo",
            onBackClick = {},
        )

        DefaultSpaceHeight()

        ToolkitToolbarWithAvatarComponent(
            colors = colors,
            title = "Toolbar Exemplo",
            onMenuClick = {},
            useRoundedBorder = false
        )

        DefaultSpaceHeight()

        ToolkitToolbarWithAvatarComponent(
            colors = colors,
            title = "Toolbar Exemplo",
            onBackClick = {},
            useRoundedBorder = false,
        )
    }
}