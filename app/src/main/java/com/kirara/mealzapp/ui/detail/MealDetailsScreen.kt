package com.kirara.mealzapp.ui.detail

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.kirara.mealzapp.model.response.MealResponse
import kotlin.math.min

@Composable
fun MealDetailsScreen(meal: MealResponse?){
    val scrollState = rememberLazyListState()
    val offset = min(1f, 1 -
            (scrollState.firstVisibleItemScrollOffset / 600f
            + scrollState.firstVisibleItemIndex))
    val size by animateDpAsState(targetValue = max(100.dp, 140.dp * offset), label = "")
    Surface(
        color = MaterialTheme.colorScheme.background
    ) {
        Column {
            Surface(
                shadowElevation = 4.dp
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Card(
                        modifier = Modifier.padding(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.background
                        ),
                        shape = CircleShape,
                        border = BorderStroke(
                            width = 2.dp,
                            color = Color.Green
                        )
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(meal?.imageUrl)
                                .crossfade(true)
                                .transformations(
                                    listOf(
                                        CircleCropTransformation()
                                    )
                                )
                                .build(),
                            contentDescription = null,
                            modifier = Modifier.size(size)
                        )
                    }
                    Text(
                        text = meal?.name ?: "default name",
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.CenterVertically)
                    )
                }
            }
            val dummyContentList = (0..100).map { it.toString() }
            LazyColumn(state = scrollState, modifier = Modifier.fillMaxWidth()) {
                items(dummyContentList) { dummyItem ->
                    Text(text = dummyItem, modifier = Modifier.padding(24.dp))
                }
            }
        }
    }
}

enum class MealProfilePictureState(
    val color: Color,
    val size: Dp,
    val borderWidth: Dp
){
    Normal(Color.Magenta,120.dp,8.dp),
    Expanded(Color.Green,200.dp,16.dp)
}