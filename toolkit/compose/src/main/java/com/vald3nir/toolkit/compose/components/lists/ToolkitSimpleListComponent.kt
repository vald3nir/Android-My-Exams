package com.vald3nir.toolkit.compose.components.lists

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Downloading
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


data class SimpleItemList(
    val icon: ImageVector,
    val title: String,
    val btnLabel: String,
    val onClickListener: () -> Unit
)


@Composable
fun BuildSimpleListComponent(items: List<SimpleItemList>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        itemsIndexed(items) { _, item ->
            BuildSimpleItemListComponent(item)
        }
    }
}


@Composable
fun BuildSimpleItemListComponent(item: SimpleItemList, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { item.onClickListener() }
    ) {
        Icon(
            imageVector = item.icon,
            contentDescription = null,
            modifier = modifier.size(40.dp)
        )
        Spacer(modifier = modifier.width(8.dp))
        Text(
            text = item.title,
            modifier = modifier.weight(1f)
        )
        Button(
            modifier = modifier.wrapContentSize(),
            onClick = { item.onClickListener() }
        ) {
            Text(item.btnLabel)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ShowComponent() {
    val items = ArrayList<SimpleItemList>()
    List(5) {
        items.add(
            SimpleItemList(
                icon = Icons.Outlined.Downloading,
                title = "Item $it",
                btnLabel = "Ver",
                onClickListener = {})
        )
    }
    BuildSimpleListComponent(items)
}