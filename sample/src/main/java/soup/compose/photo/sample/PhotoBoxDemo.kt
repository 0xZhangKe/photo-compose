/*
 * Copyright 2023 SOUP
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package soup.compose.photo.sample

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import kotlinx.coroutines.launch
import soup.compose.photo.ExperimentalPhotoApi
import soup.compose.photo.PhotoBox
import soup.compose.photo.rememberPhotoState

@OptIn(ExperimentalPhotoApi::class)
@Composable
fun PhotoBoxDemo() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "PhotoBoxDemo") })
        }
    ) { padding ->
        val painter = painterResource(R.drawable.wallpaper)
        val photoState = rememberPhotoState()
        photoState.setPhotoIntrinsicSize(painter.intrinsicSize)
        PhotoBox(
            state = photoState,
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
        ) {
            Image(
                painter,
                contentDescription = "drawable",
                modifier = Modifier.fillMaxSize(),
            )
        }

        val coroutineScope = rememberCoroutineScope()
        BackHandler(enabled = photoState.isScaled) {
            coroutineScope.launch {
                photoState.animateToInitialState()
            }
        }
    }
}
