const credentials = [
  {
    username: "admin@mail.com",
    password: "1234",
  },
  {
    username: "user@mail.com",
    password: "5678",
  },
];

const validate = (event) => {
  event.preventDefault();

  var i_email = document.getElementById("email").value;
  var i_password = document.getElementById("password").value;
  var emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
  let isValid = false;

  credentials.forEach((element) => {
    if (element.username === i_email && element.password === i_password) {
      isValid = true;
    }
  });

  if (i_email === "" || i_password === "") {
    alert("Fields cannot be null");
  } else if (!i_email.match(emailPattern)) {
    alert("Enter a valid email");
  } else if (!isValid) {
    alert("Enter the right credentials");
  } else {
    window.location.href = "signup.html";
  }
};
