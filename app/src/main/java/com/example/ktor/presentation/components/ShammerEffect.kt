package com.example.ktor.presentation.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.*
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.compose.items
import com.example.ktor.domain.model.Hero
import com.example.ktor.presentation.common.HeroItem
import com.example.ktor.ui.theme.*

@Composable
fun ShimmerEffect(){

    LazyColumn(
        contentPadding = PaddingValues(all = SMALL_PADDING),
        verticalArrangement = Arrangement.spacedBy(SMALL_PADDING)
    ){

       items(count = 4){
           AnimatedShimmerItem()
       }

    }

}

@Composable
fun AnimatedShimmerItem(){
    val transition = rememberInfiniteTransition()
    val alphaAnimation by transition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 500,
                easing = FastOutLinearInEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )
    ShimmerItem(alpha = alphaAnimation)
}

@Composable
fun ShimmerItem(alpha: Float){
    Surface(modifier = Modifier
        .fillMaxWidth()
        .height(HERO_ITEM_HEIGHT),
        color = if(isSystemInDarkTheme()) Color.Black else ShimmerLightGray,
        shape = RoundedCornerShape(size = LARGE_PADDING)
    ) {

        Column(
            modifier = Modifier.padding(MEDIUM_PADDING),
            verticalArrangement = Arrangement.Bottom
            ) {


            Surface(modifier = Modifier
                .alpha(alpha = alpha)
                .fillMaxWidth(0.5f)
                .height(NAME_PLACEHOLDER_HEIGHT),
                color = if(isSystemInDarkTheme()) Color.Black else ShimmerMediumGray,
                shape = RoundedCornerShape(size = SMALL_PADDING)
            ) {}
            Spacer(modifier = Modifier.padding(all = SMALL_PADDING))

            repeat(3){

                Surface(modifier = Modifier
                    .alpha(alpha = alpha)
                    .fillMaxWidth()
                    .height(ABOUT_PLACEHOLDER_HEIGHT),
                    color = if(isSystemInDarkTheme()) Color.Black else ShimmerMediumGray,
                    shape = RoundedCornerShape(size = SMALL_PADDING)
                ) {}
                Spacer(modifier = Modifier.padding(all = EXTRA_SMALL_PADDING))

            }

            Row(modifier = Modifier.fillMaxWidth()) {
                repeat(5){

                    Surface(modifier = Modifier
                        .alpha(alpha = alpha)
                        .size(RATING_PLACEHOLDER_HEIGHT),
                        color = if(isSystemInDarkTheme()) Color.Black else ShimmerMediumGray,
                        shape = RoundedCornerShape(size = SMALL_PADDING)
                    ) {}
                    Spacer(modifier = Modifier.padding(all = SMALL_PADDING))

                }
            }

        }

    }
}

@Preview
@Composable
fun ShimmerEffectPreview() {
    AnimatedShimmerItem()
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ShimmerEffectDarkPreview() {
    AnimatedShimmerItem()
}