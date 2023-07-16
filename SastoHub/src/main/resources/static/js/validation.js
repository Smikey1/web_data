const wname = document.getElementById('name')
const password = document.getElementById('password')
const mobile = document.getElementById('mobile')
const email = document.getElementById('email')
const cpassword = document.getElementById('cpassword')
const form = document.getElementById('form')
const errorElement = document.getElementById('errorMsg')

form.addEventListener("submit", (event) => {
    event.preventDefault();

    // Perform custom validation
    if (wname.value.trim() === "") {
        // Display error message inside Bootstrap alert
        errorMsg.innerText = "Name cannot be empty.";
        errorMsg.classList.remove("d-none");
    }
    if (email.value.trim() === "") {
        // Display error message inside Bootstrap alert
        errorMsg.innerText = "Email cannot be empty.";
        errorMsg.classList.remove("d-none");
    }
    else if(password.value.trim() === ""){
        errorMsg.innerText = "Password cannot be empty.";
        errorMsg.classList.remove("d-none");
    }
    else if(password.value.length <=6 ){
        errorMsg.innerText = "Password cannot be less than 7 letter.";
        errorMsg.classList.remove("d-none");
    }
    else if(mobile.value.length < 10 ){
        errorMsg.innerText = "Mobile number must be 10 numbers.";
        errorMsg.classList.remove("d-none");
    }
    else if(password.value.trim() != cpassword.value.trim() ){
        errorMsg.innerText = "Password does not match.";
        errorMsg.classList.remove("d-none");
    }
    else {
        // Clear error message and submit form
        errorMsg.innerText = "";
        errorMsg.classList.add("d-none");
        form.submit();
    }
});
