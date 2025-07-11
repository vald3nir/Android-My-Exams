package com.vald3nir.toolkit.compose.components.base

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material.icons.automirrored.rounded.ShortText
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Addchart
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.LocalMall
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Paid
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Pin
import androidx.compose.material.icons.filled.RemoveCircleOutline
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ShoppingBasket
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.Bookmarks
import androidx.compose.material.icons.outlined.Upcoming
import androidx.compose.material.icons.rounded.Bookmark
import androidx.compose.material.icons.rounded.BookmarkBorder
import androidx.compose.material.icons.rounded.Bookmarks
import androidx.compose.material.icons.rounded.Grid3x3
import androidx.compose.material.icons.rounded.Upcoming
import androidx.compose.material.icons.rounded.ViewDay
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun ToolkitIcon(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    contentDescription: String = "",
    tint: Color = Color.LightGray,
    onClick: (() -> Unit)? = null,
) {
    Icon(
        modifier = modifier.clickable { onClick?.invoke() },
        imageVector = imageVector,
        contentDescription = contentDescription,
        tint = tint
    )
}

@Composable
fun ImageVector.BuildIcon(
    modifier: Modifier = Modifier,
    tint: Color = Color.LightGray,
    onClick: (() -> Unit)? = null,
): @Composable (() -> Unit) {
    return {
        ToolkitIcon(
            modifier = modifier,
            imageVector = this,
            tint = tint,
            onClick = onClick
        )
    }
}

object ToolkitIcons {
    val AccountCircle = Icons.Default.AccountCircle
    val Add = Icons.Default.Add
    val AddChart = Icons.Filled.Addchart
    val ArrowBack = Icons.AutoMirrored.Rounded.ArrowBack
    val ArrowForward = Icons.AutoMirrored.Rounded.ArrowForward
    val ArrowIndicatorRight = Icons.AutoMirrored.Filled.ArrowForwardIos
    val Bookmark = Icons.Rounded.Bookmark
    val BookmarkBorder = Icons.Rounded.BookmarkBorder
    val Bookmarks = Icons.Rounded.Bookmarks
    val BookmarksBorder = Icons.Outlined.Bookmarks
    val Check = Icons.Default.Check
    val ChevronRight = Icons.Default.ChevronRight
    val ChevronUp = Icons.Default.KeyboardArrowUp
    val ChevronDown = Icons.Default.KeyboardArrowDown
    val Close = Icons.Default.Close
    val Delete = Icons.Default.Delete
    val Edit = Icons.Default.Edit
    val ExitToApp = Icons.AutoMirrored.Filled.ExitToApp
    val Favorite = Icons.Default.Favorite
    val FavoriteBorder = Icons.Default.FavoriteBorder
    val Grid3x3 = Icons.Rounded.Grid3x3
    val Home = Icons.Default.Home
    val Inbox = Icons.Default.Inbox
    val Info = Icons.Default.Info
    val Mall = Icons.Default.LocalMall
    val Menu = Icons.Default.Menu
    val MoreVert = Icons.Default.MoreVert
    val Notifications = Icons.Default.Notifications
    val Paid = Icons.Default.Paid
    val Person = Icons.Default.Person
    val Pin = Icons.Default.Pin
    val RemoveCircleOutline = Icons.Default.RemoveCircleOutline
    val Save = Icons.Default.Save
    val Search = Icons.Default.Search
    val Settings = Icons.Default.Settings
    val Share = Icons.Default.Share
    val ShoppingBasket = Icons.Default.ShoppingBasket
    val ShoppingCart = Icons.Default.ShoppingCart
    val ShortText = Icons.AutoMirrored.Rounded.ShortText
    val Star = Icons.Default.Star
    val Upcoming = Icons.Rounded.Upcoming
    val UpcomingBorder = Icons.Outlined.Upcoming
    val ViewDay = Icons.Rounded.ViewDay
    val Visibility = Icons.Default.Visibility
    val VisibilityOff = Icons.Default.VisibilityOff
    val Email = Icons.Default.Email
    val Password = Icons.Default.Password
    val ContentCopy = Icons.Default.ContentCopy
    val Science = Icons.Default.Science
    val DateRange = Icons.Default.DateRange
    val Logout = Icons.AutoMirrored.Filled.Logout
}