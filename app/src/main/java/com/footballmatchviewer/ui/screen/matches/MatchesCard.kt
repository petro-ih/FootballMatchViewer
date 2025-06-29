package com.footballmatchviewer.ui.screen.matches

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
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
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.footballmatchviewer.R

@Composable
fun MatchesCard(
    match: MatchUiItem,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(horizontal = 20.dp, vertical = 4.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(16.dp),
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .height(100.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Team(match.homeTeam)

            VerticalDivider(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp),
                color = Color.Black.copy(alpha = 0.2f)
            )

            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = match.date,
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp,
                        color = LocalContentColor.current.copy(alpha = 0.5f)
                    )
                    Text(
                        text = match.time.toString(),
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp,
                        color = LocalContentColor.current.copy(alpha = 0.5f)
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                val goalsHome = match.homeTeam.goals?.toString() ?: "-"
                val goalsAway = match.awayTeam.goals?.toString() ?: "-"
                Text(
                    text = "$goalsHome:$goalsAway",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }

            VerticalDivider(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp),
                color = Color.Black.copy(alpha = 0.2f)
            )

            Team(match.awayTeam)
        }
    }
}

@Composable
fun @Composable RowScope.Team(team: MatchUiItem.Team) {
    Column(
        modifier = Modifier
            .weight(1f)
            .padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = team.icon,
            contentDescription = null,
            placeholder = painterResource(R.drawable.ic_team_placeholder),
            error = painterResource(R.drawable.ic_team_placeholder),
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )
        Text(
            text = team.name,
            maxLines = 2,
            softWrap = true,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun MatchesCardPreview() {
    MatchesCard(
        match = MatchUiItem(
            homeTeam = MatchUiItem.Team(
                name = "Team 1",
                icon = "Icon 1",
                goals = 2
            ),
            awayTeam = MatchUiItem.Team(
                name = "Team 2",
                icon = "Icon 2",
                goals = 0
            ),
            date = "01-01-2021",
            time = "15:30"
        )
    )
}


