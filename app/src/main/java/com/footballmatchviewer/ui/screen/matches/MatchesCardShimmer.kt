package com.footballmatchviewer.ui.screen.matches

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer

private val shimmerColor = Color(0xFF2C2C2C)

@Composable
fun MatchesCardShimmer() {
    Card(
        modifier = Modifier.padding(horizontal = 20.dp, vertical = 4.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(16.dp),
    ) {
        Row(
            modifier = Modifier
                .shimmer()
                .padding(8.dp)
                .height(100.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(shimmerColor)
                )
                Spacer(modifier = Modifier.height(8.dp))
                ShimmerLine(widthFraction = 0.6f, height = 16.dp)
            }

            VerticalDivider(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp),
                color = Color.Gray.copy(alpha = 0.2f)
            )

            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                ShimmerLine(width = 60.dp, height = 12.dp)
                Spacer(modifier = Modifier.height(4.dp))
                ShimmerLine(width = 40.dp, height = 12.dp)
                Spacer(modifier = Modifier.height(10.dp))
                ShimmerLine(width = 60.dp, height = 28.dp, corner = 6.dp)
            }

            VerticalDivider(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp),
                color = Color.Gray.copy(alpha = 0.2f)
            )

            // Away team
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(shimmerColor)
                )
                Spacer(modifier = Modifier.height(8.dp))
                ShimmerLine(widthFraction = 0.6f, height = 16.dp)
            }
        }
    }
}

@Composable
fun ShimmerLine(
    width: Dp = Dp.Unspecified, widthFraction: Float = 1f, height: Dp, corner: Dp = 4.dp
) {
    Box(
        modifier = Modifier
            .then(
                if (width != Dp.Unspecified) Modifier.width(width)
                else Modifier.fillMaxWidth(widthFraction)
            )
            .height(height)
            .clip(RoundedCornerShape(corner))
            .background(shimmerColor)
    )
}

@Preview(showBackground = true)
@Composable
fun MatchesCardShimmerPreview() {
    Column(modifier = Modifier.fillMaxWidth()) {
        MatchesCardShimmer()
    }
}
