document.getElementById("test").addEventListener("click", test);
$("paymentcheck").addEventListener("click", paypal);
function test(){
    console.log("oaeshrfopuewh");
}

function paypal(){
    console.log("kicserélek mindent");
    var paymentDiv = document.getElementById("payment");
    paymentDiv.innerHTML = "";
    paymentDiv.appendChild(<label for="paypalmail"> Paypal email</label>);
    paymentDiv.appendChild(<input type="text" id="paypalmail" placeholder="example@email.com">);
}

