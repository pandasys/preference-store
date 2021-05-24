/*
 * Copyright (c) 2021  Eric A. Snell
 *
 * This file is part of PreferenceStore
 *
 * PreferenceStore is free software: you can redistribute it and/or modify it under the terms of
 * the GNU Lesser General Public License as published by the Free Software Foundation, either
 * version 3 of the License, or (at your option) any later version.
 *
 * PreferenceStore is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with
 * PreferenceStore. If not, see <http://www.gnu.org/licenses/>.
 */

package com.ealva.comppref.pref

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ContentAlpha
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ListItem
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@ExperimentalMaterialApi
@Composable
public fun Setting(
  title: String,
  summary: String,
  singleLineTitle: Boolean,
  icon: ImageVector? = null,
  enabled: Boolean = true,
  onClick: () -> Unit = {},
  trailing: @Composable (() -> Unit)? = null
) {
  EnabledWrapper(enabled = enabled) {
    ListItem(
      text = {
        Text(
          text = title,
          maxLines = if (singleLineTitle) 1 else Int.MAX_VALUE,
          modifier = if (enabled) Modifier else Modifier.alpha(ContentAlpha.disabled)
        )
      },
      secondaryText = {
        Text(
          text = summary,
          modifier = if (enabled) Modifier else Modifier.alpha(ContentAlpha.disabled)
        )
      },
      icon = icon?.let {
        {
          Icon(
            imageVector = it, null, modifier = Modifier
              .padding(8.dp)
              .size(24.dp)
          )
        }
      },
      modifier = Modifier.clickable(onClick = { if (enabled) onClick() }),
      trailing = trailing,
    )
  }
}

@Composable
public fun EnabledWrapper(enabled: Boolean = true, content: @Composable () -> Unit) {
  CompositionLocalProvider(
    LocalContentAlpha provides if (enabled) ContentAlpha.high else ContentAlpha.disabled
  ) {
    content()
  }
}


@ExperimentalMaterialApi
@Composable
public fun Setting(
  title: String,
  summary: @Composable (() -> Unit)? = null,
  singleLineTitle: Boolean,
  icon: ImageVector? = null,
  enabled: Boolean = true,
  onClick: () -> Unit = {},
  trailing: @Composable (() -> Unit)? = null
) {
  EnabledWrapper(enabled = enabled) {
    ListItem(
      text = {
        Text(
          text = title,
          maxLines = if (singleLineTitle) 1 else Int.MAX_VALUE,
          modifier = if (enabled) Modifier else Modifier.alpha(ContentAlpha.disabled)
        )
      },
      secondaryText = summary,
      icon = icon?.let {
        {
          Icon(
            imageVector = it, null, modifier = Modifier
              .padding(8.dp)
              .size(24.dp)
          )
        }
      },
      modifier = Modifier.clickable(onClick = { if (enabled) onClick() }),
      trailing = trailing,
    )
  }
}
