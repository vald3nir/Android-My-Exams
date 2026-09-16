package com.vald3nir.myexams.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.vald3nir.myexams.R
import com.vald3nir.myexams.presentation.features.exams.home.HomeProvider
import com.vald3nir.myexams.presentation.features.exams.home.ItemHomeUiModel
import com.vald3nir.toolkit.core.utils.extensions.isoToShortDate
import com.vald3nir.toolkit.designsystem.components.ToolkitSpaceHeight
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingMd
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingXs
import com.vald3nir.toolkit.designsystem.components.containers.ToolkitCard
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIcon
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.components.notifications.AlertDot
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer

@Composable
internal fun HomeExamsCell(lab: String?, date: String?, alerts: Int = 0, onEdit: () -> Unit) {
    ToolkitCard(
        modifier = Modifier,
        onClick = onEdit,
        content = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(ToolkitSpacingMd),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    ToolkitText(
                        text = lab ?: stringResource(R.string.home_screen_laboratory_not_specified),
                        style = ToolkitTextStyle.TitleMedium,
                    )
                    ToolkitSpaceHeight(ToolkitSpacingXs)
                    ToolkitText(
                        text = date?.isoToShortDate() ?: stringResource(R.string.home_screen_date_not_specified),
                        style = ToolkitTextStyle.BodyMedium,
                    )
                }
                if (alerts > 0) {
                    AlertDot(number = alerts)
                }
                ToolkitIcon(imageVector = ToolkitIconCatalog.ArrowIndicatorRight)
            }
        }
    )
}

@AppPreview
@Composable
private fun Preview(@PreviewParameter(HomeProvider::class) exams: List<ItemHomeUiModel>) {
    ToolkitPreviewContainer {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = ToolkitSpacingMd),
            verticalArrangement = Arrangement.spacedBy(ToolkitSpacingMd),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            exams.forEach { exam ->
                HomeExamsCell(
                    lab = exam.lab,
                    date = exam.date,
                    alerts = exam.alerts,
                    onEdit = { }
                )
            }
        }
    }
}
