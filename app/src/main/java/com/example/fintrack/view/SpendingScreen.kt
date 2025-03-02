package com.example.fintrack.view


import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fintrack.R
import com.example.fintrack.model.Expense
import com.example.fintrack.model.categoryItems
import com.example.fintrack.ui.theme.FinTrackTheme
import com.example.fintrack.viewModel.ExpenseViewModel
import kotlin.random.Random


@Composable
fun SpendingScreen(
    navController: NavController,
    language: Boolean,
    expenseViewModel: ExpenseViewModel,
    expense: List<Expense>
) {
    var selectedDate by remember { mutableLongStateOf(System.currentTimeMillis()) }
    var selectedIndex by rememberSaveable { mutableIntStateOf(1) }
    var selectedCategory by rememberSaveable { mutableIntStateOf(Random.nextInt(0, 4)) }
    var index = 0
    var isPressed by remember { mutableStateOf(false) }
    var amount by remember { mutableStateOf("") }
    var comment by remember { mutableStateOf("") }
    val showDatePicker = remember { mutableStateOf(false) }
    var isQABar by remember { mutableStateOf(false) }
    var currentExpense by remember {
        mutableStateOf(
            Expense(
                amount = 0,
                category = 0,
                comment = "",
                date = 0
            )
        )
    }

    FinTrackTheme {
        Scaffold(
            bottomBar = {
                if (!isQABar) BottomNavigationBar(navController, index)
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
                    text = stringResource(id = R.string.SpendingScreen),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.inverseSurface,
                    modifier = Modifier.padding(bottom = 50.dp, top = 5.dp)
                )
                Row(
                    modifier = Modifier
                        .padding(bottom = 50.dp)
                        .clip(RoundedCornerShape(30.dp))
                        .size(280.dp, 60.dp)
                        .background(MaterialTheme.colorScheme.primaryContainer),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {
                        expenseViewModel.getExpensesSortedBySmallestAmount(); selectedIndex = 0
                    }) {
                        Icon(
                            modifier = Modifier
                                .size(52.dp)
                                .clip(CircleShape)
                                .background(if (selectedIndex == 0) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primaryContainer),
                            imageVector = Icons.Default.KeyboardArrowUp,
                            tint = if (selectedIndex == 0) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.primary,
                            contentDescription = null
                        )
                    }
                    IconButton(onClick = {
                        expenseViewModel.getExpensesByTime(); selectedIndex = 1
                    }) {
                        Icon(
                            modifier = Modifier
                                .size(52.dp)
                                .clip(CircleShape)
                                .background(if (selectedIndex == 1) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primaryContainer),
                            imageVector = Icons.Default.DateRange,
                            tint = if (selectedIndex == 1) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.primary,
                            contentDescription = null
                        )
                    }
                    IconButton(onClick = {
                        expenseViewModel.getExpensesSortedByLargestAmount();selectedIndex = 2
                    }) {
                        Icon(
                            modifier = Modifier
                                .size(52.dp)
                                .clip(CircleShape)
                                .background(if (selectedIndex == 2) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primaryContainer),
                            imageVector = Icons.Default.KeyboardArrowDown,
                            tint = if (selectedIndex == 2) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.primary,
                            contentDescription = null
                        )
                    }
                }
                LazyColumn(
                    modifier = Modifier
                        .padding(bottom = 30.dp)
                        .clip(RoundedCornerShape(50.dp))
                        .size(320.dp, 460.dp)
                        .background(MaterialTheme.colorScheme.surfaceContainerHighest),
                    contentPadding = PaddingValues(vertical = 30.dp)
                ) {
                    items(expense) { item ->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(horizontal = 45.dp, vertical = 15.dp)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        if (!isPressed) {
                                            if (!isQABar) {
                                                currentExpense = item; isQABar = true
                                            }
                                        }
                                    }
                            ) {
                                Column {
                                    Text(
                                        text = "${item.amount} ${
                                            if (language) stringResource(R.string.USD) else stringResource(
                                                R.string.UAH
                                            )
                                        }",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.inverseSurface
                                    )
                                    Text(
                                        text = millisecondsToTime(item.date),
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.outline
                                    )
                                }
                                Box(
                                    modifier = Modifier
                                        .clip(CircleShape)
                                        .size(46.dp)
                                        .background(categoryItems[item.category].backgroundColor),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = categoryItems[item.category].icon,
                                        contentDescription = null,
                                        tint = categoryItems[item.category].mainColor,
                                        modifier = Modifier
                                            .clip(CircleShape)
                                            .size(33.dp)
                                            .background(categoryItems[item.category].backgroundColor)
                                    )
                                }
                            }
                            Text(
                                item.comment,
                                style = MaterialTheme.typography.titleSmall,
                                color = MaterialTheme.colorScheme.outline,
                                modifier = Modifier.padding(top = 5.dp)
                            )
                        }

                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxHeight(),
                    contentAlignment = Alignment.Center
                ) {
                    IconButton(onClick = { isPressed = true }) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null,
                            modifier = Modifier.size(62.dp),
                            tint = MaterialTheme.colorScheme.outline
                        )
                    }
                }
            }
        }
        if (isQABar) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(MaterialTheme.colorScheme.surfaceContainerHigh),
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    Text(
                        "${stringResource(R.string.Confirmation)} ${currentExpense.amount} ${
                            if (language) stringResource(R.string.USD) else stringResource(
                                R.string.UAH
                            )
                        }?",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.outline,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(
                            top = 20.dp,
                            bottom = 40.dp,
                            start = 15.dp,
                            end = 15.dp
                        )
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(0.6f),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(
                            onClick = {
                                isQABar = false
                            },
                            modifier = Modifier.size(120.dp, 45.dp),
                            shape = RoundedCornerShape(15.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.surfaceContainerHighest),
                        ) {
                            Text(
                                stringResource(R.string.Cancel),
                                color = MaterialTheme.colorScheme.outline
                            )
                        }
                        Button(
                            onClick = {
                                expenseViewModel.deleteExpense(currentExpense); isQABar = false
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                            modifier = Modifier.size(120.dp, 45.dp),
                            shape = RoundedCornerShape(15.dp)
                        ) {
                            Text(
                                stringResource(R.string.Delete),
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            }
        }
        if (isPressed) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        MaterialTheme.colorScheme.scrim.copy(
                            alpha = animateFloatAsState(
                                targetValue = if (isPressed) 0.4f else 0f,
                                animationSpec = tween(500)
                            ).value
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50.dp))
                        .size(320.dp, 520.dp)
                        .background(MaterialTheme.colorScheme.surfaceContainerHighest)
                ) {
                    Box(
                        modifier = Modifier
                            .padding(20.dp)
                            .fillMaxSize()
                    ) {
                        IconButton(onClick = { isPressed = false }) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = null,
                                modifier = Modifier.size(54.dp),
                                tint = MaterialTheme.colorScheme.outline
                            )
                        }
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = stringResource(id = R.string.SpendingScreen),
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.inverseSurface,
                                modifier = Modifier
                                    .padding(top = 5.dp)
                            )
                            TextField(
                                modifier = Modifier.padding(top = 20.dp, bottom = 20.dp),
                                value = amount,
                                onValueChange = {
                                    if (it.all { char -> char.isDigit() }) {
                                        amount = it
                                    }
                                },
                                keyboardOptions = KeyboardOptions.Default.copy(
                                    keyboardType = KeyboardType.Number,
                                    imeAction = ImeAction.Done
                                ),
                                label = {
                                    val style =
                                        MaterialTheme.typography.bodyMedium
                                    Text(
                                        text = stringResource(R.string.amount),
                                        style = style,
                                        color = MaterialTheme.colorScheme.outline
                                    )
                                }
                            )
                            TextField(
                                modifier = Modifier.padding(top = 20.dp, bottom = 20.dp),
                                value = comment,
                                onValueChange = { comment = it },
                                label = {
                                    val style =
                                        MaterialTheme.typography.bodyMedium
                                    Text(
                                        text = stringResource(R.string.comment),
                                        style = style,
                                        color = MaterialTheme.colorScheme.outline
                                    )
                                }
                            )
                            Row(
                                modifier = Modifier
                                    .padding(bottom = 20.dp)
                                    .clip(RoundedCornerShape(30.dp))
                                    .size(300.dp, 64.dp)
                                    .background(MaterialTheme.colorScheme.surfaceContainerHigh),
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                categoryItems.forEachIndexed { index, category ->
                                    IconButton(onClick = { selectedCategory = index }) {
                                        Icon(
                                            imageVector = category.icon,
                                            contentDescription = null,
                                            tint = if (selectedCategory == index) category.backgroundColor else category.mainColor,
                                            modifier = Modifier
                                                .size(60.dp)
                                                .clip(CircleShape)
                                                .background(if (selectedCategory == index) category.mainColor else category.backgroundColor)
                                        )
                                    }
                                }
                            }
                            Button(
                                modifier = Modifier.padding(bottom = 25.dp),
                                onClick = { showDatePicker.value = true },
                                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.surfaceContainerHigh)
                            ) {
                                Text(
                                    millisecondsToTime(selectedDate).toString(),
                                    color = MaterialTheme.colorScheme.outline,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }

                            if (showDatePicker.value) {
                                DatePickerModalInput(
                                    onDateSelected = { dateMillis ->
                                        if (dateMillis != null) {
                                            selectedDate = dateMillis
                                        }
                                    },
                                    onDismiss = {
                                        showDatePicker.value = false
                                    })
                            }
                            Button(
                                modifier = Modifier.size(140.dp, 55.dp),
                                onClick = {
                                    if (amount.isNotEmpty()) {
                                        expenseViewModel.addExpense(
                                            Expense(
                                                amount = amount.toInt(),
                                                category = selectedCategory,
                                                comment = comment,
                                                date = selectedDate
                                            )
                                        )
                                    }
                                    isPressed = false
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
                            ) {
                                Text(
                                    text = stringResource(R.string.create),
                                    color = MaterialTheme.colorScheme.secondary,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerModalInput(
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState(initialDisplayMode = DisplayMode.Input)

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                onDateSelected(datePickerState.selectedDateMillis)
                onDismiss()
            }) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}