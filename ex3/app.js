const credentials = [
  {
    email: "admin@mail.com",
    password: "1234",
    username: "admin",
  },
  {
    email: "arul@mail.com",
    password: "5678",
    username: "arul",
  },
];

const validate = (event) => {
  event.preventDefault();

  var i_name = document.getElementById("name").value;
  var i_password = document.getElementById("password").value;
  var emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
  let isValid = false;

  credentials.forEach((element) => {
    if (
      (element.username === i_name || element.email === i_name) &&
      element.password === i_password
    ) {
      isValid = true;
    }
  });

  if (i_name === "" || i_password === "") {
    alert("Fields cannot be null");
  } else if (!isValid) {
    alert("Enter the right credentials");
  } else if (!i_name.match(emailPattern)) {
    alert("Enter a valid email");
  } else {
    // window.location.href = "signup.html";
    alert("Login Successful!");
  }
};
