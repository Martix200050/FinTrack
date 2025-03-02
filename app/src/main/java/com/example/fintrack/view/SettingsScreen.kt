package com.example.fintrack.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fintrack.R
import com.example.fintrack.ui.theme.FinTrackTheme
import com.example.fintrack.viewModel.SettingsViewModel

@Composable
fun SettingsScreen(
    navController: NavController,
    language: Boolean,
    settingsViewModel: SettingsViewModel
) {

    var selectedIndex by rememberSaveable { mutableStateOf(language) }
    var index = 2
    FinTrackTheme {
        Scaffold(
            bottomBar = {
                BottomNavigationBar(navController, index)
            }
        ) { paddingValues ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(MaterialTheme.colorScheme.surface),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.SettingsScreen),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.inverseSurface,
                    modifier = Modifier.padding(bottom = 50.dp, top = 5.dp)
                )
                Row(
                    modifier = Modifier
                        .padding(bottom = 50.dp)
                        .clip(RoundedCornerShape(30.dp))
                        .size(220.dp, 60.dp)
                        .background(MaterialTheme.colorScheme.primaryContainer),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .clickable {
                                settingsViewModel.saveLanguage(true); selectedIndex = true
                            }
                            .clip(CircleShape)
                            .background(if (selectedIndex) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primaryContainer)
                            .size(64.dp, 48.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(id = R.string.USD),
                            style = MaterialTheme.typography.bodySmall,
                            color = if (selectedIndex) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.primary
                        )
                    }
                    Box(
                        modifier = Modifier
                            .clickable {
                                settingsViewModel.saveLanguage(false); selectedIndex = false
                            }
                            .clip(CircleShape)
                            .background(if (!selectedIndex) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primaryContainer)
                            .size(64.dp, 48.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(id = R.string.UAH),
                            style = MaterialTheme.typography.bodySmall,
                            color = if (!selectedIndex) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.primary
                        )
                    }

                }
            }
        }
    }
}