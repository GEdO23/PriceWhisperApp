package br.com.pricewhisper.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.com.pricewhisper.productListDestination

@Composable
fun HomeScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(32.dp),
        modifier = modifier
    ) {
        ButtonGroup(
            navController = navController,
            modifier = Modifier
                .border(2.dp, Color.Gray, RoundedCornerShape(24.dp))
                .padding(32.dp)
        )
    }
}

@Composable
private fun ButtonGroup(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        ActionButton(
            label = "Products",
            modifier = Modifier.widthIn(min = 100.dp, max = 100.dp)
        ) { navController.navigate(productListDestination.route) }
        ActionButton(
            label = "Profile",
            modifier = Modifier.widthIn(min = 100.dp, max = 100.dp)
        ) {}
        ActionButton(
            label = "Settings",
            modifier = Modifier.widthIn(min = 100.dp, max = 100.dp)
        ) {}
    }
}


@Composable
private fun ActionButton(
    modifier: Modifier = Modifier,
    label: String,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        FilledIconButton(onClick = onClick) { }
        Text(
            text = label,
            textAlign = TextAlign.Center
        )
    }
}
//
//@Preview(showSystemUi = true)
//@Composable
//private fun HomePreview() {
//    PriceWhisperTheme {
//        HomeScreen(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(16.dp),
//            navController = navController
//        )
//    }
//}