
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.IN2000_prosjekt.R

// Composable function to display a navigation menu
@Composable
fun NavigationMenu(
    navController: NavController,
    modifier: Modifier = Modifier) {

    NavigationBar(modifier = modifier) {
        // Navigation item for the map screen
        NavigationBarItem(
            icon = {
                Icon(
                    painterResource(id = R.drawable.mapicon),
                    contentDescription = stringResource(R.string.map_content_description)
                )
            },
            label = { Text(stringResource(R.string.map)) },
            selected = false,
            onClick = { navController.navigate("MapScreen")}
        )

        // Navigation item for the sign screen
        NavigationBarItem(
            icon = {
                Icon(
                    painterResource(id = R.drawable.signicon),
                    contentDescription = stringResource(R.string.sign_content_description)
                )
            },
            label = { Text(stringResource(R.string.sign)) },
            selected = false,
            onClick = { navController.navigate("CategoryScreen") }
        )

        // Navigation item for the about screen
        NavigationBarItem(
            icon = {
                Icon(
                    painterResource(id = R.drawable.logo),
                    contentDescription = "Om-knapp",
                    modifier = Modifier.size(30.dp)
                )
            },
            label = { Text("Om") },
            selected = false,
            onClick = { navController.navigate("AboutScreen") }
        )
    }
}
