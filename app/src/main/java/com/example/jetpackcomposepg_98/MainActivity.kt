/*
7 kyu London CityHacker JetpackComposePG_98
 Version earlier:  KotlinPG_253

https://www.codewars.com/kata/5bce125d3bb2adff0d000245


Description:
You are given a sequence of a journey in London, UK. The sequence will contain bus numbers
and TFL tube names as strings e.g. arrayOf("Northern", "Central", 243, 1, "Victoria")
Journeys will always only contain a combination of tube names and bus numbers. Each tube
journey costs £2.40 and each bus journey costs £1.50. If there are 2 or more adjacent bus
journeys, the bus fare is capped for sets of two adjacent buses and calculated as one bus
fare for each set.
For example: 'Piccadilly', 56, 93, 243, 20, 14 -> "£6.90"
Your task is to calculate the total cost of the journey and return the cost rounded to 2
decimal places in the format (where x is a number): £x.xx
Fundamentals

Solution
1
package codewars.cityhacker
2
​
3
fun londonCityHacker(journey: Array<Any>): String {
4
    // Your code goes here!
5
}

package codewars.cityhacker

import kotlin.test.assertEquals
import org.junit.Test

class LondonCityHackerTest {

    @Test
    fun exampleTests() {
        assertEquals("£7.80", londonCityHacker(arrayOf(12, "Central", "Circle", 21)));
        assertEquals("£3.90", londonCityHacker(arrayOf("Piccidilly", 56)));
        assertEquals("£7.20", londonCityHacker(arrayOf("Northern", "Central", "Circle")));
        assertEquals("£5.40", londonCityHacker(arrayOf("Piccidilly", 56, 93, 243)));
        assertEquals("£3.00", londonCityHacker(arrayOf(386, 56, 1, 876)));
        assertEquals("£0.00", londonCityHacker(arrayOf()));
    }
}
 */


package com.example.jetpackcomposepg_98

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LondonCityHacker()
        }
    }
}

@Composable
fun LondonCityHacker() {
    val backGroundColor = Color.Black
    val textColor = Color.LightGray
    val textSize = 20.sp
    val topPadding = 20.dp
    val tubes = listOf("Central", "Circle", "Northern", "Piccadilly")
    val buses = listOf(1, 12, 21, 56, 93, 243, 386, 876)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .background(color = backGroundColor)
            .padding(20.dp)
            .verticalScroll(
                rememberScrollState()
            )
    ) {
//Title:
        Text(
            text = "London CityHacker",
            color = textColor,
            fontSize = textSize * 2,
            modifier = Modifier.padding(top = topPadding * 2)
        )
//Introduction:
        Text(
            text = "With this tiny app you can calculate the total cost of the journey " +
                    "in London. Each tube journey costs £2.40 and each bus journey costs £1.50." +
                    " If there are 2 or more adjacent bus journeys, the bus fare is capped " +
                    "for sets of two adjacent buses and calculated as one bus fare for each set. " +
                    "It is not as simple. With this app you can calculate the fare of a journey. " +
                    "As start you can pick from some sample journeys, but fourther you can " +
                    "create journey plan by yourself.",
            color = textColor,
            fontSize = textSize,
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(top = topPadding)
        )
//Sample journeys:
        Text(
            text = "Try sample journey plans:",
            color = textColor,
            fontSize = textSize * 1.5,
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(top = topPadding)
        )
        val exampleJourneys = listOf(
            listOf(12, "Central", "Circle", 21),
            listOf("Piccadilly", 56),
            listOf("Northern", "Central", "Circle"),
            listOf("Piccadilly", 56, 93, 243),
            listOf(386, 56, 1, 876)
        )
        var pickedJourneyExample by remember {
            mutableStateOf("")
        }
//Task for the user:
        Text(
            text = "You can click on a sample journey to pick it, and to calculate the fare of it!",
            color = textColor,
            fontSize = textSize,
            modifier = Modifier.padding(top = topPadding, bottom = topPadding)
        )
//Write out the sample journeys on the screen by the self made  "wrightOutTheList" function:
        for (i in exampleJourneys.indices) {
            Text(
                text = "sample ${i + 1}: ${wrightOutTheList(exampleJourneys[i])}",
                color = textColor,
                fontSize = textSize,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(top = topPadding / 5)
                    .fillMaxWidth()
//Pick a sample journey:
                    .clickable {
                        pickedJourneyExample = "You have picked the sample journey:\n" +
                                "${wrightOutTheList(exampleJourneys[i])}\nThe fare " +
                                "of this sample journey is ${londonCityHacker(exampleJourneys[i])} £."
                    }
            )
        }
//Revealing the picked journey example and the fare of it:
        Text(
            text = pickedJourneyExample,
            color = textColor,
            fontSize = textSize,
            lineHeight = 30.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(top = topPadding)
        )
//Creating your own journey plan by yourself:
        var createdJourney by remember {
            mutableStateOf(mutableListOf<Any>())
        }
        var solution by remember {
            mutableStateOf("")
        }


        Text(
            text = "Create your own journey plan",
            color = textColor,
            fontSize = textSize * 1.5,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = topPadding)
        )
        Text(
            text = "You can click on the tube lines and on the the bus lines to pick them for a " +
                    "journey plan and to calculate the fare of it!",
            color = textColor,
            fontSize = textSize,
            modifier = Modifier.padding(top = topPadding, bottom = topPadding)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
//Write out the lists of the tubes and the buses:
            Column {
                Text(
                    text = "Tube lines:",
                    color = textColor,
                    fontSize = textSize
                )
                for (i in tubes) {
                    Text(text = i,
                        color = textColor,
                        fontSize = textSize,
                        lineHeight = 30.sp,
                        modifier = Modifier
                            //Picking the tubes for the journey plan:
                            .clickable {
                                createdJourney.add(i)
                                solution = "The created journey " +
                                        "plan:\n${wrightOutTheList(createdJourney)}\nThe " +
                                        "fare is £${londonCityHacker(createdJourney)}"
                            }
                    )
                }
            }
            Column {
                Text(
                    text = "Bus lines:",
                    color = textColor,
                    fontSize = textSize
                )
                for (i in buses) {
                    Text(text = i.toString(),
                        color = textColor,
                        fontSize = textSize,
                        lineHeight = 30.sp,
                        modifier = Modifier
//Picking the buses for the journey plan:
                            .clickable {
                                createdJourney.add(i)
                                solution = "The created journey " +
                                        "plan:\n${wrightOutTheList(createdJourney)}\nThe " +
                                        "fare is £${londonCityHacker(createdJourney)}"
                            }

                    )
                }
            }
        }
        //Revealing the created journey plan and the fare of it:
        Text(
            text = solution,
            fontSize = textSize,
            color = textColor,
            lineHeight = 30.sp,
            modifier = Modifier.padding(top = topPadding)
        )
        if (solution.isNotEmpty()){
            Button(onClick = {
                pickedJourneyExample = ""
                createdJourney = mutableListOf()
                  solution = ""
            },
                modifier = Modifier.padding(top = topPadding)
                ) {
                Text(text = "New begin")
            }
        }
    }
}

//Function for write out the items of the list of the journey samples:
fun wrightOutTheList(inPutList: List<Any>): String {
    var outPutString = ""
    for (j in 0 until inPutList.size - 1) {
        outPutString += "${inPutList[j]}  -  "
    }
    outPutString += inPutList[inPutList.size - 1]
    return outPutString
}

//Function for calculate the fare of a journey:
fun londonCityHacker(journey: List<Any>): String {
    var fare = 0.00
    var set = 0
    if (journey == null) {
        fare = 0.00
    } else {
        for (i in journey) {
//The case "int" that means bus:
            if (i::class.simpleName == "Int") {
                set++
//The case first bus of the set with 1.50 fare:
                if (set == 1) {
                    fare += 1.50
//The case second adjacent bus with no fare:
                } else if (set == 2) {
                    fare += 0.00
//Setting the "set" variable to zero after the second bus in the set:
                    set = 0
                }
//The case string, that means TFL tube name:
            } else if (i::class.simpleName == "String") {
                fare += 2.40
//Setting the "set" variable to zero after TFL tube in the case, when the set variable was increased earlier to 1:
                set = 0
            }
        }
    }
    return String.format("%.2f", fare)
}

