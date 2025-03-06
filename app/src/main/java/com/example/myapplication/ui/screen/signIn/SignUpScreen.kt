package com.example.myapplication.ui.screen.signIn

import android.widget.Button
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.ui.data.remote.RetrofitClient
import com.example.myapplication.ui.data.remote.User
import com.example.myapplication.ui.screen.singIn.AuthTextFiled
import com.example.myapplication.ui.screen.singIn.CommonButton
import com.example.myapplication.ui.screen.singIn.PasswordTextField
import com.example.myapplication.ui.screen.singIn.SingInContent
import com.example.myapplication.ui.screen.singIn.TitleWithSubtitleText
import com.example.myapplication.ui.theme.MatuleTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import kotlin.coroutines.coroutineContext


@Composable
fun SignUpScreen(){
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
        },
        bottomBar = {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(bottom = 50.dp)
                    .fillMaxWidth()
                    .height(40.dp)

            ) {
                Text(
                    text = "Есть аккаунт?",
                    style = MatuleTheme.typography.bodyRegular16.copy(color = MatuleTheme.colors.subTextDark),
                    textAlign = TextAlign.Center,

                    )
                Text(
                    text = "Войти",
                    style = MatuleTheme.typography.bodyMedium16.copy(color = MatuleTheme.colors.text),
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .clickable { }
                )
            }
        }
    ) { paddingValues ->
        SignUpContent(paddingValues)
    }

}

@Composable
fun SignUpContent(paddingValues: PaddingValues){
    Column(
        modifier = Modifier
            .padding(paddingValues = paddingValues)
    ){
        TitleWithSubtitleText(
            title = stringResource(R.string.registr),
            subTitle = stringResource(R.string.signin_subtitle)
        )
        val name = remember { mutableStateOf("") }
        Spacer(modifier = Modifier.height(35.dp))
        NameField(
            value = name.value,
            lableText = stringResource(R.string.name),
            placeHolderText = stringResource(R.string.xxxxxxxx),
            onChangeValue = {
                name.value = it
            }
        )
        val emailReg = remember { mutableStateOf("") }
        Spacer(modifier = Modifier.height(10.dp))
        AuthTextFiled(
            lableText = stringResource(R.string.email),
            placeHolderText = stringResource(R.string.template_email),
            value = emailReg.value,
            onChangeValue = {
                emailReg.value = it
            }
        )
        val passwordReg = remember { mutableStateOf("") }
        Spacer(modifier = Modifier.height(10.dp))
        PasswordTextField(
            labelText = stringResource(R.string.password),
            value = passwordReg.value,
            placeHolderText = "********",
            onValueChange = {
                passwordReg.value = it
            }
        )
        val colorButton = Color(0xFF48B2E7)
        var checked by remember { mutableStateOf(false) }
        Row(modifier = Modifier.padding(top = 12.dp)) {
            IconButton(
                onClick = {checked = !checked},
                modifier = Modifier
                    .padding(top = 7.dp, start = 20.dp)
                    .height(18.dp)
                    .width(18.dp)
            ) {
                Icon(painter = painterResource(R.drawable.policycheck),
                    contentDescription = null,
                    modifier = Modifier
                        .height(7.5.dp)
                        .width(6.dp)
                    )
            }
            Column(modifier = Modifier.padding(start = 12.dp)) {
                Text(
                    text = "Даю согласие на обработку",
                    style = MatuleTheme.typography.bodyRegular16.copy(color = MatuleTheme.colors.hint),
                    textDecoration = TextDecoration.Underline
                )
                Text(
                    text = "персональных данных",
                    style = MatuleTheme.typography.bodyRegular16.copy(color = MatuleTheme.colors.hint),
                    textDecoration = TextDecoration.Underline
                )
            }
        }
        val coroutine = rememberCoroutineScope{ Dispatchers.IO}
        CommonButtonForReg(
            modifier = Modifier.padding(top = 30.dp),
            buttonLabel = stringResource(R.string.registration),
            onClick = {
                val user = User(userName = name.value, password = passwordReg.value, email = emailReg.value)
                coroutine.launch{ RetrofitClient.retrofit.registration(user) }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NameField(value:String,onChangeValue:(String)->Unit,placeHolderText:String? = null,lableText:String?=null){
    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .wrapContentSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        if(lableText!= null)
            Text(text = lableText,
                style = MatuleTheme.typography.bodyRegular16.copy(MatuleTheme.colors.text),
                textAlign = TextAlign.Right
            )
        val interaction = remember { MutableInteractionSource() }
        BasicTextField(
            value = value,
            onValueChange = { onChangeValue(it) },
            modifier = Modifier

                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .background(MatuleTheme.colors.background)
        ){
                innerTextField ->
            TextFieldDefaults.DecorationBox(
                value = value,
                singleLine = true,
                innerTextField = innerTextField,
                enabled = true,
                visualTransformation = VisualTransformation.None,
                interactionSource = interaction,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MatuleTheme.colors.background,
                    disabledContainerColor = MatuleTheme.colors.background,
                    unfocusedContainerColor = MatuleTheme.colors.background,
                    errorContainerColor = MatuleTheme.colors.background,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent,

                    ),
                placeholder = {
                    if(placeHolderText!=null)
                        Text(text = placeHolderText,
                            style = MatuleTheme.typography.bodyRegular14.copy(color = MatuleTheme.colors.hint))
                }
            )
        }
    }
}
@Composable
fun CommonButtonForReg(modifier: Modifier,buttonLabel:String,onClick:()->Unit){
    Button(
        modifier = modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth()
            .height(50.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(MatuleTheme.colors.accent)
        ,
        colors = ButtonColors(
            containerColor = MatuleTheme.colors.accent,
            disabledContentColor = Color.Transparent,
            disabledContainerColor = MatuleTheme.colors.accent,
            contentColor = Color.Transparent
        ),
        onClick = onClick
    )
    {
        Text(
            text = buttonLabel,
            style = MatuleTheme.typography.bodyRegular14.copy(color = MatuleTheme.colors.background),
            textAlign = TextAlign.Center
        )
    }
}