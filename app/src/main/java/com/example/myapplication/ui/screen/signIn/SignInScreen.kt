package com.example.myapplication.ui.screen.singIn


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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.ui.screen.signIn.SignUpScreen
import com.example.myapplication.ui.theme.MatuleTheme


@Composable
fun SingInScreen(){
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
                    text = stringResource(R.string.ur_first),
                    style = MatuleTheme.typography.bodyRegular16.copy(color = MatuleTheme.colors.subTextDark),
                    textAlign = TextAlign.Center,

                )
                Text(
                    text = stringResource(R.string.sign_up),
                    style = MatuleTheme.typography.bodyMedium16.copy(color = MatuleTheme.colors.text),
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .clickable(onClick ={} )
                )
            }
        }
    ) { paddingValues ->
        SingInContent(paddingValues)
    }

}

@Composable
fun SingInContent(paddingValues: PaddingValues){
    Column(
        modifier = Modifier
            .padding(paddingValues = paddingValues)
    ) {
        TitleWithSubtitleText(
            title = stringResource(R.string.hello),
            subTitle = stringResource(R.string.signin_subtitle)
        )
        val emailAuth = remember { mutableStateOf("") }
        Spacer(modifier = Modifier.height(35.dp))
        AuthTextFiled(
            lableText = stringResource(R.string.email),
            placeHolderText = stringResource(R.string.template_email),
            value = emailAuth.value,
            onChangeValue = {
                emailAuth.value = it
            }
        )
        val passwordAuth = remember { mutableStateOf("") }
        Spacer(modifier = Modifier.height(30.dp))
        PasswordTextField(
            labelText = stringResource(R.string.password),
            value = passwordAuth.value,
            placeHolderText = "********",
            onValueChange = {
                passwordAuth.value = it
            }
        )

        RebootPasswordButton(
            buttonLabel = stringResource(R.string.reboot_pass)
        )
        CommonButton(
            modifier = Modifier.padding(top = 50.dp),
            buttonLabel = stringResource(R.string.sign_in)){

        }
    }

}

@Composable
fun TitleWithSubtitleText(title: String, subTitle: String){
    Column (
        modifier =  Modifier.padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text =  title,
            style = MatuleTheme.typography.headingBold32.copy(color = MatuleTheme.colors.text),
            textAlign = TextAlign.Center
        )
        Text(text =  subTitle,
            maxLines = 2,
            style = MatuleTheme.typography.subTitleRegular16.copy(color = MatuleTheme.colors.subTextDark),
            textAlign = TextAlign.Center
        )

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthTextFiled(value:String,onChangeValue:(String)->Unit,placeHolderText:String? = null,lableText:String?=null){
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
fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeHolderText: String? = null,
    labelText: String? = null
) {
    var showPassword by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth()
            .wrapContentSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        if (labelText != null) {
            Text(
                text = labelText,
                style = MatuleTheme.typography.bodyRegular16.copy(MatuleTheme.colors.text),
                textAlign = TextAlign.Start
            )
        }

        // Используем TextField
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(end = 8.dp),
            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            interactionSource = remember { MutableInteractionSource() },
            placeholder = {
                if (placeHolderText != null) {
                    Text(
                        text = placeHolderText,
                        style = MatuleTheme.typography.bodyRegular14.copy(color = MatuleTheme.colors.hint)
                    )
                }
            },
            trailingIcon = {
                IconButton(onClick = { showPassword = !showPassword }) {
                    Icon(
                        painter = if (showPassword) {
                            painterResource(R.drawable.visble)
                        } else {
                            painterResource(R.drawable.visibleoff)
                        },
                        contentDescription = "Переключить видимость пароля"
                    )
                }
            },
            shape = RoundedCornerShape(14.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MatuleTheme.colors.background,
                unfocusedContainerColor = MatuleTheme.colors.background,
                disabledContainerColor = MatuleTheme.colors.background,
                errorContainerColor = MatuleTheme.colors.background,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent
            ),
            isError = false
        )
    }
}


@Composable
fun CommonButton(modifier: Modifier,buttonLabel:String,onClick:()->Unit){
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

@Composable
fun RebootPasswordButton(buttonLabel: String){
    Text(
        modifier = Modifier
            .padding(top = 12.dp, start = 287.dp)
            .height(16.dp)
            .width(95.dp)
            .clickable { },
        text = buttonLabel,
        style = MatuleTheme.typography.bodyRegular12.copy(color = MatuleTheme.colors.subTextDark),
        textAlign = TextAlign.Center
        )
}
