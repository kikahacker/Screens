package com.example.myapplication.ui.screen.signIn

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.ui.screen.singIn.AuthTextFiled
import com.example.myapplication.ui.screen.singIn.CommonButton
import com.example.myapplication.ui.screen.singIn.PasswordTextField
import com.example.myapplication.ui.screen.singIn.RebootPasswordButton
import com.example.myapplication.ui.screen.singIn.TitleWithSubtitleText
import com.example.myapplication.ui.theme.MatuleTheme

@Composable
fun RecoverPassScreen(){
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .padding(top = 66.dp, start = 20.dp)
                    .fillMaxWidth()
            ) {
                IconButton(onClick = {}, modifier = Modifier
                    .height(44.dp)
                    .width(44.dp)) {
                    Icon(painter = painterResource(R.drawable.back_arrow),
                        contentDescription = null)
                }
            }
        }
    ) { paddingValues ->
        RecoverPassContent(paddingValues)
    }

}

@Composable
fun RecoverPassContent(paddingValues: PaddingValues){
    Column(
        modifier = Modifier
            .padding(paddingValues = paddingValues)
    ) {
        Column (
            modifier =  Modifier.padding(top = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text =  "Забыл Пароль",
                style = MatuleTheme.typography.headingBold32.copy(color = MatuleTheme.colors.text),
                textAlign = TextAlign.Center
            )
            Text(text =  "Введите Свою Учетную Запись \n Для Сброса",
                maxLines = 2,
                style = MatuleTheme.typography.subTitleRegular16.copy(color = MatuleTheme.colors.subTextDark),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 13.dp)
            )
            val emailAuth = remember { mutableStateOf("") }
            Spacer(modifier = Modifier.padding(top = 20.dp))
            AuthTextFiled(
                lableText = "",
                placeHolderText = stringResource(R.string.template_email),
                value = emailAuth.value,
                onChangeValue = {
                    emailAuth.value = it
                },

            )
            CommonButton(
                modifier = Modifier.padding(top = 40.dp),
                buttonLabel = "Отправить"
            ){

            }
        }

    }

}
