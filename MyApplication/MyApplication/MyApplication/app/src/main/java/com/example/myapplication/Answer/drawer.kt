package com.example.myapplication.Answer

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SideMenuWithCoroutineExample() {
    // State to control drawer open/close
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // ModalNavigationDrawer with drawer content and main content
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(drawerState)
        },
        content = {
            MainContentWithCoroutine(drawerState, scope)
        }
    )
}

@Composable
fun DrawerContent(drawerState: DrawerState) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(250.dp)
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text("Menu", style = MaterialTheme.typography.headlineMedium, color = Color.Black)
        Spacer(modifier = Modifier.height(20.dp))

        Text("Home", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(16.dp))

        Text("Profile", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(16.dp))

        Text("Settings", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(16.dp))

        Text("Help", style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
fun MainContentWithCoroutine(drawerState: DrawerState, scope: CoroutineScope) {

    var myVariable = remember { (0) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            // Launch a coroutine to open the drawer
            scope.launch {
                if(myVariable == 0){
                    drawerState.open()
                    myVariable = 1
                } else{
                    drawerState.close()
                    myVariable = 0
                }
            }
        }) {
            Text("Open Menu")
        }
    }
}
