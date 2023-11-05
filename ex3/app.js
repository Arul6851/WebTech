const credentials = [
  {
    username: "admin",
    password: "1234",
  },
  {
    username: "user",
    password: "5678",
  },
];

const validate = (event) => {
  event.preventDefault();

  var i_email = document.getElementById("email").value;
  var i_password = document.getElementById("password").value;
  var card = document.getElementById("contain");
  let flag = true;
  var errorField = document.createElement("div");
  var emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;

  credentials.forEach((element) => {
    if (element.username === i_email && element.password === i_password)
      flag = false;
  });
  if (i_email == "" || i_password == "") {
    errorField.innerHTML("cannot be null");
    card.appendChild(errorField);
  } else if (flag == false) {
    errorField.innerHTML("cannot be null");
    card.appendChild(errorField);
  } else if (!i_email.match(emailPattern)) {
    errorField.innerHTML("cannot be null");
    card.appendChild(errorField);
  } else {
    // window.location.href = "signup.html";
    errorField.innerHTML("cannot be null");
    card.appendChild(errorField);
  }
};

// const validate = () => {
//   alert("Button Pressed");
//   return false;
// };
