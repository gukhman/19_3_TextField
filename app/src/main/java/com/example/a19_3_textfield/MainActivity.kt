package com.example.a19_3_textfield

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TextAdd()
        }
    }
}

@SuppressLint("MutableCollectionMutableState")
@Composable
fun TextAdd() {
    Scaffold(
        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
            ) {
                var text by rememberSaveable { mutableStateOf("") }
                val itemList = rememberSaveable(saver = listSaver(
                    save = { stateList -> stateList.toList() },
                    restore = { it.toMutableStateList() }
                )) { mutableStateListOf<String>() }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    OutlinedTextField(
                        value = text,
                        onValueChange = { text = it },
                        label = { Text("Введите текст") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Добавить",
                        modifier = Modifier
                            .align(Alignment.End)
                            .clickable(onClick = {
                                if (text.isNotEmpty()) {
                                    itemList.add(text)
                                    text = ""
                                }
                            })
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    LazyColumn {
                        item {
                            Text(
                                text = "Динамический список",
                                fontSize = 26.sp,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(shape = RoundedCornerShape(15.dp))
                                    .background(color = Color.LightGray)
                                    .padding(12.dp)
                            )

                            Spacer(modifier = Modifier.height(16.dp))
                        }

                        items(itemList) { item ->
                            Text(
                                text = item,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                                    .clickable {
                                        itemList.remove(item)
                                    }
                            )
                        }
                    }
                }
            }
        }
    )
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    TextAdd()
}
